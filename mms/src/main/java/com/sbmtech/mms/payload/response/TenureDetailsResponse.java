package com.sbmtech.mms.payload.response;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class TenureDetailsResponse {

	private Integer tenantTenureId;
	private Date tenancyStartDate;
	private Date tenancyEndDate;
	private byte[] tenancyCopy;
	private Date createdTime;
	private Date updatedTime;
	private Integer createdBy;
	private Integer updatedBy;

	
	@Override
	public String toString() {
		return "TenureDetailsResponse [tenantTenureId=" + tenantTenureId + ", tenancyStartDate=" + tenancyStartDate
				+ ", tenancyEndDate=" + tenancyEndDate + ", tenancyCopy=" + Arrays.toString(tenancyCopy)
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}

}
