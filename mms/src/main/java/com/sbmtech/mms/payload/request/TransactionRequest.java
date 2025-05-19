package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequest {



	
	@NotNull(message = "orderId cannot be null")
	private Long orderId;
	
	private Integer subscriberId;
	
	



}
