package com.sbmtech.mms.models;

import java.time.LocalDate;

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
    private Long id;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_tenure_id", nullable = false)
    private TenureDetails tenure;
    
    
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purpose_id", referencedColumnName = "purpose_id")
	private PaymentPurpose paymentPurpose;
    
    @Column(name = "payment_status")
    private String paymentStatus;

    
}