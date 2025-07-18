package com.sbmtech.mms.controllers;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.constant.CommonConstants;
import com.sbmtech.mms.dto.BedspaceSearchCriteria;
import com.sbmtech.mms.models.Bedspace;
import com.sbmtech.mms.models.ProductConfig;
import com.sbmtech.mms.payload.request.AdditionalDetailsRequest;
import com.sbmtech.mms.payload.request.AreaRequest;
import com.sbmtech.mms.payload.request.BSUnitRequest;
import com.sbmtech.mms.payload.request.BedspaceRequest;
import com.sbmtech.mms.payload.request.DeleteBuildingRequest;
import com.sbmtech.mms.payload.request.DeleteUnitRequest;
import com.sbmtech.mms.payload.request.BuildingRequest;
import com.sbmtech.mms.payload.request.BuildingSearchRequest;
import com.sbmtech.mms.payload.request.BuildingUnitPaginationRequest;
import com.sbmtech.mms.payload.request.CommunityRequest;
import com.sbmtech.mms.payload.request.CreateUserRequest;
import com.sbmtech.mms.payload.request.DepartmentRequest;
import com.sbmtech.mms.payload.request.KeyMasterRequest;
import com.sbmtech.mms.payload.request.ParkingRequest;
import com.sbmtech.mms.payload.request.ParkingZoneRequest;
import com.sbmtech.mms.payload.request.ReserveUnitRequest;
import com.sbmtech.mms.payload.request.SavePaymentDetailsRequest;
import com.sbmtech.mms.payload.request.SubscriptionPaymentRequest;
import com.sbmtech.mms.payload.request.SubscriptionRequest;
import com.sbmtech.mms.payload.request.TenantIdRequest;
import com.sbmtech.mms.payload.request.TenantSearchRequest;
import com.sbmtech.mms.payload.request.TenantUnitRequest;
import com.sbmtech.mms.payload.request.TenantUpdateRequest;
import com.sbmtech.mms.payload.request.UnitKeysRequest;
import com.sbmtech.mms.payload.request.UnitPaginationRequest;
import com.sbmtech.mms.payload.request.UnitRequest;
import com.sbmtech.mms.payload.request.UnitUpdateRequest;
import com.sbmtech.mms.payload.request.UpdateUserRequest;
import com.sbmtech.mms.service.ConstantLookupService;
import com.sbmtech.mms.service.PaymentService;
import com.sbmtech.mms.service.ProductConfigService;
import com.sbmtech.mms.service.SubscriberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bsmgt")
@PreAuthorize("hasRole(@securityService.BSMgtAdmin)")
public class BSManagementController {

	@Autowired
	private SubscriberService subscriberService;

	@Autowired
	ConstantLookupService constantLookupService;

	@Autowired
	private ProductConfigService productConfigService;

	@Autowired
	PaymentService paymentService;

	@PostMapping("/addAdditionalDetails")
	public ResponseEntity<?> addAdditionalDetails(@Valid @RequestBody AdditionalDetailsRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {

		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addAdditionalDetails(request));
	}

	@PostMapping("/createSubscription")
	public ResponseEntity<?> createSubscription(@Valid @RequestBody SubscriptionRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {

		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.saveSubscription(request));
	}

	@GetMapping("/subscriptionPlans")
	public ResponseEntity<?> getAllSubscriptionPlans(
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {

		return ResponseEntity.ok(subscriberService.getAllSubscriptionPlans(CommonConstants.PLAN_CATEGORY_BSM));
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

	@GetMapping("/floors")
	public ResponseEntity<?> getAllFloors() {
		return ResponseEntity.ok(subscriberService.getAllFloors());
	}

	@PostMapping("/makePayment")
	public ResponseEntity<?> makePayment(@RequestBody SubscriptionPaymentRequest subscriptionPayment) {
		return ResponseEntity.ok(subscriberService.makePayment(subscriptionPayment));
	}

	@PostMapping("/addArea")
	public ResponseEntity<?> addSubscriberLocation(@Valid @RequestBody AreaRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.addArea(subscriberId, request));
	}

	@PostMapping("/addCommunity")
	public ResponseEntity<?> addCommunity(@Valid @RequestBody CommunityRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.addCommunity(subscriberId, request));
	}

