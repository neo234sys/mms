package com.sbmtech.mms.service.impl;

import static com.sbmtech.mms.constant.CommonConstants.FAILURE_CODE;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_DESC;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_CODE;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_DESC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.dto.ChequeDetailsDTO;
import com.sbmtech.mms.models.PaymentPurpose;
import com.sbmtech.mms.models.TenantCCDetails;
import com.sbmtech.mms.models.TenantChequeDetails;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.OrderRequest;
import com.sbmtech.mms.payload.request.SavePaymentDetailsRequest;
import com.sbmtech.mms.repository.OrderRepository;
import com.sbmtech.mms.repository.PaymentPurposeRepository;
import com.sbmtech.mms.repository.TenantCCDetailsRepository;
import com.sbmtech.mms.repository.TenantChequeDetailsRepository;
import com.sbmtech.mms.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentPurposeRepository paymentPurposeRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private TenantCCDetailsRepository ccRepo;

	@Autowired
	private TenantChequeDetailsRepository chequeRepo;

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
	
	

	 public void savePaymentDetails(SavePaymentDetailsRequest request) {
	        Integer mode = request.getPaymentModeId();

	        if (mode == 1 && request.getCcDetails() != null) { // Credit Card
	            TenantCCDetails cc = new TenantCCDetails();
	            cc.setTenantId(request.getTenantId());
	            cc.setTenantUnitId(request.getTenantUnitId());
	            cc.setCcName(request.getCcDetails().getCcName());
	            cc.setCcNo(request.getCcDetails().getCcNo());
	            cc.setCcCvcNo(request.getCcDetails().getCcCvcNo());
	            cc.setCcExpiry(request.getCcDetails().getCcExpiry());
	            ccRepo.save(cc);

	        } else if (mode == 2 && request.getChequeDetails() != null) { // Cheque
	            for (ChequeDetailsDTO detail : request.getChequeDetails()) {
	                TenantChequeDetails cheque = new TenantChequeDetails();
	                cheque.setTenantId(request.getTenantId());
	                cheque.setTenantUnitId(request.getTenantUnitId());
	                cheque.setChequeNo(detail.getChequeNo());
	                cheque.setChequeBank(detail.getChequeBank());
	                cheque.setChequeAmount(detail.getChequeAmount());
	                cheque.setChequePic(detail.getChequePic());
	                cheque.setChequeDate(LocalDate.parse(detail.getChequeDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
	                cheque.setPaymentPurposeId(detail.getPaymentPurposeId());
	                chequeRepo.save(cheque);
	            }
	        } else {
	            throw new IllegalArgumentException("Invalid payment mode or missing details");
	        }
	    }
}
