package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidChannelId;
import com.sbmtech.mms.validator.ValidCurrentAndFutureDate;
import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidFutureDate;
import com.sbmtech.mms.validator.ValidPlanId;
import com.sbmtech.mms.validator.ValidSubscriberlId;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {

	//@ValidPlanId
	private Long userId;
	
	private Integer tenantId;
	
	private Integer tenantUnitId;
	
	private Integer purposeId;
	
	@ValidSubscriberlId
	private Integer subscriberId;
	
	private Double amount;
	
	private String paymentMethod;
		
	
	@ValidChannelId
	private Integer channelId;



}
