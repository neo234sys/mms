package com.sbmtech.mms.models;

import java.io.Serializable;
import java.sql.Timestamp;

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
	@JoinColumn(name = "floor_id", referencedColumnName = "floor_id")
	private Floor floor;

	@Column(name = "unit_name")
	private String unitName;

	//@Enumerated(EnumType.STRING)
	@Column(name = "unit_type")
	private String unitType;

	//@Enumerated(EnumType.STRING)
	@Column(name = "unit_sub_type")
	private String unitSubType;

	@Column(name = "size")
	private String size;

	@Column(name = "has_balcony")
	private Boolean hasBalcony;

	@Column(name = "is_occupied", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean isOccupied;

	@Column(name = "is_under_maintenance", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean isUnderMaintenance;

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
	
	@Lob
	@Column(name = "unit_main_pic1")
	private byte[] unitMainPic1;

	@Lob
	@Column(name = "unit_pic2")
	private byte[] unitPic2;

	@Lob
	@Column(name = "unit_pic3")
	private byte[] unitPic3;

	@Lob
	@Column(name = "unit_pic4")
	private byte[] unitPic4;

	@Lob
	@Column(name = "unit_pic5")
	private byte[] unitPic5;

	@Column(name = "created_time", updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdTime;

	@Column(name = "updated_time", nullable = false)
	@UpdateTimestamp
	private Timestamp updatedTime;

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

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getUnitSubType() {
		return unitSubType;
	}

	public void setUnitSubType(String unitSubType) {
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

	public Boolean getIsOccupied() {
		return isOccupied;
	}

	public void setIsOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public Boolean getIsUnderMaintenance() {
		return isUnderMaintenance;
	}

	public void setIsUnderMaintenance(Boolean isUnderMaintenance) {
		this.isUnderMaintenance = isUnderMaintenance;
	}

	public byte[] getUnitMainPic1() {
		return unitMainPic1;
	}

	public void setUnitMainPic1(byte[] unitMainPic1) {
		this.unitMainPic1 = unitMainPic1;
	}

	public byte[] getUnitPic2() {
		return unitPic2;
	}

	public void setUnitPic2(byte[] unitPic2) {
		this.unitPic2 = unitPic2;
	}

	public byte[] getUnitPic3() {
		return unitPic3;
	}

	public void setUnitPic3(byte[] unitPic3) {
		this.unitPic3 = unitPic3;
	}

	public byte[] getUnitPic4() {
		return unitPic4;
	}

	public void setUnitPic4(byte[] unitPic4) {
		this.unitPic4 = unitPic4;
	}

	public byte[] getUnitPic5() {
		return unitPic5;
	}

	public void setUnitPic5(byte[] unitPic5) {
		this.unitPic5 = unitPic5;
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

	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", building=" + building + ", floor=" + floor + ", unitName=" + unitName
				+ ", unitType=" + unitType + "]";
	}
	

}