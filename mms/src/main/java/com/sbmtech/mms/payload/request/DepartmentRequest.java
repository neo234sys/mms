package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;

public class DepartmentRequest {

	@NotEmpty(message = "departmentName must not be empty and null")
	private String departmentName;

	private Integer subscriberId;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	
}
