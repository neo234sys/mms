package com.sbmtech.mms.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment_purpose")
@Setter
@Getter
public class PaymentPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purpose_id")
    private Integer purposeId;

    @Column(name = "purpose_name", length = 75)
    private String purposeName;

 
 
}