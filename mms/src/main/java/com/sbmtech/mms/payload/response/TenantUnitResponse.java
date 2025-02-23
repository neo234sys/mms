package com.sbmtech.mms.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TenantUnitResponse {

	
	private Integer TenantUnitId;

	public Integer getTenantUnitId() {
		return TenantUnitId;
	}

	public void setTenantUnitId(Integer tenantUnitId) {
		TenantUnitId = tenantUnitId;
	}
	
	
	

	
	

	


	
	
}
