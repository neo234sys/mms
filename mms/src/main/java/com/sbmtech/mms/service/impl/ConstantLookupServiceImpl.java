package com.sbmtech.mms.service.impl;

import static com.sbmtech.mms.constant.CommonConstants.FAILURE_CODE;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_DESC;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_CODE;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_DESC;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.dto.KeyValuePairDTO;
import com.sbmtech.mms.exception.BusinessException;
import com.sbmtech.mms.models.ParkingTypeMaster;
import com.sbmtech.mms.models.PaymentMode;
import com.sbmtech.mms.models.PaymentPurpose;
import com.sbmtech.mms.models.RentCycle;
import com.sbmtech.mms.models.UnitStatus;
import com.sbmtech.mms.models.UnitSubType;
import com.sbmtech.mms.models.UnitType;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.repository.ParkingTypeMasterRepository;
import com.sbmtech.mms.repository.PaymentModeRepository;
import com.sbmtech.mms.repository.PaymentPurposeRepository;
import com.sbmtech.mms.repository.RentCycleRepository;
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

	@Autowired
	PaymentModeRepository paymentModeRepository;

	@Autowired
	RentCycleRepository rentCycleRepository;

	@Autowired
	ParkingTypeMasterRepository parkingTypeMasterRepository;
	
	@Autowired
	PaymentPurposeRepository paymentPurposeRepository;

	@Override
	public ApiResponse<Object> getUnitTypeLookup() throws Exception {

		List<UnitType> result = unitTypeRepository.findAll();

		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		List<KeyValuePairDTO> listDto = result.stream().map(p -> {
			KeyValuePairDTO kv = new KeyValuePairDTO();
			kv.setKey(p.getUnitTypeId());
			kv.setValue(p.getUnitTypeName());
			return kv;
		}).collect(Collectors.toList());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, listDto, null, null);
	}

	@Override
	public ApiResponse<Object> getUnitSubtypeLookup(Integer unitTypId) throws Exception {
		List<UnitSubType> unitSubTypeList = UnitSubTypeRepository.findByUnitTypeUnitTypeId(unitTypId);
		List<KeyValuePairDTO> responses = new ArrayList<>();

		if (unitSubTypeList.isEmpty()) {
			throw new BusinessException("Invalid unitTypId", null);
		}

		for (UnitSubType unitSubType : unitSubTypeList) {

			KeyValuePairDTO unitSubTypeResponse = new KeyValuePairDTO();
			unitSubTypeResponse.setKey(unitSubType.getUnitSubtypeId());
			unitSubTypeResponse.setValue(unitSubType.getUnitSubtypeName());

			responses.add(unitSubTypeResponse);
		}

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, responses, null, null);

	}

	@Override
	public ApiResponse<Object> getUnitStatusLookup() throws Exception {
		List<UnitStatus> result = unitStatusRepository.findAll();

		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		List<KeyValuePairDTO> listDto = result.stream().map(p -> {
			KeyValuePairDTO kv = new KeyValuePairDTO();
			kv.setKey(p.getUnitStatusId());
			kv.setValue(p.getUnitStatusName());
			return kv;
		}).collect(Collectors.toList());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, listDto, null, null);
	}

	@Override
	public ApiResponse<Object> getPaymentModeLookup() throws Exception {
		List<PaymentMode> result = paymentModeRepository.findAll();

		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		List<KeyValuePairDTO> listDto = result.stream().map(p -> {
			KeyValuePairDTO kv = new KeyValuePairDTO();
			kv.setKey(p.getPaymentModeId());
			kv.setValue(p.getPaymentModeName());
			return kv;
		}).collect(Collectors.toList());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, listDto, null, null);
	}

	@Override
	public ApiResponse<Object> getRentCycleLookup() throws Exception {
		List<RentCycle> result = rentCycleRepository.findAll();

		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		List<KeyValuePairDTO> listDto = result.stream().map(p -> {
			KeyValuePairDTO kv = new KeyValuePairDTO();
			kv.setKey(p.getRentCycleId());
			kv.setValue(p.getRentCycleName());
			return kv;
		}).collect(Collectors.toList());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, listDto, null, null);
	}

	@Override
	public ApiResponse<Object> getParkingTypeLookup() throws Exception {
		List<ParkingTypeMaster> result = parkingTypeMasterRepository.findAll();

		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
//		List<KeyValuePairDTO> listDto = result.stream()
//                .map(p->{
//                	KeyValuePairDTO kv=new KeyValuePairDTO();
//                	kv.setKey(p.getRentCycleId());
//                	kv.setValue(p.getRentCycleName());
//                	return kv;
//                }).collect(Collectors.toList());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, result, null, null);
	}
	
	@Override
	public ApiResponse<Object> getPaymentPurposeLookup() throws Exception {

		List<PaymentPurpose> result = paymentPurposeRepository.findAll();

		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		List<KeyValuePairDTO> listDto = result.stream().map(p -> {
			KeyValuePairDTO kv = new KeyValuePairDTO();
			kv.setKey(p.getPurposeId());
			kv.setValue(p.getPurposeName());
			return kv;
		}).collect(Collectors.toList());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, listDto, null, null);
	}
}
