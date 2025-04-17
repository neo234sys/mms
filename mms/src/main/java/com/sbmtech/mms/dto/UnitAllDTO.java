package com.sbmtech.mms.dto;

public class UnitAllDTO {

	private Integer unitId;
	private String unitName;
	private String floorName;
	private String unitTypeName;
	private String unitSubtypeName;
	private String size;
	private Boolean hasBalcony;
	private String unitStatusName;
	private Double rentMonth;
	private Double rentYear;
	private Double securityDeposit;
	private String buildingName;
	private String address;
	private String communityName;
	private String areaName;
	private String cityName;
	private String stateName;
	private String countryName;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getUnitTypeName() {
		return unitTypeName;
	}

	public void setUnitTypeName(String unitTypeName) {
		this.unitTypeName = unitTypeName;
	}

	public String getUnitSubtypeName() {
		return unitSubtypeName;
	}

	public void setUnitSubtypeName(String unitSubtypeName) {
		this.unitSubtypeName = unitSubtypeName;
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

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "UnitAllDTO [unitId=" + unitId + ", unitName=" + unitName + ", floorName=" + floorName
				+ ", unitTypeName=" + unitTypeName + ", unitSubtypeName=" + unitSubtypeName + ", size=" + size
				+ ", hasBalcony=" + hasBalcony + ", unitStatusName=" + unitStatusName + ", rentMonth=" + rentMonth
				+ ", rentYear=" + rentYear + ", securityDeposit=" + securityDeposit + ", buildingName=" + buildingName
				+ ", address=" + address + ", communityName=" + communityName + ", areaName=" + areaName + ", cityName="
				+ cityName + ", stateName=" + stateName + ", countryName=" + countryName + "]";
	}

}