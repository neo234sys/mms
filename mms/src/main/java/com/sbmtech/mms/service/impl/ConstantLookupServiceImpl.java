package com.sbmtech.mms.service.impl;

import static com.sbmtech.mms.constant.CommonConstants.FAILURE_CODE;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_DESC;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_CODE;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_DESC;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.exception.BusinessException;
import com.sbmtech.mms.models.UnitStatus;
import com.sbmtech.mms.models.UnitSubType;
import com.sbmtech.mms.models.UnitType;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.response.UnitSubTypeResponse;
import com.sbmtech.mms.repository.UnitStatusRepository;
import com.sbmtech.mms.repository.UnitSubTypeRepository;
import com.sbmtech.mms.repository.UnitTypeRepository;
import com.sbmtech.mms.service.ConstantLookupService;

@Service
public class ConstantLookupServiceImpl implements ConstantLookupService {
	
	
	
	
	@Autowired
	UnitTypeRepository unitTypeRepository;
	
	@Autowired
	UnitSubTypeRepository UnitSubTypeRepository;
	
	@Autowired
	UnitStatusRepository unitStatusRepository;
	
	@Override
	public  ApiResponse<List<UnitType>>  getUnitTypeLookup()throws Exception {
	
		List <UnitType> result= unitTypeRepository.findAll();

		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, result, null, null);
	}

	@Override
	public ApiResponse<List<UnitSubTypeResponse>> getUnitSubtypeLookup(Integer unitTypId) throws Exception {
		List<UnitSubType> unitSubTypeList = UnitSubTypeRepository.findByUnitTypeUnitTypeId(unitTypId);
		List<UnitSubTypeResponse> responses = new ArrayList<>();

		if (unitSubTypeList.isEmpty()) {
			throw new BusinessException("Invalid unitTypId", null);
		}

		for (UnitSubType unitSubType : unitSubTypeList) {

			UnitSubTypeResponse unitSubTypeResponse = new UnitSubTypeResponse();
			unitSubTypeResponse.setUnitSubtypeId(unitSubType.getUnitSubtypeId());
			unitSubTypeResponse.setUnitSubtypeName(unitSubType.getUnitSubtypeName());

			responses.add(unitSubTypeResponse);
		}

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, responses, null, null);
		
	}

	@Override
	public ApiResponse<List<UnitStatus>> getUnitStatusLookup() throws Exception {
		List <UnitStatus> result= unitStatusRepository.findAll();

		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, result, null, null);
	}

}
