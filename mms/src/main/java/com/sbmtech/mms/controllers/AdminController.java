package com.sbmtech.mms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.constant.ErrorConstant;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.service.SubscriberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private SubscriberService subscriberService;

	@PostMapping("/createSubscriber")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "mms endpoint", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> createSubscriber(@RequestBody SubscriberRequest requestDTO) {
		try {
			subscriberService.createSubscriber(requestDTO);
			//return ResponseEntity.status(HttpStatus.CREATED)					.body(new ApiResponse<>(1, ErrorConstant.CREATE_SUBSCRIBER_SUCCESS, null));
		} catch (IllegalArgumentException e) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(0, e.getMessage(), null));
		} catch (Exception e) {
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)					.body(new ApiResponse<>(0, ErrorConstant.CREATE_SUBSCRIBER_ERROR, null));
		}
		return null;
	}
}
