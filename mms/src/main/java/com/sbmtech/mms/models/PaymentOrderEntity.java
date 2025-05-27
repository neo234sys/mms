package com.sbmtech.mms.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_order")
@Setter
@Getter
@NoArgsConstructor
public class PaymentOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
    private Long orderId;


    
    @ManyToOne
    @JoinColumn(name = "payment_mode_id")
    private PaymentMode paymentMode;
    
    
    @OneToOne
    @JoinColumn(name = "purpose_id")
    PaymentPurpose PaymentPurpose;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions = new ArrayList<>();
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_id")
	private Subscriber subscriber;

	@Column(name = "order_date")
    private LocalDateTime orderDate;
    
    private String paymentStatus; //refere OrderSTatusEnum
    

    
}