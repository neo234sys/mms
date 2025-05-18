package com.sbmtech.mms.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "rent_due")
public class RentDueEntity {
	
	public RentDueEntity() {}

    public RentDueEntity(LocalDate dueDate, double amount, TenureDetails tenure,PaymentPurpose paymentPurpose) {
        this.dueDate = dueDate;
        this.amount = amount;
        this.tenure = tenure;
        this. paymentPurpose= paymentPurpose;
    }
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_due_id")
    private Long rentDueId;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_tenure_id", nullable = false)
    private TenureDetails tenure;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_id")
	private Subscriber subscriber;
    
    
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purpose_id", referencedColumnName = "purpose_id")
	private PaymentPurpose paymentPurpose;
    
    @OneToOne(fetch = FetchType.LAZY)
   	@JoinColumn(name = "payment_mode_id", referencedColumnName = "payment_mode_id")
   	private PaymentMode paymentMode;
    
//    @OneToOne(mappedBy = "rentDue", cascade = CascadeType.ALL)
//    private PaymentOrderEntity order;
    

    @Column(name = "active", nullable = false)
    private Integer active;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private PaymentOrderEntity order;
    
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    
    @Column(name = "payment_status")
    private String paymentStatus;
    
    @OneToOne(mappedBy = "rentDue", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private TenantChequeDetails chequeDetails;
    
    
    @OneToOne(mappedBy = "rentDue", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private TenantCCDetails ccDetails;

    
}