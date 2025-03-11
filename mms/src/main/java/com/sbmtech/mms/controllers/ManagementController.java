package com.sbmtech.mms.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.constant.CommonConstants;
import com.sbmtech.mms.constant.SubscriptionStatus;
import com.sbmtech.mms.models.ProductConfig;
import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.Subscriptions;
import com.sbmtech.mms.payload.request.AdditionalDetailsRequest;
import com.sbmtech.mms.payload.request.AreaRequest;
import com.sbmtech.mms.payload.request.BuildingRequest;
import com.sbmtech.mms.payload.request.CommunityRequest;
import com.sbmtech.mms.payload.request.CreateUserRequest;
import com.sbmtech.mms.payload.request.DepartmentRequest;
import com.sbmtech.mms.payload.request.FloorRequest;
import com.sbmtech.mms.payload.request.KeyMasterRequest;
import com.sbmtech.mms.payload.request.ParkingRequest;
import com.sbmtech.mms.payload.request.ParkingZoneRequest;
import com.sbmtech.mms.payload.request.SubscriptionPaymentRequest;
import com.sbmtech.mms.payload.request.SubscriptionRequest;
import com.sbmtech.mms.payload.request.TenantUnitRequest;
import com.sbmtech.mms.payload.request.UnitKeysRequest;
import com.sbmtech.mms.payload.request.UnitRequest;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.SubscriptionRepository;
import com.sbmtech.mms.service.ConstantLookupService;
import com.sbmtech.mms.service.ProductConfigService;
import com.sbmtech.mms.service.SubscriberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mgt")
@PreAuthorize("hasRole(@securityService.MgtAdmin)")
public class ManagementController {

	@Autowired
	private SubscriberService subscriberService;

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	
	@Autowired
	ConstantLookupService constantLookupService;
	
