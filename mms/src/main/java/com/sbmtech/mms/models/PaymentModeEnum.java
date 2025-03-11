package com.sbmtech.mms.models;

public enum PaymentModeEnum {
	CREDIT_CARD(1),
	BANK_TRANSFER(2), 
	CASH(3),
	CHEQUE(4);
	
	
	
	private Integer value;
	
	private PaymentModeEnum(Integer value){
		
		this.value = value;
	}
	
	public Integer getValue(){
		
		return this.value;
	}

	
}