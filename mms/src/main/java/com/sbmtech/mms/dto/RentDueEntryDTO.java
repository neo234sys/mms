package com.sbmtech.mms.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentDueEntryDTO {

    private Long rentDueId;
    //private Integer tenureId;

   // @NotNull(message = "Due date is required")
    private LocalDate dueDate;

   // @NotNull(message = "Amount is required")
   // @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private double amount;

    private Integer paymentPurposeId;
    private Integer paymentModeId;
  //  private String paymentPurposeName;

    private CreditCardDetailsDTO ccDetails;

    //@Valid
    //private List<ChequeDetailsDTO> chequeDetails;
    
    ChequeDetailsDTO chequeDetails;
}
