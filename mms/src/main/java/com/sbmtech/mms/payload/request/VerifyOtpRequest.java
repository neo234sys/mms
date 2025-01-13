package com.sbmtech.mms.payload.request;

public class VerifyOtpRequest {

	private Integer subscriberId;
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
