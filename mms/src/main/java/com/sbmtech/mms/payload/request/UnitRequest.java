package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;


public class UnitRequest {

	private Integer buildingId;
	private Integer floorId;
	
	@NotEmpty(message = "unitName cannot be null")
	private String unitName;
	
	@NotEmpty(message = "unitType cannot be null")
	private String unitType;
	
	@NotEmpty(message = "unitSubType cannot be null")
	private String unitSubType;
	
	private String size;
	private Boolean hasBalcony;
	private Boolean isOccupied;
	private Boolean isUnderMaintenance;
	private byte[] unitMainPic1;
	private byte[] unitPic2;
	private byte[] unitPic3;
	private byte[] unitPic4;
	private byte[] unitPic5;

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

}
