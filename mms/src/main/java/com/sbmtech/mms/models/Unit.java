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

@Entity
@Table(name = "unit")
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

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public FloorMaster getFloor() {
		return floor;
	}

	public void setFloor(FloorMaster floor) {
		this.floor = floor;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	public UnitSubType getUnitSubType() {
		return unitSubType;
	}

	public void setUnitSubType(UnitSubType unitSubType) {
		this.unitSubType = unitSubType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Boolean getHasBalcony() {
		return hasBalcony;
	}

	public void setHasBalcony(Boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

	public UnitStatus getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(UnitStatus unitStatus) {
		this.unitStatus = unitStatus;
	}

	public Double getRentMonth() {
		return rentMonth;
	}

	public void setRentMonth(Double rentMonth) {
		this.rentMonth = rentMonth;
	}

	public Double getRentYear() {
		return rentYear;
	}

	public void setRentYear(Double rentYear) {
		this.rentYear = rentYear;
	}

	public Double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public String getWaterConnNo() {
		return waterConnNo;
	}

	public void setWaterConnNo(String waterConnNo) {
		this.waterConnNo = waterConnNo;
	}

	public String getEbConnNo() {
		return ebConnNo;
	}

	public void setEbConnNo(String ebConnNo) {
		this.ebConnNo = ebConnNo;
	}

	public String getUnitMainPic1Name() {
		return unitMainPic1Name;
	}

	public void setUnitMainPic1Name(String unitMainPic1Name) {
		this.unitMainPic1Name = unitMainPic1Name;
	}

	public String getUnitPic2Name() {
		return unitPic2Name;
	}

	public void setUnitPic2Name(String unitPic2Name) {
		this.unitPic2Name = unitPic2Name;
	}

	public String getUnitPic3Name() {
		return unitPic3Name;
	}

	public void setUnitPic3Name(String unitPic3Name) {
		this.unitPic3Name = unitPic3Name;
	}

	public String getUnitPic4Name() {
		return unitPic4Name;
	}

	public void setUnitPic4Name(String unitPic4Name) {
		this.unitPic4Name = unitPic4Name;
	}

	public String getUnitPic5Name() {
		return unitPic5Name;
	}

	public void setUnitPic5Name(String unitPic5Name) {
		this.unitPic5Name = unitPic5Name;
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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