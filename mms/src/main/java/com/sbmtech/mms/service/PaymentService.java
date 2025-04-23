package com.sbmtech.mms.service;

import java.util.List;

import com.sbmtech.mms.models.PaymentPurpose;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.SavePaymentDetailsRequest;

public interface PaymentService {

	public ApiResponse<List<PaymentPurpose>> getAllPaymentPurposes() throws Exception;

	public ApiResponse<Object> savePaymentDetails(SavePaymentDetailsRequest request);
}
