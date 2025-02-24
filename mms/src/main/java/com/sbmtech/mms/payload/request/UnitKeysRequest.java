package com.sbmtech.mms.payload.request;

import java.util.List;

import javax.validation.constraints.Min;

import com.sbmtech.mms.validator.ValidSubscriberlId;

public class UnitKeysRequest {

	@Min(value=1, message="min value 1")
	private Integer unitId;
	
	@Min(value=1, message="min value 1")
	private Integer keyId;
	
	//@ValidSubscriberlId
	private Integer subscriberId;
	
	
	private List<Integer> keyIds;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getKeyId() {
		return keyId;
	}

	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}

	public Integer keyId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public List<Integer> getKeyIds() {
		return keyIds;
	}

	public void setKeyIds(List<Integer> keyIds) {
		this.keyIds = keyIds;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}
	
	

}
