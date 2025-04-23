package com.sbmtech.mms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public  class CreditCardDetailsDTO {
        private String ccName;
        private String ccNo;
        private String ccCvcNo;
        private String ccExpiry;
    }
