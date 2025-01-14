package com.sbmtech.mms.payload.request;

public class SubscriptionPaymentRequest {

	private Integer subscriberId;
	private Double amount;
	private String paymentMethod; // Enum: Credit Card, Bank Transfer, Cash, Cheque
	private Long transactionId;

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

}
