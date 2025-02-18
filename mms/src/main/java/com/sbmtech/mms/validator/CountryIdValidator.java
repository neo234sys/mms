package com.sbmtech.mms.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sbmtech.mms.repository.CountriesRepository;

public class CountryIdValidator implements ConstraintValidator<ValidCountryId, Integer> {



	@Autowired
	private CountriesRepository countriesRepository;

    
    @Override
    public void initialize(ValidCountryId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer  natId, ConstraintValidatorContext constraintValidatorContext) {
    	boolean countryExists=false;
    	
    	if(natId!=null) {
    		countryExists = countriesRepository.existsById(natId);
    	}
    	
        return  countryExists;
    }
}