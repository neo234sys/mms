package com.sbmtech.mms.service;

import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;

public interface SubscriberService {
	public ApiResponse<String> createSubscriber(SubscriberRequest request) throws Exception;

	public ApiResponse<?> verifyOtp(VerifyOtpRequest request);
}
