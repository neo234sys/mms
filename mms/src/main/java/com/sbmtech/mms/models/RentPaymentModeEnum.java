package com.sbmtech.mms.models;

public enum RentPaymentModeEnum {
	CREDIT_CARD("CREDIT_CARD"),
	BANK_TRANSFER("BANK_TRANSFER"), 
	CASH("CASH"),
	CHEQUE("CHEQUE");
	
	
	
	private String value;
	
	private RentPaymentModeEnum(String value){
		
		this.value = value;
	}
	
	public String getValue(){
		
		return this.value;
	}

	
}