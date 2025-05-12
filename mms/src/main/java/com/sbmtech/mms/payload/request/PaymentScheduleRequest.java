package com.sbmtech.mms.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentScheduleRequest {
	
	private Integer subscriberId;

	private Integer tenantUnitId;
}