	@PostMapping("/addBuilding")
	public ResponseEntity<?> addBuilding(@Valid @RequestBody BuildingRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addBuilding(request));
	}
	/*
	 * Not used, instead floor name is given in addunit directly from the lookup
	 * 
	 * @PostMapping("/addFloor") public ResponseEntity<?>
	 * addFloor(@Valid @RequestBody FloorRequest request,
	 * 
	 * @CurrentSecurityContext(expression = "authentication") Authentication auth)
	 * throws Exception { return
	 * ResponseEntity.ok(subscriberService.addFloor(request)); }
	 * 
	 */

	@PostMapping("/addUnit")
	public ResponseEntity<?> addUnit(@Valid @RequestBody BSUnitRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addUnit(request));
	}

	@PostMapping("/createTenant")
	public ResponseEntity<?> createTenant(@Valid @RequestBody CreateUserRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.createTenant(request));
	}
	
	@PostMapping("/bsCreateTenant")
	public ResponseEntity<?> bsCreateTenant(@RequestBody CreateUserRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.createTenant(request));
	}
	
	@PostMapping("/bsUpdateTenant")
	public ResponseEntity<?> bsUpdateTenant(@RequestBody UpdateUserRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.bsUpdateTenant(request));
	}


	@PostMapping("/addParkingZone")
	public ResponseEntity<?> addParkingZone(@Valid @RequestBody ParkingZoneRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.createParkingZone(request));
	}

	@PostMapping("/getAllParkingZoneByBuilding")
	public ResponseEntity<?> getAllParkingZoneByBuilding(@RequestBody ParkingZoneRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.getAllParkingZoneByBuilding(request));
	}

	@PostMapping("/addParking")
	public ResponseEntity<?> addParking(@Valid @RequestBody ParkingRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.createParking(request));
	}

	@PostMapping("/getAllParkingByBuilding")
	public ResponseEntity<?> getAllParkingByBuilding(@RequestBody ParkingRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.getAllParkingByBuilding(request));
	}

	@PostMapping("/addKey")
	public ResponseEntity<?> addKey(@Valid @RequestBody KeyMasterRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addKey(request));
	}

	@PostMapping("/addUnitKey")
	public ResponseEntity<?> addUnitKey(@Valid @RequestBody UnitKeysRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addUnitKey(request));
	}

	@PostMapping("/addTenantUnit")
	public ResponseEntity<?> addTenantUnit(@Valid @RequestBody TenantUnitRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addTenantUnit(request));
	}

	@PostMapping("/savePaymentDetails")
	public ResponseEntity<?> savePaymentDetails(@Valid @RequestBody SavePaymentDetailsRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(paymentService.savePaymentDetails(request));
	}

	@PostMapping("/addDepartment")
	public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentRequest request,
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.addDepartment(request));
	}

	@GetMapping("/getUnitTypeLookup")
	public ResponseEntity<?> getUnitTypeLookup(
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		return ResponseEntity.ok(constantLookupService.getUnitTypeLookup());

	}

	@GetMapping("/getUnitSubtypeLookup")
	public ResponseEntity<?> getUnitSubtypeLookup(
			@CurrentSecurityContext(expression = "authentication") Authentication auth, @RequestParam Integer unitTypId)
			throws Exception {
		return ResponseEntity.ok(constantLookupService.getUnitSubtypeLookup(unitTypId));
	}

	@GetMapping("/getUnitStatusLookup")
	public ResponseEntity<?> getUnitStatusLookup(
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		return ResponseEntity.ok(constantLookupService.getUnitStatusLookup());
	}

	@GetMapping("/getPaymentModeLookup")
	public ResponseEntity<?> getPaymentModeLookup(
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		return ResponseEntity.ok(constantLookupService.getPaymentModeLookup());
	}

	@GetMapping("/getRentCycleLookup")
	public ResponseEntity<?> getRentCycleLookup(
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		return ResponseEntity.ok(constantLookupService.getRentCycleLookup());
	}

	@GetMapping("/getPakingTypeLookup")
	public ResponseEntity<?> getPakingTypeLookup(
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {
		return ResponseEntity.ok(constantLookupService.getParkingTypeLookup());
	}

	@GetMapping("/getProductConfig")
	public ProductConfig getProductConfig(@CurrentSecurityContext(expression = "authentication") Authentication auth)
			throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return productConfigService.getProductConfigBySubscriberId(subscriberId);
	}

	@PostMapping("saveOrUpdateProductConfig")
	public ProductConfig saveOrUpdateProductConfig(
			@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@RequestBody Map<String, Object> config) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return productConfigService.saveOrUpdateProductConfig(subscriberId, config);
	}

	@PostMapping("/getAllBuildings")
	public ResponseEntity<?> getAllBuildings(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@RequestBody(required = false) BuildingSearchRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.getAllBuildings(subscriberId, request));
	}
	
	@PostMapping("/getBuildingById")
	public ResponseEntity<?> getBuildingById(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@RequestBody(required = false) BuildingRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.getBuildingById(subscriberId, request.getBuildingId()));
	}

	/*
	 * alternate approach for getAllBuildings search will be cosider later
	 */
	@PostMapping("/search")
	public ResponseEntity<?> searchBuildings(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@RequestBody(required = false) BuildingSearchRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);

		return ResponseEntity.ok(subscriberService.searchBuildings(subscriberId, request));
	}

	@PostMapping("/reserveUnit")
	public ResponseEntity<?> reserveUnit(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@Valid @RequestBody ReserveUnitRequest reserveUnitRequest) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.reserveUnit(subscriberId, reserveUnitRequest));
	}

	@PostMapping("/getAllUnitsByBuilding")
	public ResponseEntity<?> getAllUnitsByBuildingId(
			@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@Valid @RequestBody BuildingUnitPaginationRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.getAllUnitsByBuildingId(subscriberId, request.getBuildingId(),
				request.getPaginationRequest()));
	}

	@PostMapping("/getAllTenantsByBuilding")
	public ResponseEntity<?> getAllTenantsByBuildingId(
			@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@RequestBody BuildingUnitPaginationRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.getAllTenantsByBuildingId(subscriberId, request.getBuildingId(),
				request.getPaginationRequest()));
	}

	@PostMapping("/deleteBuilding")
	public ResponseEntity<?> deleteBuilding(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@Valid @RequestBody DeleteBuildingRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.deleteBuilding(request));
	}

	@PostMapping("/deleteUnit")
	public ResponseEntity<?> deleteUnit(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@Valid @RequestBody DeleteUnitRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.deleteUnit(request));
	}

	@PostMapping("/deleteTenant")
	public ResponseEntity<?> deleteTenant(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@Valid @RequestBody TenantIdRequest request) throws Exception {
		return ResponseEntity.ok(subscriberService.deleteTenant(request));
	}

	@PostMapping("/updateBuilding")
	public ResponseEntity<?> updateBuilding(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@Valid @RequestBody BuildingRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.updateBuilding(request));
	}

	@PostMapping("/updateUnit")
	public ResponseEntity<?> updateUnit(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@Valid @RequestBody UnitUpdateRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.updateUnit(subscriberId, request));
	}

	@PostMapping("/updateTenant")
	public ResponseEntity<?> updateTenant(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@Valid @RequestBody TenantUpdateRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.updateTenant(subscriberId, request));
	}

	@PostMapping("/getAllUnits")
	public ResponseEntity<?> searchUnits(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@RequestBody UnitPaginationRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.searchUnits(subscriberId, request));
	}

	@PostMapping("/getAllTenants")
	public ResponseEntity<?> getAllTenants(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@RequestBody TenantSearchRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		return ResponseEntity.ok(subscriberService.getAllTenants(subscriberId, request));
	}
	
	@GetMapping("/bsCategories")
	public  ResponseEntity<?> getAllCategories() {
		return ResponseEntity.ok(subscriberService.getAllBedspaceCategories());
	}

	@GetMapping("/bsBathtypes")
	public  ResponseEntity<?> getAllBathroomTypes() {
	    return ResponseEntity.ok(subscriberService.getAllBedspaceBathroomTypes());
	}

	@GetMapping("/bsPartitions")
	public  ResponseEntity<?> getAllPartitions() {
	    return ResponseEntity.ok(subscriberService.getAllBedspacePartitions());
	}
	
	@PostMapping("/createBedspace")
    public ResponseEntity<?> createBedspace(@CurrentSecurityContext(expression = "authentication") Authentication auth,
    		@RequestBody BedspaceRequest request)throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
        return ResponseEntity.ok(subscriberService.createBedspace(subscriberId, request));
       
    }
	
	@PostMapping("/updateBedspace")
	public ResponseEntity<?> updateBedspace(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@Valid @RequestBody BedspaceRequest request) throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		request.setSubscriberId(subscriberId);
		return ResponseEntity.ok(subscriberService.updateBedspace(subscriberId, request));
	}
	/*
	@PostMapping("/search")
	public ResponseEntity<?> searchBedspaces(@RequestBody BedspaceSearchCriteria criteria) {
		//ResponseEntity<List<BedspaceDTO>>
		List<Bedspace> bedspaces = subscriberService.searchBedspaces(criteria);
	    List<BedspaceDTO> result = bedspaces.stream()
	                                            .map(this::mapToDTO)
	                                            .collect(Collectors.toList());
	    return ResponseEntity.ok(result);
	}
	*/
	@PostMapping("/searchBedSpaces")
	public ResponseEntity<?> searchBedspaces(@CurrentSecurityContext(expression = "authentication") Authentication auth,
			@RequestBody BedspaceSearchCriteria criteria)throws Exception {
		Integer subscriberId = subscriberService.getSubscriberIdfromAuth(auth);
		criteria.setSubscriberId(subscriberId);
		//List<Bedspace> bedspaces =subscriberService.searchBedspaces(criteria);
	    return ResponseEntity.ok(subscriberService.searchBedspaces(criteria));
	    
	  //  return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getPaymentPurpose")
	public ResponseEntity<?> getPaymentPurpose(
			@CurrentSecurityContext(expression = "authentication") Authentication auth) throws Exception {

		return ResponseEntity.ok(paymentService.getAllPaymentPurposes());
	}
	
}