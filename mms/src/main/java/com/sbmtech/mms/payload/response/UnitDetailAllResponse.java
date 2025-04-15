package com.sbmtech.mms.payload.response;

public class UnitDetailAllResponse {

	private Integer unitId;
	private String unitName;
	private Integer buildingId;
	private String buildingName;
	private String floor;
	private Integer unitTypeId;
	private String unitTypeName;
	private Integer unitSubTypeId;
	private String unitSubTypeName;
	private Integer unitStatusId;
	private String unitStatusName;
	private TenantDetailResponse tenantDetails;
	private String unitMainPic1Link;
	private String unitPic2Link;
	private String unitPic3Link;
	private String unitPic4Link;
	private String unitPic5Link;

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

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
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

	public TenantDetailResponse getTenantDetails() {
		return tenantDetails;
	}

	public void setTenantDetails(TenantDetailResponse tenantDetails) {
		this.tenantDetails = tenantDetails;
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

	@Override
	public String toString() {
		return "UnitDetailAllResponse [unitId=" + unitId + ", unitName=" + unitName + ", buildingId=" + buildingId
				+ ", buildingName=" + buildingName + ", floor=" + floor + ", unitTypeId=" + unitTypeId
				+ ", unitTypeName=" + unitTypeName + ", unitSubTypeId=" + unitSubTypeId + ", unitSubTypeName="
				+ unitSubTypeName + ", unitStatusId=" + unitStatusId + ", unitStatusName=" + unitStatusName
				+ ", tenantDetails=" + tenantDetails + ", unitMainPic1Link=" + unitMainPic1Link + ", unitPic2Link="
				+ unitPic2Link + ", unitPic3Link=" + unitPic3Link + ", unitPic4Link=" + unitPic4Link + ", unitPic5Link="
				+ unitPic5Link + "]";
	}

}
