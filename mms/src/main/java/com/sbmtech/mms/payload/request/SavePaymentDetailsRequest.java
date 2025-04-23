package com.sbmtech.mms.payload.request;

import java.util.List;

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
    private List<ChequeDetailsDTO> chequeDetails;
}