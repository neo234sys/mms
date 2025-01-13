package com.sbmtech.mms.payload.request;

public class SubscriberRequest {

	private String subscriberName;
	private String companyEmail;
	private String companyMobileNo;
	private String companyName;
	private Integer channelId;
	private Integer natId;
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
