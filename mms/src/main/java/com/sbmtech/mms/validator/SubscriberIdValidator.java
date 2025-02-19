package com.sbmtech.mms.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.SubscriptionPlanMasterRepository;

public class SubscriberIdValidator implements ConstraintValidator<ValidSubscriberlId, Integer> {



	@Autowired
	private SubscriberRepository subscriberRepository;
    
    @Override
    public void initialize(ValidSubscriberlId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer  subscriberlId, ConstraintValidatorContext constraintValidatorContext) {
    	boolean subscriberlIdExists=false;
    	
    	if(subscriberlId!=null) {
    		subscriberlIdExists=subscriberRepository.existsBySubscriberIdAndActive(subscriberlId,1);
    	}
    	
        return  subscriberlIdExists;
    }
}