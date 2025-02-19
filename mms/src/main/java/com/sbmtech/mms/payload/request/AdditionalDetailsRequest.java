package com.sbmtech.mms.payload.request;

import com.sbmtech.mms.validator.ValidSubscriberlId;

public class AdditionalDetailsRequest {


	@ValidSubscriberlId
	private Integer subscriberId;
	
	private String companyAddress;
	private String companyContactName;
	private String companyLandlineNo;
	private String companyTradeLicense;
	private byte[] companyTradeLicenseCopy;
	private byte[] companyLogo;

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyContactName() {
		return companyContactName;
	}

	public void setCompanyContactName(String companyContactName) {
		this.companyContactName = companyContactName;
	}

	public String getCompanyLandlineNo() {
		return companyLandlineNo;
	}

	public void setCompanyLandlineNo(String companyLandlineNo) {
		this.companyLandlineNo = companyLandlineNo;
	}

	public String getCompanyTradeLicense() {
		return companyTradeLicense;
	}

	public void setCompanyTradeLicense(String companyTradeLicense) {
		this.companyTradeLicense = companyTradeLicense;
	}

	public byte[] getCompanyTradeLicenseCopy() {
		return companyTradeLicenseCopy;
	}

	public void setCompanyTradeLicenseCopy(byte[] companyTradeLicenseCopy) {
		this.companyTradeLicenseCopy = companyTradeLicenseCopy;
	}

	public byte[] getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(byte[] companyLogo) {
		this.companyLogo = companyLogo;
	}

}
