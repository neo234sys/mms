package com.sbmtech.mms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TenantUnitDTO {

	private Integer tenantUnitId;
	private Integer unitId;
	private Integer tenurePeriodMonth;
	private Boolean expired;
	private Boolean active;
	private String tenancyStartDate;
	private String tenancyEndDate;
}
