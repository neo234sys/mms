package com.sbmtech.mms.dto;

import java.io.Serializable;

public class NotificationEmailResponseDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String email;
	private boolean isEmailSent = false;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEmailSent() {
		return isEmailSent;
	}
	public void setEmailSent(boolean isEmailSent) {
		this.isEmailSent = isEmailSent;
	}
}