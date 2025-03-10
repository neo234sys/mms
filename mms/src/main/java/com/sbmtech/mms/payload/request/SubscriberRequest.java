package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidChannelId;
import com.sbmtech.mms.validator.ValidCountryId;

public class SubscriberRequest {

	@NotEmpty(message = "subscriberName must not be empty and null")
	private String subscriberName;
	
	@Email(message = "Please enter a valid email Id")
	@NotEmpty(message = "Email cannot be null")
	private String companyEmail;
	
	@NotEmpty(message = "companyMobileNo cannot be null")
	private String companyMobileNo;
	
	@NotEmpty(message = "companyName cannot be null")
	private String companyName;

	@ValidChannelId
	private Integer channelId;

	@ValidCountryId
	private Integer natId;
	
	@NotEmpty(message = "password cannot be null")
	private String password;

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyMobileNo() {
		return companyMobileNo;
	}

	public void setCompanyMobileNo(String companyMobileNo) {
		this.companyMobileNo = companyMobileNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getNatId() {
		return natId;
	}

	public void setNatId(Integer natId) {
		this.natId = natId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
