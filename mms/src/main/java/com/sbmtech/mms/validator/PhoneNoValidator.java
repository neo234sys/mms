package com.sbmtech.mms.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sbmtech.mms.util.CommonUtil;

public class PhoneNoValidator implements ConstraintValidator<ValidPhoneNo, String> {

	@Override
	public void initialize(ValidPhoneNo constraintAnnotation) {

	}

	@Override
	public boolean isValid(String phoneNo, ConstraintValidatorContext constraintValidatorContext) {

		if (CommonUtil.getLongValofObject(phoneNo).longValue() == 0L) {
			return false;
		} else if (phoneNo.length() < 5) {
			return false;
		}
		return true;
	}

}