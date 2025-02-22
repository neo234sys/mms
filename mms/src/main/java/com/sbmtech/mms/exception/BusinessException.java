package com.sbmtech.mms.exception;

public class BusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String message,Throwable tw) {
		super(message);
	}
}