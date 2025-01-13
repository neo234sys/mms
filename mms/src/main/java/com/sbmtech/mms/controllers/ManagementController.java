package com.sbmtech.mms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.service.SubscriberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mgt")
public class ManagementController {

	@Autowired
	private SubscriberService subscriberService;

	@PostMapping("/createSubscriber")
	public ResponseEntity<?> createSubscriber(@RequestBody SubscriberRequest request) throws Exception {
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