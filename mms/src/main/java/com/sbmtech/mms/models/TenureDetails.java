package com.sbmtech.mms.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tenure_details")
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

	public Integer getTenantTenureId() {
		return tenantTenureId;
	}

	public void setTenantTenureId(Integer tenantTenureId) {
		this.tenantTenureId = tenantTenureId;
	}

	public TenantUnit getTenantUnit() {
		return tenantUnit;
	}

	public void setTenantUnit(TenantUnit tenantUnit) {
		this.tenantUnit = tenantUnit;
	}

	

	public Date getTenancyStartDate() {
		return tenancyStartDate;
	}

	public void setTenancyStartDate(Date tenancyStartDate) {
		this.tenancyStartDate = tenancyStartDate;
	}

	public Date getTenancyEndDate() {
		return tenancyEndDate;
	}

	public void setTenancyEndDate(Date tenancyEndDate) {
		this.tenancyEndDate = tenancyEndDate;
	}

	public byte[] getTenancyCopy() {
		return tenancyCopy;
	}

	public void setTenancyCopy(byte[] tenancyCopy) {
		this.tenancyCopy = tenancyCopy;
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
	
	
	
	

}