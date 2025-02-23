package com.sbmtech.mms.payload.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UniKeyResponse {

	
	private Integer unitKeysId;
	
	private List<Integer> keyIds;

	public List<Integer> getKeyIds() {
		return keyIds;
	}

	public void setKeyIds(List<Integer> keyIds) {
		this.keyIds = keyIds;
	}

	public Integer getUnitKeysId() {
		return unitKeysId;
	}

	public void setUnitKeysId(Integer unitKeysId) {
		this.unitKeysId = unitKeysId;
	}

	

	
	

	


	
	
}
