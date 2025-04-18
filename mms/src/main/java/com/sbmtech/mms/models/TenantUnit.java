package com.sbmtech.mms.models;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToOne;
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

	@Column(name = "tenure_period_in_month")
	private Integer tenurePeriodMonth;

//	@Column(name = "rent_cycle")
//	private String rentCycle;

	@Column(name = "expired")
	private Boolean expired;

	@Column(name = "active")
	private Boolean active;

//	@Column(name = "rent_payment_mode")
//	private String rentPaymentMode;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rent_cycle_id", referencedColumnName = "rent_cycle_id")
	private RentCycle rentCycle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private Order order;

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

	@OneToMany(mappedBy = "tenantUnit", fetch = FetchType.LAZY)
	private List<TenureDetails> tenureDetails = new ArrayList<>();

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

	public Integer getTenurePeriodMonth() {
		return tenurePeriodMonth;
	}

	public void setTenurePeriodMonth(Integer tenurePeriodMonth) {
		this.tenurePeriodMonth = tenurePeriodMonth;
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

//	public PaymentMode getPaymentMode() {
//		return paymentMode;
//	}
//
//	public void setPaymentMode(PaymentMode paymentMode) {
//		this.paymentMode = paymentMode;
//	}

	public RentCycle getRentCycle() {
		return rentCycle;
	}

	public void setRentCycle(RentCycle rentCycle) {
		this.rentCycle = rentCycle;
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

	public List<TenureDetails> getTenureDetails() {
		return tenureDetails;
	}

	public void setTenureDetails(List<TenureDetails> tenureDetails) {
		this.tenureDetails = tenureDetails;
	}

	

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "TenantUnit [tenantUnitId=" + tenantUnitId + ", tenant=" + tenant + ", unit=" + unit + ", parking="
				+ parking + ", tenurePeriodMonth=" + tenurePeriodMonth + ", expired=" + expired + ", active=" + active
				+ ", rentCycle=" + rentCycle + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", tenureDetails=" + tenureDetails + "]";
	}

}