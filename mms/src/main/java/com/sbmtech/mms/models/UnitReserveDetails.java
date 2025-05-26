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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "unit_reserve_details")
@Setter
@Getter
@NoArgsConstructor
public class UnitReserveDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_reserve_id")
	private Integer unitReserveId;

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
	
	@Column(name = "status")
	private String status;  //REALEASED,OCCUPIED, PAYMENT_DONE

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
	
	 @ManyToOne
	 @JoinColumn(name = "order_id")
	 private PaymentOrderEntity order;

	@Override
	public String toString() {
		return "UnitReserveDetails [unitReserveId=" + unitReserveId + ", unit=" + unit + ", user=" + user
				+ ", reserveStartDate=" + reserveStartDate + ", reserveEndDate=" + reserveEndDate + ", paymentRequired="
				+ paymentRequired + "]";
	}
	
	

}