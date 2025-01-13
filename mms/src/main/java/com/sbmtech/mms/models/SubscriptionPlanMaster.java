package com.sbmtech.mms.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "subscription_plan_master")
public class SubscriptionPlanMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "plan_id")
	private Integer planId;

	@Column(name = "plan_name")
	private String planName;

	@Column(name = "plan_duration_days")
	private Integer planDurationDays;

	@Column(name = "plan_price")
	private Float planPrice;

	@Column(name = "features")
	private String features;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getPlanDurationDays() {
		return planDurationDays;
	}

	public void setPlanDurationDays(Integer planDurationDays) {
		this.planDurationDays = planDurationDays;
	}

	public Float getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(Float planPrice) {
		this.planPrice = planPrice;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
