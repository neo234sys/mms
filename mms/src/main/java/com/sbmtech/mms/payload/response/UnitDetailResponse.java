package com.sbmtech.mms.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UnitDetailResponse {

	private Integer unitId;
	private Integer buildingId;
	private String floor;
	private String unitName;
	private Integer unitTypeId;
	private String unitTypeName;
	private Integer unitSubTypeId;
	private String unitSubTypeName;
	private String size;
	private Boolean hasBalcony;
	private Integer unitStatusId;
	private String unitStatusName;
	private Double rentMonth;
	private Double rentYear;
	private Double securityDeposit;
	private String waterConnNo;
	private String ebConnNo;
	private String unitMainPic1Link;
	private String unitPic2Link;
	private String unitPic3Link;
	private String unitPic4Link;
	private String unitPic5Link;
	private TenantDetailResponse tenantDetails;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getUnitTypeId() {
		return unitTypeId;
	}

	public void setUnitTypeId(Integer unitTypeId) {
		this.unitTypeId = unitTypeId;
	}

	public String getUnitTypeName() {
		return unitTypeName;
	}

	public void setUnitTypeName(String unitTypeName) {
		this.unitTypeName = unitTypeName;
	}

	public Integer getUnitSubTypeId() {
		return unitSubTypeId;
	}

	public void setUnitSubTypeId(Integer unitSubTypeId) {
		this.unitSubTypeId = unitSubTypeId;
	}

	public String getUnitSubTypeName() {
		return unitSubTypeName;
	}

	public void setUnitSubTypeName(String unitSubTypeName) {
		this.unitSubTypeName = unitSubTypeName;
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

	public Integer getUnitStatusId() {
		return unitStatusId;
	}

	public void setUnitStatusId(Integer unitStatusId) {
		this.unitStatusId = unitStatusId;
	}

	public String getUnitStatusName() {
		return unitStatusName;
	}

	public void setUnitStatusName(String unitStatusName) {
		this.unitStatusName = unitStatusName;
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

	public String getUnitMainPic1Link() {
		return unitMainPic1Link;
	}

	public void setUnitMainPic1Link(String unitMainPic1Link) {
		this.unitMainPic1Link = unitMainPic1Link;
	}

	public String getUnitPic2Link() {
		return unitPic2Link;
	}

	public void setUnitPic2Link(String unitPic2Link) {
		this.unitPic2Link = unitPic2Link;
	}

	public String getUnitPic3Link() {
		return unitPic3Link;
	}

	public void setUnitPic3Link(String unitPic3Link) {
		this.unitPic3Link = unitPic3Link;
	}

	public String getUnitPic4Link() {
		return unitPic4Link;
	}

	public void setUnitPic4Link(String unitPic4Link) {
		this.unitPic4Link = unitPic4Link;
	}

	public String getUnitPic5Link() {
		return unitPic5Link;
	}

	public void setUnitPic5Link(String unitPic5Link) {
		this.unitPic5Link = unitPic5Link;
	}

	public TenantDetailResponse getTenantDetails() {
		return tenantDetails;
	}

	public void setTenantDetails(TenantDetailResponse tenantDetails) {
		this.tenantDetails = tenantDetails;
	}

	@Override
	public String toString() {
		return "UnitDetailResponse [unitId=" + unitId + ", buildingId=" + buildingId + ", floor=" + floor
				+ ", unitName=" + unitName + ", unitTypeId=" + unitTypeId + ", unitTypeName=" + unitTypeName
				+ ", unitSubTypeId=" + unitSubTypeId + ", unitSubTypeName=" + unitSubTypeName + ", size=" + size
				+ ", hasBalcony=" + hasBalcony + ", unitStatusId=" + unitStatusId + ", unitStatusName=" + unitStatusName
				+ ", rentMonth=" + rentMonth + ", rentYear=" + rentYear + ", securityDeposit=" + securityDeposit
				+ ", waterConnNo=" + waterConnNo + ", ebConnNo=" + ebConnNo + ", unitMainPic1Link=" + unitMainPic1Link
				+ ", unitPic2Link=" + unitPic2Link + ", unitPic3Link=" + unitPic3Link + ", unitPic4Link=" + unitPic4Link
				+ ", unitPic5Link=" + unitPic5Link + ", tenantDetails=" + tenantDetails + "]";
	}

}