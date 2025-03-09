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

import com.sbmtech.mms.payload.request.OrderRequest;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.SubscriptionRepository;
import com.sbmtech.mms.service.PaymentService;
import com.sbmtech.mms.service.SubscriberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mgt/pay")
@PreAuthorize("hasRole(@securityService.MgtAdmin)")
public class PaymentController {

	@Autowired
	private SubscriberService subscriberService;

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	
	@Autowired
	PaymentService paymentService;
	
	//@Autowired
	//OrderService orderService;
	

	@GetMapping("/getPaymentPurpose")
	public ResponseEntity<?> getPaymentPurpose(
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		
		return ResponseEntity.ok(paymentService.getAllPaymentPurposes());
	}
	
	
	@PostMapping("/createOrder")
	public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(paymentService.createOrder(request));
	}
	
	
	
	
}