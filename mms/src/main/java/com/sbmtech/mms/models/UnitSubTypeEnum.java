package com.sbmtech.mms.models;

public enum UnitSubTypeEnum {
	
	
	STUDIO("STUDIO"),_1BHK1("1BHK"),_2BHK2("2BHK"),_3BHK3("3BHK");
	
	private String value;
	
	private UnitSubTypeEnum(String value){
		
		this.value = value;
	}
	
	public String getValue(){
		
		return this.value;
	}
}
