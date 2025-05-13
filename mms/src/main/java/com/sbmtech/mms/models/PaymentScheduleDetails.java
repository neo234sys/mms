package com.sbmtech.mms.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "payment_schedule_details")
public class PaymentScheduleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_schd_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_unit_id", referencedColumnName = "tenant_unit_id")
    private TenantUnit tenantUnit;

    @ManyToOne
    @JoinColumn(name = "payment_mode", referencedColumnName = "payment_mode_id")
    private PaymentMode paymentMode;

    @Column(name = "payment_schd_date")
    private LocalDateTime paymentScheduleDate;

    @Column(name = "payment_status")
    private String paymentStatus;

    // Getters and Setters
}