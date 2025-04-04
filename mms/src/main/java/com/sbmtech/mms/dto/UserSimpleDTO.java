package com.sbmtech.mms.dto;

public class UserSimpleDTO {

	private Integer userId;
	private String userName;
	private String email;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserSimpleDTO [userId=" + userId + ", userName=" + userName + ", email=" + email + "]";
	}

}
