package com.sbmtech.mms.controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

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
import com.sbmtech.mms.payload.request.BuildingRequest;
import com.sbmtech.mms.payload.request.CommunityRequest;
import com.sbmtech.mms.payload.request.CreateUserRequest;
import com.sbmtech.mms.payload.request.FloorRequest;
import com.sbmtech.mms.payload.request.KeyMasterRequest;
import com.sbmtech.mms.payload.request.ParkingRequest;
import com.sbmtech.mms.payload.request.ParkingZoneRequest;
import com.sbmtech.mms.payload.request.SubscriberLocationRequest;
import com.sbmtech.mms.payload.request.SubscriptionPaymentRequest;
import com.sbmtech.mms.payload.request.SubscriptionRequest;
import com.sbmtech.mms.payload.request.TenantUnitRequest;
import com.sbmtech.mms.payload.request.UnitKeysRequest;
import com.sbmtech.mms.payload.request.UnitRequest;
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

	@PostMapping("/addAdditionalDetails")
	public ResponseEntity<?> addAdditionalDetails(@RequestBody AdditionalDetailsRequest request) {
		return ResponseEntity.ok(subscriberService.addAdditionalDetails(request));
	}

	@PostMapping("/createSubscription")
	public ResponseEntity<?> createSubscription(@Valid @RequestBody SubscriptionRequest request) {
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

	@PostMapping("/addSubscriberLocation")
	public ResponseEntity<?> addSubscriberLocation(@RequestBody SubscriberLocationRequest request) {
		return ResponseEntity.ok(subscriberService.addSubscriberLocation(request));
	}

	@PostMapping("/addCommunity")
	public ResponseEntity<?> addCommunity(@RequestBody CommunityRequest request) {
		return ResponseEntity.ok(subscriberService.addCommunity(request));
	}

	@PostMapping("/addBuilding")
	public ResponseEntity<?> addBuilding(@RequestBody BuildingRequest request) {
		return ResponseEntity.ok(subscriberService.addBuilding(request));
	}

	@PostMapping("/addFloor")
	public ResponseEntity<?> addFloor(@RequestBody FloorRequest request) {
		return ResponseEntity.ok(subscriberService.addFloor(request));
	}

	@PostMapping("/addUnit")
	public ResponseEntity<?> addUnit(@RequestBody UnitRequest request) {
		return ResponseEntity.ok(subscriberService.addUnit(request));
	}

	@PostMapping("/createTenant")
	public ResponseEntity<?> createUserAndMergeTenant(@RequestBody CreateUserRequest request) {
		return ResponseEntity.ok(subscriberService.createUserAndMergeTenant(request));
	}

	@PostMapping("/addParkingZone")
	public ResponseEntity<?> addParkingZone(@RequestBody ParkingZoneRequest request) {
		return ResponseEntity.ok(subscriberService.createParkingZone(request));
	}

	@PostMapping("/addParking")
	public ResponseEntity<?> addParking(@RequestBody ParkingRequest request) {
		return ResponseEntity.ok(subscriberService.createParking(request));
	}

	@PostMapping("/addKey")
	public ResponseEntity<?> addKey(@RequestBody KeyMasterRequest request) {
		return ResponseEntity.ok(subscriberService.addKey(request));
	}

	@PostMapping("/addUnitKey")
	public ResponseEntity<?> addUnitKey(@RequestBody UnitKeysRequest request) {
		return ResponseEntity.ok(subscriberService.addUnitKey(request));
	}

	@PostMapping("/addTenantUnit")
	public ResponseEntity<?> addTenantUnit(@RequestBody TenantUnitRequest request) {
		return ResponseEntity.ok(subscriberService.addTenantUnit(request));
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