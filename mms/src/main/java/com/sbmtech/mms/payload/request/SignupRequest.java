package com.sbmtech.mms.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

	private String mobileNo;

	private String email;

	private String role;

	private String password;

	private Boolean active;

	private Long emiratesId;

	private Integer natId;

	private Integer companyId;

	private String address;

	private byte[] eidaCopy;
}