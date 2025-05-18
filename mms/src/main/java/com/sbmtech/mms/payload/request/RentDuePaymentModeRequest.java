package com.sbmtech.mms.payload.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.dto.RentDueEntryDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentDuePaymentModeRequest {

	Integer tenureId;
	
	Integer subscriberId;

    @Valid
    @NotEmpty(message = "Rent dues are required")
    private List<RentDueEntryDTO> rentDues;

}
