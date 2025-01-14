package com.sbmtech.mms.service;

import java.util.List;

import com.sbmtech.mms.models.ChannelMaster;
import com.sbmtech.mms.models.Countries;
import com.sbmtech.mms.models.SubscriptionPlanMaster;
import com.sbmtech.mms.payload.request.AdditionalDetailsRequest;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.SubscriptionPaymentRequest;
import com.sbmtech.mms.payload.request.SubscriptionRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;

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
}
