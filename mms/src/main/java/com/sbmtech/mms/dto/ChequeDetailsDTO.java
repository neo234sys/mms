package com.sbmtech.mms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChequeDetailsDTO {
	
        private String chequeNo;
        private String chequeBank;
        private Double chequeAmount;
        private String chequePic;
        private String chequeDate; // Consider converting to LocalDate
        private Integer paymentPurposeId;
    }