package com.sbmtech.mms.payload.request;

import java.util.Arrays;

public class ReserveUnitRequest {

	private Integer unitId;
	private Long mobileNo;
	private Integer paymentRequired;
	private String email;
	private String emiratesId;
	private String dob;
	private Integer gender;
	private String address;
	private byte[] eidaCopy;
	private String nationality;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getPaymentRequired() {
		return paymentRequired;
	}

	public void setPaymentRequired(Integer paymentRequired) {
		this.paymentRequired = paymentRequired;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmiratesId() {
		return emiratesId;
	}

	public void setEmiratesId(String emiratesId) {
		this.emiratesId = emiratesId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getEidaCopy() {
		return eidaCopy;
	}

	public void setEidaCopy(byte[] eidaCopy) {
		this.eidaCopy = eidaCopy;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "ReserveUnitRequest [unitId=" + unitId + ", mobileNo=" + mobileNo + ", paymentRequired="
				+ paymentRequired + ", email=" + email + ", emiratesId=" + emiratesId + ", dob=" + dob + ", gender="
				+ gender + ", address=" + address + ", eidaCopy=" + Arrays.toString(eidaCopy) + ", nationality="
				+ nationality + "]";
	}

}
