package com.sbmtech.mms.payload.request;

public class ApiResponse<T> {
	private int responseCode;
	private String responseMessage;
	private T data;

	public ApiResponse(int responseCode, String responseMessage, T data) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.data = data;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}