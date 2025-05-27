package com.sbmtech.mms.payload.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.sbmtech.mms.validator.ValidChannelId;
import com.sbmtech.mms.validator.ValidSubscriberlId;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRentRequest {


	private Long userId;
	
	private Integer tenantId;
	
	@NotNull(message = "tenantUnitId cannot be null")
	private Integer tenantUnitId;
	
	@NotNull(message = "rentDueId cannot be null")
	private List<Long> rentDueIds;
	
	//@NotNull(message = "purposeId cannot be null")
	//private Integer purposeId;
	
	
	private Integer subscriberId;
	
	//@NotNull(message = "paymentMode cannot be null")
	//private Integer paymentModeId;
	
	
	//private Integer unitReserveId;
	
	//private Double amount;
	
	//private String paymentMethod;
		
	
	//@ValidChannelId
	//private Integer channelId;



}
