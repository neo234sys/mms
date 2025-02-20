package com.sbmtech.mms.models;

public enum UnitTypeEnum {
	
	
	APARTMENT("APARTMENT"),VILLA("VILLA"),COMMERCIAL("COMMERCIAL");
	
	private String value;
	
	private UnitTypeEnum(String value){
		
		this.value = value;
	}
	
	public String getValue(){
		
		return this.value;
	}
	
	
}