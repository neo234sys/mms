package com.sbmtech.mms.models;

public enum OrderStatusEnum {
	PENDING("PENDING"), PROCESSED("PROCESSED"), CANCELLED("CANCELLED");

	private String value;

	private OrderStatusEnum(String value) {

		this.value = value;
	}

	public String getValue() {

		return this.value;
	}
}