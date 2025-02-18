package com.sbmtech.mms.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.User;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.LoginRequest;
import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.payload.response.JwtResponse;
import com.sbmtech.mms.repository.RoleRepository;
import com.sbmtech.mms.repository.UserRepository;
import com.sbmtech.mms.security.jwt.JwtUtils;
import com.sbmtech.mms.security.services.UserDetailsImpl;
import com.sbmtech.mms.service.SubscriberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/op")
public class PublicController {
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
	private SubscriberService subscriberService;

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

			Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

			if (userOptional.isPresent()) {
				User user = userOptional.get();
				Subscriber subscriber = user.getSubscriber();

				if (subscriber != null && subscriber.getOtpVerified() == 1) {
					JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUserId(), userDetails.getUsername(),
							userDetails.getMobileNo(), roles);
					return ResponseEntity.ok(new ApiResponse<>(1, "Authentication successful!", jwtResponse,
							user.getUserId(), subscriber.getSubscriberId()));
				} else {
					return ResponseEntity.ok(new ApiResponse<>(0, "OTP not verified. Please verify your OTP to login.",
							null, null, null));
				}
			} else {
				return ResponseEntity.ok(new ApiResponse<>(0, "User not found with email: " + loginRequest.getEmail(),
						null, null, null));
			}

		} catch (BadCredentialsException e) {
			return ResponseEntity.ok(new ApiResponse<>(0, "Invalid username or password!", null, null, null));

		} catch (Exception e) {
			return ResponseEntity
					.ok(new ApiResponse<>(0, "An error occurred during authentication!", null, null, null));
		}
	}

	@PostMapping("/createSubscriber")
	public ResponseEntity<?> createSubscriber( @Valid @RequestBody SubscriberRequest request) throws Exception {
		return ResponseEntity.ok(subscriberService.createSubscriber(request));
	}

	@PostMapping("/verifyOtp")
	public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request) {
		return ResponseEntity.ok(subscriberService.verifyOtp(request));
	}

	@PostMapping("/resendOtp")
	public ResponseEntity<?> resendOtp(@RequestBody ResendOtpRequest request) {
		return ResponseEntity.ok(subscriberService.resendOtp(request));
	}

	@GetMapping("/channels")
	public ResponseEntity<?> getAllChannels() {
		return ResponseEntity.ok(subscriberService.getAllChannels());
	}

	@GetMapping("/countries")
	public ResponseEntity<?> getAllCountries() {
		return ResponseEntity.ok(subscriberService.getAllCountries());
	}

}
