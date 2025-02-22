package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.sbmtech.mms.util.CommonUtil;
import com.sbmtech.mms.validator.ValidCountryId;
import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidPhoneNo;
import com.sbmtech.mms.validator.ValidSubscriberlId;

public class CreateUserRequest {

	@Email
	private String email;
	
	@ValidPhoneNo
	private String mobileNo;
	
	@NotBlank (message = "password cannot be null")
	@NotEmpty(message = "password cannot be null")
	private String password;
	
	@Pattern(regexp="784[-]*\\d{4}[-]*\\d{7}[-]*\\d",message="Invalid EmiratesId")  
	private String emiratesId;
	

	@NotBlank (message = "dob cannot be null")
	@NotEmpty(message = "dob cannot be null")
	@ValidDateDDMMYYYY
	private String dob;
	
	
	//@NotEmpty(message = "password cannot be null")
	@Min(value=1, message="1 for Male")
	@Max(value=2, message="2 for Female")
	private Integer gender;
	
	@NotBlank (message = "password cannot be null")
	@NotEmpty(message = "password cannot be null")
	private String address;
	
	
	private byte[] eidaCopy;
	
	@ValidCountryId
	private Integer nationalityId;
	
	@ValidSubscriberlId
	private Integer subscriberId;
	
	
	
	@NotEmpty(message = "firstName cannot be null")
	private String firstName;
	
	
	@NotEmpty(message = "lastName cannot be null")
	private String lastName;
	
	
	private String phoneNumber;
	

	@NotBlank (message = "eidaExpiryDate cannot be null")
	@NotEmpty(message = "eidaExpiryDate cannot be null")
	@ValidDateDDMMYYYY
	private String eidaExpiryDate;
	
	private String passportNo;
	
	@NotBlank (message = "passportExpiryDate cannot be null")
	@NotEmpty(message = "passportExpiryDate cannot be null")
	@ValidDateDDMMYYYY
	private String passportExpiryDate;
	
	
	private byte[] passportCopy;
	private byte[] photo;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmiratesId() {
		emiratesId=emiratesId.replace("-", "");
		
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

	public Integer getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEidaExpiryDate() {
		return eidaExpiryDate;
	}

	public void setEidaExpiryDate(String eidaExpiryDate) {
		this.eidaExpiryDate = eidaExpiryDate;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(String passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public byte[] getPassportCopy() {
		return passportCopy;
	}

	public void setPassportCopy(byte[] passportCopy) {
		this.passportCopy = passportCopy;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}