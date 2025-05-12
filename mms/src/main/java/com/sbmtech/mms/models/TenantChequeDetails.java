package com.sbmtech.mms.models;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenant_cheque_details")
@Setter
@Getter
public class TenantChequeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tenantId;
    private Integer tenantUnitId;

    private String chequeNo;
    private String chequeBank;
    private Double chequeAmount;
    private String chequePicName;
    private LocalDate chequeDate;

    private Integer paymentPurposeId;
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}