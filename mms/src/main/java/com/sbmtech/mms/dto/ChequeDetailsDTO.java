package com.sbmtech.mms.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidFutureDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChequeDetailsDTO {

	@NotBlank(message = "Cheque number is required")
	private String chequeNo;

	@NotBlank(message = "Bank name is required")
	private String chequeBank;

	@NotNull(message = "Cheque amount is required")
	@DecimalMin(value = "0.01", message = "Amount must be greater than 0")
	private Double chequeAmount;

	private  byte[] chequePic;

	@NotNull(message = "Cheque date is required")
	//@FutureOrPresent(message = "Cheque date must be today or in the future")
	@ValidFutureDate
	@ValidDateDDMMYYYY
	private String chequeDate; // Consider converting to LocalDate

	private Integer paymentPurposeId;
}