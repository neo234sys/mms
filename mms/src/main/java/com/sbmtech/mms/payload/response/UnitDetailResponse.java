package com.sbmtech.mms.payload.response;

public class UnitDetailResponse {

	private Integer unitId;
	private Integer buildingId;
	private Integer floorId;
	private String unitName;
	private Integer unitTypeId;
	private Integer unitSubTypeId;
	private String size;
	private Boolean hasBalcony;
	private Integer unitStatusId;
	private Double rentMonth;
	private Double rentYear;
	private Double securityDeposit;
	private String waterConnNo;
	private String ebConnNo;

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

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
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

	public Integer getUnitSubTypeId() {
		return unitSubTypeId;
	}

	public void setUnitSubTypeId(Integer unitSubTypeId) {
		this.unitSubTypeId = unitSubTypeId;
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
		return "UnitDetailResponse [unitId=" + unitId + ", buildingId=" + buildingId + ", floorId=" + floorId
				+ ", unitName=" + unitName + ", unitTypeId=" + unitTypeId + ", unitSubTypeId=" + unitSubTypeId
				+ ", size=" + size + ", hasBalcony=" + hasBalcony + ", unitStatusId=" + unitStatusId + ", rentMonth="
				+ rentMonth + ", rentYear=" + rentYear + ", securityDeposit=" + securityDeposit + ", waterConnNo="
				+ waterConnNo + ", ebConnNo=" + ebConnNo + "]";
	}

}
