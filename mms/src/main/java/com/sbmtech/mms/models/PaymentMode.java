package com.sbmtech.mms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment_mode")
@Setter
@Getter
public class PaymentMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "payment_mode_id")
    private int paymentModeId;

    @Column(name = "payment_mode_name", nullable = true, length = 50)
    private String paymentModeName;


    public PaymentMode() {}


  

    
}