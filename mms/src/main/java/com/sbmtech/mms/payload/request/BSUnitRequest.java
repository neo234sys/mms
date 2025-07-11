package com.sbmtech.mms.payload.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class BSUnitRequest {

	private Integer subscriberId;
	
	private Integer buildingId;

	
	@NotEmpty(message = "floorName cannot be null")
	private String floorName;
	
	@NotEmpty(message = "unitName cannot be null")
	private String unitName;
	

	@NotNull(message = "unitTypeId cannot be null")
	private Integer unitTypeId;
	

	@NotNull(message = "unitSubTypeId cannot be null")
	private Integer unitSubTypeId;
	
	private String size;
	private Boolean hasBalcony;
	private Boolean isOccupied;
	private Boolean isUnderMaintenance;
	private byte[] unitMainPic1;
	private byte[] unitPic2;
	private byte[] unitPic3;
	private byte[] unitPic4;
	private byte[] unitPic5;
	
	


	
	private String waterConnNo;
	private String ebConnNo;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}


	
	

	public String getUnitName() {
		return unitName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
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

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

}
