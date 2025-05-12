package com.sbmtech.mms.payload.request;

import java.util.List;

import javax.validation.Valid;

import com.sbmtech.mms.dto.ChequeDetailsDTO;
import com.sbmtech.mms.dto.CreditCardDetailsDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SavePaymentDetailsRequest {
	
	private Integer subscriberId;
	
	
    private Integer tenantUnitId;
	
	
    private Integer tenantId;
	
	
	private Integer paymentModeId;

    private CreditCardDetailsDTO ccDetails;
    
    @Valid
    private List<ChequeDetailsDTO> chequeDetails;
}