package com.sbmtech.mms.models;

public enum UnitStatusEnum {
	VACANT("VACANT"),
	OCCUPIED("OCCUPIED"),
	RESERVED("RESERVED"), 
	UNDER_MAINTAINANCE("UNDER_MAINTAINANCE");

	
	
	private String value;
	
	private UnitStatusEnum(String value){
		
		this.value = value;
	}
	
	public String getValue(){
		
		return this.value;
	}

}