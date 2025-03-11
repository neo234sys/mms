package com.sbmtech.mms.models;

public enum UnitStatusEnum {
	VACANT(1),
	OCCUPIED(2),
	RESERVED(3), 
	UNDER_MAINTAINANCE(4);

	
	
	private Integer value;
	
	private UnitStatusEnum(Integer value){
		
		this.value = value;
	}
	
	public Integer getValue(){
		
		return this.value;
	}

}