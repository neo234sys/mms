package com.sbmtech.mms.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unit")
@Setter
@Getter
public class Unit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_id")
	private Integer unitId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "building_id", referencedColumnName = "building_id")
	private Building building;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_name", referencedColumnName = "floor_name")
	private FloorMaster floor;

	@Column(name = "unit_name")
	private String unitName;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_type_id", referencedColumnName = "unit_type_id")
	private UnitType unitType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_subtype_id", referencedColumnName = "unit_subtype_id")
	private UnitSubType unitSubType;

	@Column(name = "size")
	private String size;

	@Column(name = "has_balcony")
	private Boolean hasBalcony;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_status_id", referencedColumnName = "unit_status_id")
	private UnitStatus unitStatus;

	@Column(name = "rent_month")
	private Double rentMonth;

	@Column(name = "rent_year")
	private Double rentYear;

	@Column(name = "security_deposit")
	private Double securityDeposit;

	@Column(name = "water_conn_no")
	private String waterConnNo;

	@Column(name = "eb_conn_no")
	private String ebConnNo;

	@Column(name = "unit_main_pic1_name")
	private String unitMainPic1Name;

	@Column(name = "unit_pic2_name")
	private String unitPic2Name;

	@Column(name = "unit_pic3_name")
	private String unitPic3Name;

	@Column(name = "unit_pic4_name")
	private String unitPic4Name;

	@Column(name = "unit_pic5_name")
	private String unitPic5Name;

	@Column(name = "created_time", updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdTime;

	@Column(name = "updated_time", nullable = false)
	@UpdateTimestamp
	private Timestamp updatedTime;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_id")
	private Subscriber subscriber;


	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", building=" + building + ", floor=" + floor + ", unitName=" + unitName
				+ ", unitType=" + unitType + ", unitSubType=" + unitSubType + ", size=" + size + ", hasBalcony="
				+ hasBalcony + ", unitStatus=" + unitStatus + ", rentMonth=" + rentMonth + ", rentYear=" + rentYear
				+ ", securityDeposit=" + securityDeposit + ", waterConnNo=" + waterConnNo + ", ebConnNo=" + ebConnNo
				+ ", unitMainPic1Name=" + unitMainPic1Name + ", unitPic2Name=" + unitPic2Name + ", unitPic3Name="
				+ unitPic3Name + ", unitPic4Name=" + unitPic4Name + ", unitPic5Name=" + unitPic5Name + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + ", isDeleted=" + isDeleted + "]";
	}

}