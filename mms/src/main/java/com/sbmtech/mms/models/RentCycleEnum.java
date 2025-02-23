package com.sbmtech.mms.models;

public enum RentCycleEnum {
	MONTHLY("MONTHLY"),
	QUARTERLY("QUARTERLY"),
	HALFLY("HALFLY"), 
	YEARLY("YEARLY");

	
	
	private String value;
	
	private RentCycleEnum(String value){
		
		this.value = value;
	}
	
	public String getValue(){
		
		return this.value;
	}

}