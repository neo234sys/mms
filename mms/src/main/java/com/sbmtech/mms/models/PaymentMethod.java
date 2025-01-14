package com.sbmtech.mms.models;

public enum PaymentMethod {
	CREDIT_CARD("Credit Card"), BANK_TRANSFER("Bank Transfer"), CASH("Cash"), CHEQUE("Cheque");

	private String value;

	PaymentMethod(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static PaymentMethod fromValue(String value) {
		for (PaymentMethod method : values()) {
			if (method.getValue().equalsIgnoreCase(value)) {
				return method;
			}
		}
		throw new IllegalArgumentException("Unknown payment method: " + value);
	}
}