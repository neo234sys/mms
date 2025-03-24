package com.sbmtech.mms.payload.request;

import com.sbmtech.mms.validator.ValidChannelId;
import com.sbmtech.mms.validator.ValidSubscriberlId;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {


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
