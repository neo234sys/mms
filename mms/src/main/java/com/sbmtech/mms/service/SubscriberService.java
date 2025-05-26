package com.sbmtech.mms.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;

import com.sbmtech.mms.models.ChannelMaster;
import com.sbmtech.mms.models.Countries;
import com.sbmtech.mms.models.Subscriptions;
import com.sbmtech.mms.payload.request.AdditionalDetailsRequest;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.AreaRequest;
import com.sbmtech.mms.payload.request.BuildingRequest;
import com.sbmtech.mms.payload.request.BuildingSearchRequest;
import com.sbmtech.mms.payload.request.CommunityRequest;
import com.sbmtech.mms.payload.request.CreateUserRequest;
import com.sbmtech.mms.payload.request.DeleteBuildingRequest;
import com.sbmtech.mms.payload.request.DeleteUnitRequest;
import com.sbmtech.mms.payload.request.DepartmentRequest;
import com.sbmtech.mms.payload.request.KeyMasterRequest;
import com.sbmtech.mms.payload.request.PaginationRequest;
import com.sbmtech.mms.payload.request.ParkingRequest;
import com.sbmtech.mms.payload.request.ParkingZoneRequest;
import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.ReserveUnitRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
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
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.payload.response.SubscriptionPlans;

public interface SubscriberService {

	public Integer getSubscriberIdfromAuth(Authentication auth) throws Exception;

	public Map<String, Object> createSubscriber(SubscriberRequest request) throws Exception;

	public ApiResponse<?> verifyOtp(VerifyOtpRequest request);

	public ApiResponse<String> resendOtp(ResendOtpRequest request);

	public ApiResponse<List<ChannelMaster>> getAllChannels();

	public ApiResponse<List<Countries>> getAllCountries();

	public ApiResponse<String> addAdditionalDetails(AdditionalDetailsRequest request);

	public ApiResponse<String> saveSubscription(SubscriptionRequest subscriptionRequest);

	public ApiResponse<List<SubscriptionPlans>> getAllSubscriptionPlans();

	public ApiResponse<String> makePayment(SubscriptionPaymentRequest paymentRequest);

	public ApiResponse<Object> getStatesByCountryId(Integer countryId);

	public ApiResponse<Object> getCitiesByStateAndCountryId(Integer stateId, Integer countryId);

	public ApiResponse<Object> addArea(Integer subscriberId, AreaRequest request);

	public ApiResponse<Object> addCommunity(Integer subscriberId, CommunityRequest request);

	public ApiResponse<Object> addBuilding(BuildingRequest request);

	// public ApiResponse<Object> addFloor(FloorRequest request);

	public ApiResponse<Object> addUnit(UnitRequest request) throws Exception;

	public ApiResponse<Object> createTenant(CreateUserRequest request) throws Exception;

	public ApiResponse<Object> createParkingZone(ParkingZoneRequest request);

	public ApiResponse<Object> createParking(ParkingRequest request);

	public ApiResponse<Object> addKey(KeyMasterRequest request);

	public ApiResponse<Object> addUnitKey(UnitKeysRequest request);

	public ApiResponse<Object> addTenantUnit(TenantUnitRequest request);

	public ApiResponse<Object> addDepartment(@Valid DepartmentRequest request);

	public ApiResponse<Object> getAllBuildings(Integer subscriberId, BuildingSearchRequest request) throws Exception;

	public ApiResponse<Object> searchBuildings(Integer subscriberId, BuildingSearchRequest request) throws Exception;

	public ApiResponse<Object> reserveUnit(Integer subscriberId, ReserveUnitRequest reserveUnitRequest)throws Exception;

	public ApiResponse<Object> getAllUnitsByBuildingId(Integer subscriberId, Integer buildingId,
			PaginationRequest paginationRequest);

	public ApiResponse<Object> getAllTenantsByBuildingId(Integer subscriberId, Integer buildingId,
			PaginationRequest paginationRequest);

	public ApiResponse<?> deleteBuilding(DeleteBuildingRequest request);

	public ApiResponse<?> deleteUnit(DeleteUnitRequest request);

	public ApiResponse<?> deleteTenant(TenantIdRequest request);

	public ApiResponse<?> updateBuilding(BuildingRequest request) throws Exception;

	public ApiResponse<?> updateUnit(Integer subscriberId, UnitUpdateRequest request);

	public ApiResponse<?> updateTenant(Integer subscriberId, TenantUpdateRequest request);

	public ApiResponse<?> getAllFloors();

	public ApiResponse<Object> getAllParkingZoneByBuilding(@Valid ParkingZoneRequest request);

	public ApiResponse<Object> getAllParkingByBuilding(ParkingRequest request);

	public Subscriptions getActiveSubscriptionDetails(Integer subscriberId);

	public ApiResponse<Object> searchUnits(Integer subscriberId, UnitPaginationRequest request);

	public ApiResponse<Object> getAllTenants(Integer subscriberId, TenantSearchRequest request);

}
