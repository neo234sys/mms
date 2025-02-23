package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidSubscriberlId;

public class KeyMasterRequest {

	@NotEmpty(message = "keyName cannot be null")
	private String keyName;
	
	@ValidSubscriberlId
	private Integer subscriberId;

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

}
