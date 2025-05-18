package com.sbmtech.mms.payload.request;

import java.util.List;

import com.sbmtech.mms.dto.RentDue;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentModeRequest {
	
	private Integer subscriberId;

	private Integer tenureId;
	
	private List<RentDuePaymentModeRequest> rentDueDetails;
	
	
}
