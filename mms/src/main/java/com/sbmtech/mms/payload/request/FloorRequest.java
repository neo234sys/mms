package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;


public class FloorRequest {

	@NotEmpty(message = "floorName must not be empty and null")
	private String floorName;
	
	
	private Integer buildingId;

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

}