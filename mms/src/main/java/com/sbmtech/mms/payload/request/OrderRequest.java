package com.sbmtech.mms.payload.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.sbmtech.mms.validator.ValidChannelId;
import com.sbmtech.mms.validator.ValidSubscriberlId;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {


	private Long userId;
	
	private Integer tenantId;
	
	@NotNull(message = "paymentMode cannot be null")
	private Integer tenantUnitId;
	
	@NotNull(message = "rentDueId cannot be null")
	private List<Long> rentDueIds;
	
	//private Integer purposeId;
	
	
	private Integer subscriberId;
	
	@NotNull(message = "paymentMode cannot be null")
	private Integer paymentModeId;
	
	//private Double amount;
	
	//private String paymentMethod;
		
	
	//@ValidChannelId
	//private Integer channelId;



}
