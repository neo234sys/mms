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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tenant_unit")
public class TenantUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tenant_unit_id")
	private Integer tenantUnitId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id", referencedColumnName = "tenant_id")
	private Tenant tenant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	private Unit unit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parking_id", referencedColumnName = "parking_id")
	private Parking parking;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_keys_id", referencedColumnName = "unit_keys_id")
	private UnitKeys unitKeys;

	@Column(name = "registered_date")
	private Date registeredDate;

	@Column(name = "security_deposit")
	private BigDecimal securityDeposit;

	@Column(name = "rent")
	private BigDecimal rent;

	@Enumerated(EnumType.STRING)
	@Column(name = "rent_cycle")
	private RentCycle rentCycle;

	@Column(name = "expired")
	private Boolean expired;

	@Column(name = "active")
	private Boolean active;

	@Enumerated(EnumType.STRING)
	@Column(name = "rent_payment_mode")
	private RentPaymentMode rentPaymentMode;

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

	public Integer getTenantUnitId() {
		return tenantUnitId;
	}

	public void setTenantUnitId(Integer tenantUnitId) {
		this.tenantUnitId = tenantUnitId;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public UnitKeys getUnitKeys() {
		return unitKeys;
	}

	public void setUnitKeys(UnitKeys unitKeys) {
		this.unitKeys = unitKeys;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public BigDecimal getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(BigDecimal securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public RentCycle getRentCycle() {
		return rentCycle;
	}

	public void setRentCycle(RentCycle rentCycle) {
		this.rentCycle = rentCycle;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public RentPaymentMode getRentPaymentMode() {
		return rentPaymentMode;
	}

	public void setRentPaymentMode(RentPaymentMode rentPaymentMode) {
		this.rentPaymentMode = rentPaymentMode;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}