package com.sbmtech.mms.models;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "unit_reserve_details")
public class UnitReserveDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_reserve_details")
	private Long unitReserveDetailsId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	private Unit unit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@Column(name = "reserve_start_date")
	@Temporal(TemporalType.DATE)
	private Date reserveStartDate;

	@Column(name = "reserve_end_date")
	@Temporal(TemporalType.DATE)
	private Date reserveEndDate;

	@Column(name = "payment_required")
	private Integer paymentRequired;

	@Column(name = "created_time", updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdTime;

	@Column(name = "updated_time", nullable = false)
	@UpdateTimestamp
	private Timestamp updatedTime;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	public Long getUnitReserveDetailsId() {
		return unitReserveDetailsId;
	}

	public void setUnitReserveDetailsId(Long unitReserveDetailsId) {
		this.unitReserveDetailsId = unitReserveDetailsId;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getReserveStartDate() {
		return reserveStartDate;
	}

	public void setReserveStartDate(Date reserveStartDate) {
		this.reserveStartDate = reserveStartDate;
	}

	public Date getReserveEndDate() {
		return reserveEndDate;
	}

	public void setReserveEndDate(Date reserveEndDate) {
		this.reserveEndDate = reserveEndDate;
	}

	public Integer getPaymentRequired() {
		return paymentRequired;
	}

	public void setPaymentRequired(Integer paymentRequired) {
		this.paymentRequired = paymentRequired;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
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

	@Override
	public String toString() {
		return "UnitReserveDetails [unitReserveDetailsId=" + unitReserveDetailsId + ", unit=" + unit + ", user=" + user
				+ ", reserveStartDate=" + reserveStartDate + ", reserveEndDate=" + reserveEndDate + ", paymentRequired="
				+ paymentRequired + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + "]";
	}

}