package com.sbmtech.mms.payload.request;

public class TenantIdRequest {

	private Integer tenantId;

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return "TenantIdRequest [tenantId=" + tenantId + "]";
	}

}
