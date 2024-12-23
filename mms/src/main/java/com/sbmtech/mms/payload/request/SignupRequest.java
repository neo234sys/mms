package com.sbmtech.mms.payload.request;

import java.util.Set;

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

	private Set<String> role;

	private String password;

	private Boolean active;

	private Long emiratesId;

	private Long natId;

	private Long companyId;

	private String address;

	private byte[] eidaCopy;
}