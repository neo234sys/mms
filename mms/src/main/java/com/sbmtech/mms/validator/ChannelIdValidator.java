package com.sbmtech.mms.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sbmtech.mms.repository.ChannelMasterRepository;

public class ChannelIdValidator implements ConstraintValidator<ValidChannelId, Integer> {



    @Autowired
	private ChannelMasterRepository channelMasterRepository;
    
    @Override
    public void initialize(ValidChannelId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer  channelId, ConstraintValidatorContext constraintValidatorContext) {
    	boolean channelExists=false;
    	
    	if(channelId!=null) {
    		channelExists = channelMasterRepository.existsById(channelId);
    	}
    	
        return  channelExists;
    }
}