package com.sbmtech.mms.dto;

import java.time.LocalDate;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbmtech.mms.models.PaymentOrderEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentDue {
	private Long rentDueId;
	private Integer tenureId;
    private LocalDate dueDate;
    private double amount;
    private Integer paymentPurposeId;
    private String paymentPurposeName;
    private Integer paymentModeId;
    private String paymentModeName;
    

    public RentDue(LocalDate dueDate, double amount,Integer paymentPurposeId,String paymentPurposeName) {
        this.dueDate = dueDate;
        this.amount = amount;
        this.paymentPurposeId=paymentPurposeId;
        this.paymentPurposeName=paymentPurposeName;
    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PaymentOrderEntity order;
    

    @Override
    public String toString() {
        return "RentDue{dueDate=" + dueDate + ", amount=" + amount + '}';
    }
}