package com.sbmtech.mms.service;

import java.util.List;

import com.sbmtech.mms.models.PaymentMode;
import com.sbmtech.mms.models.RentCycle;
import com.sbmtech.mms.models.UnitStatus;
import com.sbmtech.mms.models.UnitType;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.response.UnitSubTypeResponse;

public interface ConstantLookupService {

	public ApiResponse<List<UnitType>>  getUnitTypeLookup()throws Exception;

	public ApiResponse<List<UnitSubTypeResponse>> getUnitSubtypeLookup(Integer unitTypId)throws Exception;

	public  ApiResponse<List<UnitStatus>> getUnitStatusLookup()throws Exception;

	public ApiResponse<List<PaymentMode>> getPaymentModeLookup()throws Exception;

	public ApiResponse<List<RentCycle>> getRentCycleLookup()throws Exception;

	
}
