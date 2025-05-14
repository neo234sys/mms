package com.sbmtech.mms.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenure_details")
@Setter
@Getter
public class TenureDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tenant_tenure_id")
	private Integer tenantTenureId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_unit_id", referencedColumnName = "tenant_unit_id")
	private TenantUnit tenantUnit;

	@Column(name = "tenancy_start_date")
	private Date tenancyStartDate;

	@Column(name = "tenancy_end_date")
	private Date tenancyEndDate;
	
	@Column(name = "total_rent_per_year")
	private Double totalRentPerYear;
	
	@Column(name = "discount")
	private Double discount;
	
	@Column(name = "total_rent_af_discount")
	private Double totalRentAfDiscount;
	
	@Column(name = "security_deposit")
	private Double securityDeposit;

	@Lob
	@Column(name = "tenancy_copy")
	private byte[] tenancyCopy;

	@Column(name = "created_time")
	@CreationTimestamp
	private Date createdTime;

	@Column(name = "updated_time")
	@UpdateTimestamp
	private Date updatedTime;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;
	
	
	@OneToMany(mappedBy = "tenure", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RentDueEntity> rentDues = new ArrayList<>();

	

}