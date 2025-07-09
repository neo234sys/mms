package com.sbmtech.mms.models;

public enum RoleEnum {
	
	
	ROLE_ADMIN("ROLE_ADMIN",1),
	ROLE_MGT_ADMIN("ROLE_MGT_ADMIN",2),
	ROLE_MGT_MANAGER("ROLE_MGT_MANAGER",3),
	ROLE_MGT_SUPERVISOR("ROLE_MGT_SUPERVISOR",4),
	ROLE_MGT_SECURITY("ROLE_MGT_SECURITY",5),
	ROLE_MGT_ELECTRICIAN("ROLE_MGT_ELECTRICIAN",6),
	ROLE_MGT_PLUMBER("ROLE_MGT_PLUMBER",7),
	ROLE_TENANT("ROLE_TENANT",8),
	
	ROLE_BS_MGT_ADMIN("ROLE_BS_MGT_ADMIN",9);
	
	private String name;
	private Integer value;
	
	private RoleEnum(String name,Integer value){
		this.name = name;
		this.value = value;
	}
	
	public Integer getValue(){
		
		return this.value;
	}

	public String getName() {
		return name;
	}

	
	
	
}