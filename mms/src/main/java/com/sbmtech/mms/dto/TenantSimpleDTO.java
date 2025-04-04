package com.sbmtech.mms.dto;

import java.util.Date;
import java.util.List;

public class TenantSimpleDTO {

	private Integer tenantId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date eidaExpiryDate;
	private String passportNo;
	private Date passportExpiryDate;
	private String nationality;
	private List<TenureDetailsDTO> tenureDetails;

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getEidaExpiryDate() {
		return eidaExpiryDate;
	}

	public void setEidaExpiryDate(Date eidaExpiryDate) {
		this.eidaExpiryDate = eidaExpiryDate;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(Date passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<TenureDetailsDTO> getTenureDetails() {
		return tenureDetails;
	}

	public void setTenureDetails(List<TenureDetailsDTO> tenureDetails) {
		this.tenureDetails = tenureDetails;
	}

	@Override
	public String toString() {
		return "TenantSimpleDTO [tenantId=" + tenantId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", eidaExpiryDate=" + eidaExpiryDate
				+ ", passportNo=" + passportNo + ", passportExpiryDate=" + passportExpiryDate + ", nationality="
				+ nationality + ", tenureDetails=" + tenureDetails + "]";
	}

}
