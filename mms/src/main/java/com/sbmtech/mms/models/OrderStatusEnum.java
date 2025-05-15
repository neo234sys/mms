package com.sbmtech.mms.models;

public enum OrderStatusEnum {
	INITIATED("INITIATED"),PENDING("PENDING"), PROCESSED("PROCESSED"), CANCELLED("CANCELLED"), FAILED("FAILED");

	private String value;

	private OrderStatusEnum(String value) {

		this.value = value;
	}

	public String getValue() {

		return this.value;
	}
}