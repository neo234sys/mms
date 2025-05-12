package com.sbmtech.mms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreditCardDetailsDTO {
	@NotBlank(message = "Cardholder name is required")
	private String ccName;

	@NotBlank(message = "Card number is required")
	@Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
	private String ccNo;

	@NotBlank(message = "CVV is required")
	@Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
	private String ccCvcNo;

	@NotBlank(message = "Expiry date is required")
	@Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Expiry date must be in MM/YY format")

	private String ccExpiry;
}
