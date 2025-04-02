package com.sbmtech.mms.payload.response;

import java.util.Arrays;
import java.util.Date;

public class TenureDetailsResponse {

	private Integer tenantTenureId;
	private Date tenancyStartDate;
	private Date tenancyEndDate;
	private byte[] tenancyCopy;
	private Date createdTime;
	private Date updatedTime;
	private Integer createdBy;
	private Integer updatedBy;

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

	public byte[] getTenancyCopy() {
		return tenancyCopy;
	}

	public void setTenancyCopy(byte[] tenancyCopy) {
		this.tenancyCopy = tenancyCopy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "TenureDetailsResponse [tenantTenureId=" + tenantTenureId + ", tenancyStartDate=" + tenancyStartDate
				+ ", tenancyEndDate=" + tenancyEndDate + ", tenancyCopy=" + Arrays.toString(tenancyCopy)
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}

}
