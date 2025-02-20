package com.sbmtech.mms.models;

public enum UserTypeEnum {
	
	
	ADMIN(1),SUBSCRIBER(2),EMPLOYEE(3),TENANT(4);
	
	private Integer value;
	
	private UserTypeEnum(Integer value){
		
		this.value = value;
	}
	
	public Integer getValue(){
		
		return this.value;
	}
	
	
}