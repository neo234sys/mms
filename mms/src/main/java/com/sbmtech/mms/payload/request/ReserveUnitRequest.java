package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidCountryId;
import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidPhoneNo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReserveUnitRequest {

	private Integer unitId;
	private Integer subscriberId;
	
	@NotEmpty(message = "firstName cannot be null")
	private String firstName;
	
	@ValidPhoneNo
	private String mobileNo;
	
	
	@Email
	private String email;
	private String emiratesId;
	
	@NotBlank (message = "dob cannot be null")
	@NotEmpty(message = "dob cannot be null")
	@ValidDateDDMMYYYY
	private String dob;
	
	
	@Min(value=1, message="1 for Male")
	@Max(value=2, message="2 for Female")
	private Integer gender;
	
	@NotBlank (message = "address cannot be null")
	@NotEmpty(message = "address cannot be null")
	private String address;
	
	private byte[] eidaCopy;
	
	@ValidCountryId
	private Integer nationalityId;

	
}
