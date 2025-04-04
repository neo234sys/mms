package com.sbmtech.mms.dto;

import java.util.Date;

public class TenureDetailsDTO {

	private Integer tenantTenureId;
	private Date tenancyStartDate;
	private Date tenancyEndDate;
	private String tenancyCopyUrl;

	public Integer getTenantTenureId() {
		return tenantTenureId;
	}

	public void setTenantTenureId(Integer tenantTenureId) {
		this.tenantTenureId = tenantTenureId;
	}

	public Date getTenancyStartDate() {
		return tenancyStartDate;
	}

	public void setTenancyStartDate(Date tenancyStartDate) {
		this.tenancyStartDate = tenancyStartDate;
	}

	public Date getTenancyEndDate() {
		return tenancyEndDate;
	}

	public void setTenancyEndDate(Date tenancyEndDate) {
		this.tenancyEndDate = tenancyEndDate;
	}

	public String getTenancyCopyUrl() {
		return tenancyCopyUrl;
	}

	public void setTenancyCopyUrl(String tenancyCopyUrl) {
		this.tenancyCopyUrl = tenancyCopyUrl;
	}

	@Override
	public String toString() {
		return "TenureDetailsDTO [tenantTenureId=" + tenantTenureId + ", tenancyStartDate=" + tenancyStartDate
				+ ", tenancyEndDate=" + tenancyEndDate + ", tenancyCopyUrl=" + tenancyCopyUrl + "]";
	}

}
