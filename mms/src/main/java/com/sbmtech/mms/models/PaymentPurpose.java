package com.sbmtech.mms.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_purpose")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purpose_id")
    private Integer purposeId;

    @Column(name = "purpose_name", length = 75)
    private String purposeName;

 
 
}