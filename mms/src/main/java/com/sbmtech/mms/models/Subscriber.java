package com.sbmtech.mms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "subscriber")
public class Subscriber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscriber_id")
	private Integer subscriberId;

	@Column(name = "subscriber_name")
	private String subscriberName;

	@Column(name = "company_address")
	private String companyAddress;

	@Column(name = "companay_contact_name")
	private String companyContactName;

	@Column(name = "company_email")
	private String companyEmail;

	@Column(name = "company_landline_no")
	private String companyLandlineNo;

	@Column(name = "company_mobile_no")
	private String companyMobileNo;

	@Column(name = "companay_name")
	private String companyName;

	@Column(name = "company_tradelicense")
	private String companyTradeLincense;

	@Lob
	@Column(name = "company_tradelicense_copy")
	private byte[] companyTradecopy;

	@Lob
	@Column(name = "company_logo")
	private byte[] companyLogo;

	@Column(name = "channel_id")
	private Integer channelId;

	@Column(name = "otp_verified")
	private Integer otpVerified;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "active")
	private Integer active;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_date")
	private String updatedDate;

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
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

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyLandlineNo() {
		return companyLandlineNo;
	}

	public void setCompanyLandlineNo(String companyLandlineNo) {
		this.companyLandlineNo = companyLandlineNo;
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

	public String getCompanyTradeLincense() {
		return companyTradeLincense;
	}

	public void setCompanyTradeLincense(String companyTradeLincense) {
		this.companyTradeLincense = companyTradeLincense;
	}

	public byte[] getCompanyTradecopy() {
		return companyTradecopy;
	}

	public void setCompanyTradecopy(byte[] companyTradecopy) {
		this.companyTradecopy = companyTradecopy;
	}

	public byte[] getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(byte[] companyLogo) {
		this.companyLogo = companyLogo;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getOtpVerified() {
		return otpVerified;
	}

	public void setOtpVerified(Integer otpVerified) {
		this.otpVerified = otpVerified;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
