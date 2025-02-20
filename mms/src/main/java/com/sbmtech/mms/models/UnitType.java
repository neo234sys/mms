package com.sbmtech.mms.models;

public enum UnitType {
	
	
	APARTMENT("APARTMENT"),VILLA("VILLA"),COMMERCIAL("COMMERCIAL");
	
	private String value;
	
	private UnitType(String value){
		
		this.value = value;
	}
	
	public String getValue(){
		
		return this.value;
	}
	
	
}