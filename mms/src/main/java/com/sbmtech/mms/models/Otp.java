package com.sbmtech.mms.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp")
public class Otp implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "otp_id")
	private Integer otpId;

	@Column(name = "otp_code")
	private Long otpCode;

	@Column(name = "otp_type")
	private String otpType; // 'S' for subscriber, 'U' for user, etc.

	@Column(name = "reference_id")
	private Integer referenceId;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "expires_at")
	private Timestamp expiresAt;

	@Column(name = "verified")
	private Boolean verified;

	public Integer getOtpId() {
		return otpId;
	}

	public void setOtpId(Integer otpId) {
		this.otpId = otpId;
	}

	public Long getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(Long otpCode) {
		this.otpCode = otpCode;
	}

	public String getOtpType() {
		return otpType;
	}

	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Timestamp expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}