package com.sbmtech.mms.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.sbmtech.mms.constant.CommonConstants;
import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.Subscriptions;
import com.sbmtech.mms.models.User;
import com.sbmtech.mms.payload.request.LoginRequest;
import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.payload.response.JwtResponse;
import com.sbmtech.mms.repository.RoleRepository;
import com.sbmtech.mms.repository.SubscriptionRepository;
import com.sbmtech.mms.repository.UserRepository;
import com.sbmtech.mms.security.jwt.JwtUtils;
import com.sbmtech.mms.security.services.UserDetailsImpl;
import com.sbmtech.mms.service.SubscriberService;
import com.sbmtech.mms.util.CommonUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subs")
public class SubscriberController {

	private static final Logger logger = LogManager.getLogger(SubscriberController.class);

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

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@PostMapping("/signin")
	public ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Subscriber subscriber = null;
		User user = null;
		JwtResponse jwtResponse = null;
		boolean isOTPVerified = false;
		boolean validSubscription = false;

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
				user = userOptional.get();
				subscriber = user.getSubscriber();

				if (subscriber != null) {
					logger.info("authenticateUser = {}", subscriber);

					if (subscriber.getOtpVerified() == null || subscriber.getOtpVerified() != 1) {
						isOTPVerified = false;
						ResendOtpRequest request = new ResendOtpRequest();
						request.setSubscriberId(subscriber.getSubscriberId());
						subscriberService.resendOtp(request);
						return CommonUtil.buildErrorResponse("OTP verification required before login.", isOTPVerified,
								subscriber.getSubscriberId());
					} else {
						isOTPVerified = true;
					}

					if (subscriber.getActive() == 0) {
						return CommonUtil.buildErrorResponse("Subscription suspended", isOTPVerified,
								subscriber.getSubscriberId());
					}

					Subscriptions existingSubscription = subscriptionRepository
							.findTopBySubscriber_SubscriberIdOrderBySubscriptionIdDesc(subscriber.getSubscriberId());
					if (existingSubscription != null) {
						if (CommonUtil.getCurrentLocalDate().isAfter(existingSubscription.getStartDate().minusDays(1))
								&& CommonUtil.getCurrentLocalDate().isBefore(existingSubscription.getEndDate())) {

							validSubscription = true;
						}
					}

					jwtResponse = new JwtResponse(jwt, userDetails.getUserId(), userDetails.getUsername(),
							userDetails.getMobileNo(), roles);
				}

			} else {
				logger.info("User not found with email: {}", loginRequest.getEmail());
				return CommonUtil.buildErrorResponse("User not found with email: " + loginRequest.getEmail(),
						isOTPVerified, null);
			}

		} catch (BadCredentialsException e) {
			return CommonUtil.buildErrorResponse("Invalid username or password!", isOTPVerified, null);

		} catch (Exception e) {
			return CommonUtil.buildErrorResponse("An error occurred during authentication!", isOTPVerified, null);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("responseCode", CommonConstants.SUCCESS_CODE);
		response.put("responseDesc", CommonConstants.SUCCESS_DESC);
		response.put("data", jwtResponse);
		response.put("userId", user.getUserId());
		response.put("subscriberId", subscriber != null ? subscriber.getSubscriberId() : null);
		response.put("isOTPVerified", isOTPVerified);
		response.put("validSubscription", validSubscription);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/createSubscriber")
	public ResponseEntity<?> createSubscriber(@Valid @RequestBody SubscriberRequest request) throws Exception {
		return ResponseEntity.ok(subscriberService.createSubscriber(request));
	}

	@PostMapping("/verifyOtp")
	public ResponseEntity<?> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
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
