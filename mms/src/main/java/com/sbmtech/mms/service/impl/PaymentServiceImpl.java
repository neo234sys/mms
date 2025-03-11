package com.sbmtech.mms.service.impl;

import static com.sbmtech.mms.constant.CommonConstants.FAILURE_CODE;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_DESC;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_CODE;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_DESC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.models.PaymentPurpose;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.OrderRequest;
import com.sbmtech.mms.payload.response.SubscriptionPlans;
import com.sbmtech.mms.repository.OrderRepository;
import com.sbmtech.mms.repository.PaymentPurposeRepository;
import com.sbmtech.mms.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentPurposeRepository paymentPurposeRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	public ApiResponse<Object> createOrder(OrderRequest request)throws Exception{
		
		//return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, buildingResp, null, request.getSubscriberId());
		return null;
	}

	@Override
	public ApiResponse<List<PaymentPurpose>> getAllPaymentPurposes() throws Exception {
		List<PaymentPurpose> result= paymentPurposeRepository.findAll();
		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, result, null, null);
		
	}
}
