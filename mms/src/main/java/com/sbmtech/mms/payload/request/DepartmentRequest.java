package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;

public class DepartmentRequest {

	@NotEmpty(message = "departmentName must not be empty and null")
	private String deptName;

	private Integer subscriberId;

	

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	
}
