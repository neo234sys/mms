package com.sbmtech.mms.payload.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TenantDetailResponse {

	private Integer tenantId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date dateOfBirth;
	private Long emiratesId;
	private Date eidaExpiryDate;
	private String passportNo;
	private Date passportExpiryDate;
	private Integer nationalityId;
	private Integer unitId;
	private Integer buildingId;
	private Integer parkingId;

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getEmiratesId() {
		return emiratesId;
	}

	public void setEmiratesId(Long emiratesId) {
		this.emiratesId = emiratesId;
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

	public Integer getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	@Override
	public String toString() {
		return "TenantDetailResponse [tenantId=" + tenantId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", emiratesId="
				+ emiratesId + ", eidaExpiryDate=" + eidaExpiryDate + ", passportNo=" + passportNo
				+ ", passportExpiryDate=" + passportExpiryDate + ", nationalityId=" + nationalityId + ", unitId="
				+ unitId + ", buildingId=" + buildingId + ", parkingId=" + parkingId + "]";
	}

}