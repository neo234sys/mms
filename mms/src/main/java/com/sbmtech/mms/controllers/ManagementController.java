package com.sbmtech.mms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.security.services.SubscriberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mgt")
public class ManagementController {

	@Autowired
	private SubscriberService subscriberService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse<String>> createSubscriber(@RequestBody SubscriberRequest requestDTO) {
		try {
			subscriberService.createSubscriber(requestDTO);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse<>(1, "Subscriber created successfully!", null));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(0, e.getMessage(), null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(0, "An error occurred while creating the subscriber!", null));
		}
	}
}
