package com.sbmtech.mms.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.payload.request.PaymentScheduleRequest;
import com.sbmtech.mms.payload.request.SavePaymentDetailsRequest;
import com.sbmtech.mms.service.PaymentService;
import com.sbmtech.mms.service.SubscriberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mgt/pay")
@PreAuthorize("hasRole(@securityService.MgtAdmin)")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	private SubscriberService subscriberService;

	@GetMapping("/getPaymentPurpose")
	public ResponseEntity<?> getPaymentPurpose(
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {

		return ResponseEntity.ok(paymentService.getAllPaymentPurposes());
	}
	
	
	@PostMapping("/createPaymentSchedule")
	public ResponseEntity<?> createPaymentSchedule(@Valid @RequestBody PaymentScheduleRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(paymentService.calculatePaymentSchedule(request));
	}

//	@PostMapping("/createOrder")
//	public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest request,
//			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
//		
//		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
//		request.setSubscriberId(subscriberId);
//		return ResponseEntity.ok(paymentService.createOrder(request));
//	}

}