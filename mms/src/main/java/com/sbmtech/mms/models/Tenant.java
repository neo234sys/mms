package com.sbmtech.mms.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenants")
@Setter
@Getter
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

	
	@Column(name = "passport_copy_filename")
	private String passportCopyFilename;

	
	@Column(name = "photo_filename")
	private String photoFilename;

	//@Column(name = "nat_id")
	//private Integer nationalityId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nat_id", referencedColumnName = "country_id")
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_id")
	private Subscriber subscriber;

	@OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
	private List<TenantUnit> tenantUnits;
	
	@Column(name = "tenant_status")
	private String tenantStatus;

	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", emiratesId=" + emiratesId
				+ ", eidaExpiryDate=" + eidaExpiryDate + ", passportNo=" + passportNo + ", passportExpiryDate="
				+ passportExpiryDate + ", passportCopy=" +  ", nationality="
				+ nationality + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", isDeleted=" + isDeleted + "]";
	}

}