package com.sbmtech.mms.service;

import java.util.List;

import javax.validation.Valid;

import com.sbmtech.mms.models.PaymentPurpose;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.OrderRentRequest;
import com.sbmtech.mms.payload.request.OrderReservationRequest;
import com.sbmtech.mms.payload.request.PaymentScheduleRequest;
import com.sbmtech.mms.payload.request.RentDuePaymentModeRequest;
import com.sbmtech.mms.payload.request.SavePaymentDetailsRequest;
import com.sbmtech.mms.payload.request.TransactionRequest;

public interface PaymentService {

	public ApiResponse<List<PaymentPurpose>> getAllPaymentPurposes() throws Exception;

	public ApiResponse<Object> savePaymentDetails(SavePaymentDetailsRequest request)throws Exception;

	public ApiResponse<Object> createPaymentSchedule(@Valid PaymentScheduleRequest request);

	public ApiResponse<Object>  createRentOrder(@Valid OrderRentRequest request);
	

	public ApiResponse<Object> updatePaymentModeDetails(@Valid RentDuePaymentModeRequest request)throws Exception;

	public ApiResponse<Object> getPaymentSchedule(@Valid PaymentScheduleRequest request);

	public  ApiResponse<Object> createTransaction(@Valid TransactionRequest request);

	public ApiResponse<Object> createReservationOrder(@Valid OrderReservationRequest request);
}
