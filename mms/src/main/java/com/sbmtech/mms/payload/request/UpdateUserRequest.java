package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.sbmtech.mms.validator.ValidCountryId;
import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidPhoneNo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserRequest {
	

	@Email
	private String email;

	@ValidPhoneNo
	private String mobileNo;

	@NotBlank(message = "password cannot be null")
	@NotEmpty(message = "password cannot be null")
	private String password;

	@Pattern(regexp = "784[-]*\\d{4}[-]*\\d{7}[-]*\\d", message = "Invalid EmiratesId")
	private String emiratesId;

	@NotBlank(message = "dob cannot be null")
	@NotEmpty(message = "dob cannot be null")
	@ValidDateDDMMYYYY
	private String dob;

	// @NotEmpty(message = "password cannot be null")
	@Min(value = 1, message = "1 for Male")
	@Max(value = 2, message = "2 for Female")
	private Integer gender;

	@NotBlank(message = "address cannot be null")
	@NotEmpty(message = "address cannot be null")
	private String address;

	private byte[] eidaCopy;

	@ValidCountryId
	private Integer nationalityId;

	// @ValidSubscriberlId
	private Integer subscriberId;

	@NotEmpty(message = "firstName cannot be null")
	private String firstName;

	@NotEmpty(message = "lastName cannot be null")
	private String lastName;

	//private String phoneNumber;

	@NotBlank(message = "eidaExpiryDate cannot be null")
	@NotEmpty(message = "eidaExpiryDate cannot be null")
	@ValidDateDDMMYYYY
	private String eidaExpiryDate;

	private String passportNo;

	@NotBlank(message = "passportExpiryDate cannot be null")
	@NotEmpty(message = "passportExpiryDate cannot be null")
	@ValidDateDDMMYYYY
	private String passportExpiryDate;

	private byte[] passportCopy;
	private byte[] photo;



}