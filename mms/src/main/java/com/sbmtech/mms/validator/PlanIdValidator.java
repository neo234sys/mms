package com.sbmtech.mms.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sbmtech.mms.repository.SubscriptionPlanMasterRepository;

public class PlanIdValidator implements ConstraintValidator<ValidPlanId, Integer> {



    @Autowired
    private SubscriptionPlanMasterRepository planMasterRepository;
    
    @Override
    public void initialize(ValidPlanId constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer  planId, ConstraintValidatorContext constraintValidatorContext) {
    	boolean planExists=false;
    	
    	if(planId!=null) {
    		planExists = planMasterRepository.existsByPlanIdAndActive(planId,1);
    		
    	}
    	
        return  planExists;
    }
}