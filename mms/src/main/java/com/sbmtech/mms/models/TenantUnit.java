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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenant_unit")
@Setter
@Getter
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
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
//	private Order order;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_id")
	private Subscriber subscriber;

	@OneToMany(mappedBy = "tenantUnit", fetch = FetchType.LAZY)
	private List<TenureDetails> tenureDetails = new ArrayList<>();

	

}