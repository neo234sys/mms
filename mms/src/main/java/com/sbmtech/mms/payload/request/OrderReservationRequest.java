package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderReservationRequest {


	private Long userId;
	
	private Integer tenantId;
	
	//@NotNull(message = "tenantUnitId cannot be null")
	//private Integer tenantUnitId;
	
	//@NotNull(message = "rentDueId cannot be null")
	//private List<Long> rentDueIds;
	
	@NotNull(message = "purposeId cannot be null")
	private Integer purposeId;
	
	
	private Integer subscriberId;
	
	@NotNull(message = "paymentMode cannot be null")
	private Integer paymentModeId;
	
	@NotNull(message = "unitReserveId cannot be null")
	private Integer unitReserveId;
	
	//private Double amount;
	
	//private String paymentMethod;
		
	
	//@ValidChannelId
	//private Integer channelId;



}
