package com.sbmtech.mms.service;

import com.sbmtech.mms.payload.request.ApiResponse;

public interface ConstantLookupService {

	public ApiResponse<Object>  getUnitTypeLookup()throws Exception;
	
	public ApiResponse<Object> getUnitSubtypeLookup(Integer unitTypId)throws Exception;

	public  ApiResponse<Object> getUnitStatusLookup()throws Exception;

	public ApiResponse<Object> getPaymentModeLookup()throws Exception;

	public ApiResponse<Object> getRentCycleLookup()throws Exception;

	public ApiResponse<Object> getParkingTypeLookup() throws Exception ;
	
	public ApiResponse<Object> getPaymentPurposeLookup() throws Exception ;

	
}
