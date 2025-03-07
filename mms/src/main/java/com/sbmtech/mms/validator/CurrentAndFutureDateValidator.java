package com.sbmtech.mms.validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.sbmtech.mms.constant.CommonConstants;
import com.sbmtech.mms.util.CommonUtil;

public class CurrentAndFutureDateValidator implements ConstraintValidator<ValidCurrentAndFutureDate, String> {

    private Boolean isOptional;

    @Override
    public void initialize(ValidCurrentAndFutureDate validDate) {
        this.isOptional = validDate.optional();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        boolean futureDate = isFutureDate("dd/MM/yyyy", value);

        return isOptional ? (futureDate || (StringUtils.isNotEmpty(value))) : futureDate;
    }

    private static boolean isFutureDate(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (value != null){
               date = sdf.parse(value);
               LocalDate today = LocalDate.now(CommonUtil.ZONE_DUBAI);
               today= today.minusDays(1);
               LocalDate ld= CommonUtil.getLocalDatefromString(value, CommonConstants.DATE_ddMMyyyy);
               return ld.isAfter(today);
               
            }

        } catch (ParseException ex) {
        }
        return false;
    }
}