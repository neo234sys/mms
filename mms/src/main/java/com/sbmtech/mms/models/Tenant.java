package com.sbmtech.mms.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tenants")
public class Tenant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tenant_id")
	private Integer tenantId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "emirates_id")
	private Long emiratesId;

	@Column(name = "eida_expiry_date")
	@Temporal(TemporalType.DATE)
	private Date eidaExpiryDate;

	// @Lob
	@Column(name = "eida_copy_filename")
	private String eidaCopyFilename;

	@Column(name = "passport_no")
	private String passportNo;

	@Column(name = "passport_expiry_date")
	@Temporal(TemporalType.DATE)
	private Date passportExpiryDate;

	// @Lob
	@Column(name = "passport_copy_filename")
	private String passportCopyFilename;

	// @Lob
	@Column(name = "photo_filename")
	private String photoFilename;

	@Column(name = "nat_id")
	private Integer nationalityId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nat_id", referencedColumnName = "country_id", insertable = false, updatable = false)
	private Countries nationality;

	@Column(name = "created_time")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdTime;

	@Column(name = "updated_time")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedTime;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getEmiratesId() {
		return emiratesId;
	}

	public void setEmiratesId(Long emiratesId) {
		this.emiratesId = emiratesId;
	}

	public Date getEidaExpiryDate() {
		return eidaExpiryDate;
	}

	public void setEidaExpiryDate(Date eidaExpiryDate) {
		this.eidaExpiryDate = eidaExpiryDate;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(Date passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public String getEidaCopyFilename() {
		return eidaCopyFilename;
	}

	public void setEidaCopyFilename(String eidaCopyFilename) {
		this.eidaCopyFilename = eidaCopyFilename;
	}

	public String getPassportCopyFilename() {
		return passportCopyFilename;
	}

	public void setPassportCopyFilename(String passportCopyFilename) {
		this.passportCopyFilename = passportCopyFilename;
	}

	public String getPhotoFilename() {
		return photoFilename;
	}

	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	public Integer getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public Countries getNationality() {
		return nationality;
	}

	public void setNationality(Countries nationality) {
		this.nationality = nationality;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", emiratesId=" + emiratesId
				+ ", eidaExpiryDate=" + eidaExpiryDate + ", passportNo=" + passportNo + ", passportExpiryDate="
				+ passportExpiryDate + ", passportCopy=" + ", nationalityId=" + nationalityId + ", nationality="
				+ nationality + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", isDeleted=" + isDeleted + "]";
	}

}