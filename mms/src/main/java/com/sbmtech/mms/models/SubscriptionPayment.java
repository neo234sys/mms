package com.sbmtech.mms.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "subscription_payment")
public class SubscriptionPayment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscription_payment_id")
	private Integer subscriptionPaymentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_id", referencedColumnName = "subscriber_id")
	private Subscriber subscriber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id", referencedColumnName = "subscription_id")
	private Subscriptions subscription;

	@Column(name = "amount")
	private Double amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method")
	private PaymentMethod paymentMethod;

	@Column(name = "payment_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;

	@Column(name = "transaction_id")
	private Long transactionId;

	@Column(name = "status")
	private String status;

	public Integer getSubscriptionPaymentId() {
		return subscriptionPaymentId;
	}

	public void setSubscriptionPaymentId(Integer subscriptionPaymentId) {
		this.subscriptionPaymentId = subscriptionPaymentId;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public Subscriptions getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscriptions subscription) {
		this.subscription = subscription;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}