	 @Autowired
	 private ProductConfigService productConfigService;
	 
	
	@PostMapping("/addAdditionalDetails")
	public ResponseEntity<?> addAdditionalDetails(@Valid @RequestBody AdditionalDetailsRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth) throws Exception{
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addAdditionalDetails(request));
	}

	@PostMapping("/createSubscription")
	public ResponseEntity<?> createSubscription(@Valid @RequestBody SubscriptionRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.saveSubscription(request));
	}

	@GetMapping("/subscriptionPlans")
	public ResponseEntity<?> getAllSubscriptionPlans(
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		
		return ResponseEntity.ok(subscriberService.getAllSubscriptionPlans());
	}
	
	@GetMapping("/countries")
	public ResponseEntity<?> getAllCountries() {
		return ResponseEntity.ok(subscriberService.getAllCountries());
	}
	
	@GetMapping("/states")
	public ResponseEntity<?> getStatesByCountryId(@RequestParam Integer countryId) {
		return ResponseEntity.ok(subscriberService.getStatesByCountryId(countryId));
	}

	@GetMapping("/cities")
	public ResponseEntity<?> getCitiesByStateAndCountryId(@RequestParam Integer stateId,
			@RequestParam Integer countryId) {
		return ResponseEntity.ok(subscriberService.getCitiesByStateAndCountryId(stateId, countryId));
	}


	@PostMapping("/makePayment")
	public ResponseEntity<?> makePayment(@RequestBody SubscriptionPaymentRequest subscriptionPayment) {
		return ResponseEntity.ok(subscriberService.makePayment(subscriptionPayment));
	}

	@PostMapping("/addArea")
	public ResponseEntity<?> addSubscriberLocation(@Valid @RequestBody AreaRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception{
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addArea(request));
	}

	@PostMapping("/addCommunity")
	public ResponseEntity<?> addCommunity(@Valid @RequestBody CommunityRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth) throws Exception{
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addCommunity(request));
	}

	@PostMapping("/addBuilding")
	public ResponseEntity<?> addBuilding(@Valid @RequestBody BuildingRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addBuilding(request));
	}

	@PostMapping("/addFloor")
	public ResponseEntity<?> addFloor(@Valid @RequestBody FloorRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		//request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addFloor(request));
	}

	@PostMapping("/addUnit")
	public ResponseEntity<?> addUnit(@Valid @RequestBody UnitRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.addUnit(request));
	}

	@PostMapping("/createTenant")
	public ResponseEntity<?> createUserAndMergeTenant(@Valid @RequestBody CreateUserRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth) throws Exception {
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.createUserAndMergeTenant(request));
	}

	@PostMapping("/addParkingZone")
	public ResponseEntity<?> addParkingZone(@Valid @RequestBody ParkingZoneRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.createParkingZone(request));
	}

	@PostMapping("/addParking")
	public ResponseEntity<?> addParking(@Valid @RequestBody ParkingRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.createParking(request));
	}

	@PostMapping("/addKey")
	public ResponseEntity<?> addKey(@Valid @RequestBody KeyMasterRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addKey(request));
	}

	@PostMapping("/addUnitKey")
	public ResponseEntity<?> addUnitKey(@Valid @RequestBody UnitKeysRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addUnitKey(request));
	}

	@PostMapping("/addTenantUnit")
	public ResponseEntity<?> addTenantUnit(@Valid @RequestBody TenantUnitRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception{
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addTenantUnit(request));
	}
	
	
	
	@PostMapping("/addDepartment")
	public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentRequest request,
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addDepartment(request));
	}
	

	@GetMapping("/getUnitTypeLookup")
	public ResponseEntity<?> getUnitTypeLookup(
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		
		return ResponseEntity.ok(constantLookupService.getUnitTypeLookup());
	
	}
	
	@GetMapping("/getUnitSubtypeLookup")
	public ResponseEntity<?> getUnitSubtypeLookup(
			@CurrentSecurityContext(expression = "authentication")  Authentication auth,
			@RequestParam Integer unitTypId)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		
		return ResponseEntity.ok(constantLookupService.getUnitSubtypeLookup(unitTypId));
	}
	
	@GetMapping("/getUnitStatusLookup")
	public ResponseEntity<?> getUnitStatusLookup(
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		
		return ResponseEntity.ok(constantLookupService.getUnitStatusLookup());
	}
	
	@GetMapping("/getPaymentModeLookup")
	public ResponseEntity<?> getPaymentModeLookup(
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		
		return ResponseEntity.ok(constantLookupService.getPaymentModeLookup());
	}
	
	@GetMapping("/getRentCycleLookup")
	public ResponseEntity<?> getRentCycleLookup(
			@CurrentSecurityContext(expression = "authentication")  Authentication auth)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		
		return ResponseEntity.ok(constantLookupService.getRentCycleLookup());
	}
	
	
	@GetMapping("/getProductConfig")
    public ProductConfig getProductConfig(@CurrentSecurityContext(expression = "authentication")  Authentication auth) throws Exception{
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
        return productConfigService.getProductConfigBySubscriberId(subscriberId);
    }

    @PostMapping("saveOrUpdateProductConfig")
    public ProductConfig saveOrUpdateProductConfig(@CurrentSecurityContext(expression = "authentication")  Authentication auth,
            @RequestBody Map<String, Object> config) throws Exception{
    	Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
        return productConfigService.saveOrUpdateProductConfig(subscriberId, config);
    }
    
    
    
    @GetMapping("/getAllBuildings")
	public ResponseEntity<?> getAllBuildings(@CurrentSecurityContext(expression = "authentication")  Authentication auth,
			 @RequestParam(defaultValue = CommonConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	            @RequestParam(defaultValue = CommonConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	            @RequestParam(defaultValue = CommonConstants.DEFAULT_SORT_BY, required = false) String sortBy,
	            @RequestParam(defaultValue = CommonConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)throws Exception {
		
		Integer subscriberId=subscriberService.getSubscriberIdfromAuth(auth);
		
		return ResponseEntity.ok(subscriberService.getAllBuildings(subscriberId,pageNo, pageSize, sortBy, sortDir));
	}

	@Scheduled(cron = "0 */5 * * * ?") //every 5 hrs
	public void expireSubscriptionsEvery5Seconds() {
		LocalDateTime now = LocalDateTime.now();

		List<Subscriptions> subscriptionsToExpire = subscriptionRepository
				.findByStatusInAndEndDateBefore(List.of(SubscriptionStatus.ACTIVE.toString(), SubscriptionStatus.TRIAL.toString()), now);

		for (Subscriptions subscription : subscriptionsToExpire) {
			subscription.setStatus(SubscriptionStatus.EXPIRED.toString());
		
			subscriptionRepository.save(subscription);
			Subscriber subscriber=subscription.getSubscriber();
			subscriber.setActive(0);
			subscriberRepository.save(subscriber);
			
		}
	}
	
	
	
}