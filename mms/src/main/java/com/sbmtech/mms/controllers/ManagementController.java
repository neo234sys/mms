package com.sbmtech.mms.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.models.Subscriptions;
import com.sbmtech.mms.payload.request.AdditionalDetailsRequest;
import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.SubscriptionPaymentRequest;
import com.sbmtech.mms.payload.request.SubscriptionRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.repository.SubscriptionRepository;
import com.sbmtech.mms.service.SubscriberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mgt")
public class ManagementController {

	@Autowired
	private SubscriberService subscriberService;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

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

	@PostMapping("/addAdditionalDetails")
	public ResponseEntity<?> addAdditionalDetails(@RequestBody AdditionalDetailsRequest request) {
		return ResponseEntity.ok(subscriberService.addAdditionalDetails(request));
	}

	@PostMapping("/createSubscription")
	public ResponseEntity<?> createSubscription(@RequestBody SubscriptionRequest request) {
		return ResponseEntity.ok(subscriberService.saveSubscription(request));
	}

	@GetMapping("/subscriptionPlans")
	public ResponseEntity<?> getAllSubscriptionPlans() {
		return ResponseEntity.ok(subscriberService.getAllSubscriptionPlans());
	}

	@PostMapping("/makePayment")
	public ResponseEntity<?> makePayment(@RequestBody SubscriptionPaymentRequest subscriptionPayment) {
		return ResponseEntity.ok(subscriberService.makePayment(subscriptionPayment));
	}

	@Scheduled(cron = "0/5 * * * * ?")
	public void expireSubscriptionsEvery5Seconds() {
		LocalDateTime now = LocalDateTime.now();

		List<Subscriptions> subscriptionsToExpire = subscriptionRepository
				.findByStatusInAndEndDateBefore(List.of("ACTIVE", "PAYMENT_PROCEEDED"), now);

		for (Subscriptions subscription : subscriptionsToExpire) {
			subscription.setStatus("EXPIRED");
			subscriptionRepository.save(subscription);
		}
	}

}