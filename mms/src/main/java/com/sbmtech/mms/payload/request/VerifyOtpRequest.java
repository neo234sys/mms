package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Min;

public class VerifyOtpRequest {

	
	//@Positive
	//@Digits(integer=5, fraction=0,message = "subscriberId cannot be null" )
	@Min(value=1, message="subscriberId can not be 0")
	private Integer subscriberId;
	
	
	@Min(value=1, message="otpCode can not be 0")
	private Long otpCode;

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Long getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(Long otpCode) {
		this.otpCode = otpCode;
	}

}
