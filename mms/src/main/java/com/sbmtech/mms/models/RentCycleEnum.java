package com.sbmtech.mms.models;

public enum RentCycleEnum {
	MONTHLY(1),
	QUARTERLY(2),
	HALFLY(3), 
	YEARLY(4);

	
	
	private Integer value;
	
	private RentCycleEnum(Integer value){
		
		this.value = value;
	}
	
	public Integer getValue(){
		
		return this.value;
	}

}