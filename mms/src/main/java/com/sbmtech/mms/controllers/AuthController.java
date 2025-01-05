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

import com.sbmtech.mms.models.Company;
import com.sbmtech.mms.models.Countries;
import com.sbmtech.mms.models.ERole;
import com.sbmtech.mms.models.Role;
import com.sbmtech.mms.models.User;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.LoginRequest;
import com.sbmtech.mms.payload.request.SignupRequest;
import com.sbmtech.mms.payload.response.JwtResponse;
import com.sbmtech.mms.repository.CompanyRepository;
import com.sbmtech.mms.repository.CountriesRepository;
import com.sbmtech.mms.repository.RoleRepository;
import com.sbmtech.mms.repository.UserRepository;
import com.sbmtech.mms.security.jwt.JwtUtils;
import com.sbmtech.mms.security.services.CompanyService;
import com.sbmtech.mms.security.services.NationalityService;
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
	private CountriesRepository nationalityRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private NationalityService nationalityService;

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
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUserId(), userDetails.getUsername(),
					userDetails.getMobileNo(), roles);
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

		if (signUpRequest.getNatId() == null || !nationalityRepository.existsById(signUpRequest.getNatId())) {
			return ResponseEntity.ok(new ApiResponse<>(0, "Invalid Nationality ID!", null));
		}

//		if (signUpRequest.getCompanyId() == null || !companyRepository.existsById(signUpRequest.getCompanyId())) {
//			return ResponseEntity.ok(new ApiResponse<>(0, "Invalid Company ID!", null));
//		}

		System.out.println("pwd="+encoder.encode(signUpRequest.getPassword()));
		User user = new User();
		user.setMobileNo(signUpRequest.getMobileNo());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setActive(1);
		user.setEmiratesId(signUpRequest.getEmiratesId());
		user.setAddress(signUpRequest.getAddress());
		//user.setEidaCopy(signUpRequest.getEidaCopy());

		Countries nationality = nationalityRepository.findById(signUpRequest.getNatId())
				.orElseThrow(() -> new RuntimeException("Error: Nationality not found."));
//		Company company = companyRepository.findById(signUpRequest.getCompanyId())
//				.orElseThrow(() -> new RuntimeException("Error: Company not found."));
		//user.setNationality(nationality);
		user.setCountries(nationality);
		//user.setCompany(company);

		Role role = null;
		
		Set<Role> roles = new HashSet<>();
		
		if (signUpRequest.getRole().equals("admin")) {
			role = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		} else if (signUpRequest.getRole().equals("mgtadmin")) {
			role = roleRepository.findByName(ERole.ROLE_MGT_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(role);
		} 
		/*else {
			switch (signUpRequest.getRole()) {
			case "admin":
				role = roleRepository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				break;
			case "mod":
				role = roleRepository.findByName(ERole.ROLE_MODERATOR)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				break;
			default:
				role = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			}
		}
		*/

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

	@PostMapping("/company")
	public ResponseEntity<ApiResponse<Company>> createOrUpdateCompany(@RequestBody Company company) {
		try {
			Company savedCompany = companyService.saveCompany(company);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse<>(1, "Company created or updated successfully!", savedCompany));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(0, "An error occurred while saving the company!", null));
		}
	}

	

}
