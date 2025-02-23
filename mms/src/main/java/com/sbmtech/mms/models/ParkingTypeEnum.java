package com.sbmtech.mms.models;

public enum ParkingTypeEnum {
	COVERED("COVERED"), OPEN("OPEN"), GARAGE("GARAGE");
	
	private String value;
	
	private ParkingTypeEnum(String value){
		
		this.value = value;
	}
	
	public String getValue(){
		
		return this.value;
	}
}
