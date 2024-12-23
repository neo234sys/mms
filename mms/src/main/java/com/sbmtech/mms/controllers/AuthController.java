package com.sbmtech.mms.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.models.ERole;
import com.sbmtech.mms.models.Role;
import com.sbmtech.mms.models.User;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.LoginRequest;
import com.sbmtech.mms.payload.request.SignupRequest;
import com.sbmtech.mms.payload.response.JwtResponse;
import com.sbmtech.mms.repository.RoleRepository;
import com.sbmtech.mms.repository.UserRepository;
import com.sbmtech.mms.security.jwt.JwtUtils;
import com.sbmtech.mms.security.services.RoleService;
import com.sbmtech.mms.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private RoleService roleService;

	@PostMapping("/signin")
	public ResponseEntity<ApiResponse<Object>> authenticateUser(@RequestBody LoginRequest loginRequest) {

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getMobileNo(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), roles);
			return ResponseEntity.ok(new ApiResponse<>(1, "Authentication successful!", jwtResponse));

		} catch (BadCredentialsException e) {
			return ResponseEntity.ok(new ApiResponse<>(0, "Invalid username or password!", null));

		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse<>(0, "An error occurred during authentication!", null));
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<Object>> registerUser(@RequestBody SignupRequest signUpRequest) {

		if (userRepository.existsByMobileNo(signUpRequest.getMobileNo())) {
			return ResponseEntity.ok(new ApiResponse<>(0, "Mobile number is already taken!", null));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.ok(new ApiResponse<>(0, "Email is already in use!", null));
		}

		if (userRepository.existsByEmiratesId(signUpRequest.getEmiratesId())) {
			return ResponseEntity.ok(new ApiResponse<>(0, "Emirates ID is already registered!", null));
		}

		User user = new User();
		user.setMobileNo(signUpRequest.getMobileNo());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setActive(signUpRequest.getActive());
		user.setEmiratesId(signUpRequest.getEmiratesId());
		user.setAddress(signUpRequest.getAddress());
		user.setEidaCopy(signUpRequest.getEidaCopy());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new ApiResponse<>(1, "User registered successfully!", null));
	}

	@PostMapping("/role")
	public ResponseEntity<ApiResponse<Role>> createOrUpdateRole(@RequestBody Role role) {
		try {
			Role savedRole = roleService.saveRole(role);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse<>(1, "Role created or updated successfully!", savedRole));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(0, "An error occurred while saving the role!", null));
		}
	}

}
