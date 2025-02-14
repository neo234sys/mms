package com.sbmtech.mms.service;

import java.util.List;

import com.sbmtech.mms.models.ChannelMaster;
import com.sbmtech.mms.models.Countries;
import com.sbmtech.mms.models.SubscriptionPlanMaster;
import com.sbmtech.mms.payload.request.AdditionalDetailsRequest;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.BuildingRequest;
import com.sbmtech.mms.payload.request.CommunityRequest;
import com.sbmtech.mms.payload.request.CreateUserRequest;
import com.sbmtech.mms.payload.request.FloorRequest;
import com.sbmtech.mms.payload.request.KeyMasterRequest;
import com.sbmtech.mms.payload.request.ParkingRequest;
import com.sbmtech.mms.payload.request.ParkingZoneRequest;
import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.SubscriberLocationRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.SubscriptionPaymentRequest;
import com.sbmtech.mms.payload.request.SubscriptionRequest;
import com.sbmtech.mms.payload.request.TenantUnitRequest;
import com.sbmtech.mms.payload.request.UnitKeysRequest;
import com.sbmtech.mms.payload.request.UnitRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.payload.response.CityResponse;
import com.sbmtech.mms.payload.response.StateResponse;

public interface SubscriberService {
	public ApiResponse<String> createSubscriber(SubscriberRequest request) throws Exception;

	public ApiResponse<?> verifyOtp(VerifyOtpRequest request);

	public ApiResponse<String> resendOtp(ResendOtpRequest request);

	public ApiResponse<List<ChannelMaster>> getAllChannels();

	public ApiResponse<List<Countries>> getAllCountries();

	public ApiResponse<String> addAdditionalDetails(AdditionalDetailsRequest request);

	public ApiResponse<String> saveSubscription(SubscriptionRequest subscriptionRequest);

	public ApiResponse<List<SubscriptionPlanMaster>> getAllSubscriptionPlans();

	public ApiResponse<String> makePayment(SubscriptionPaymentRequest paymentRequest);

	public ApiResponse<List<StateResponse>> getStatesByCountryId(Integer countryId);

	public ApiResponse<List<CityResponse>> getCitiesByStateAndCountryId(Integer stateId, Integer countryId);

	public ApiResponse<String> addSubscriberLocation(SubscriberLocationRequest request);

	public ApiResponse<String> addCommunity(CommunityRequest request);

	public ApiResponse<String> addBuilding(BuildingRequest request);

	public ApiResponse<String> addFloor(FloorRequest request);

	public ApiResponse<String> addUnit(UnitRequest request);

	public ApiResponse<String> createUserAndMergeTenant(CreateUserRequest request);

	public ApiResponse<String> createParkingZone(ParkingZoneRequest request);

	public ApiResponse<String> createParking(ParkingRequest request);

	public ApiResponse<String> addKey(KeyMasterRequest request);

	public ApiResponse<String> addUnitKey(UnitKeysRequest request);

	public ApiResponse<String> addTenantUnit(TenantUnitRequest request);
}
