package com.sbmtech.mms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.service.PaymentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mgt/pay")
@PreAuthorize("hasRole(@securityService.MgtAdmin)")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@GetMapping("/getPaymentPurpose")
	public ResponseEntity<?> getPaymentPurpose(
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {

		return ResponseEntity.ok(paymentService.getAllPaymentPurposes());
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