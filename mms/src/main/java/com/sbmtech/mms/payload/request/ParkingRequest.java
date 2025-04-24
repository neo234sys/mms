package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;

public class ParkingRequest {

	@NotEmpty(message = "parkingName cannot be null")
	private String parkingName;

	private Integer parkZoneId;

	private String parkingType;

	// private Boolean isAvailable;

	private Integer buildingId;

	// @ValidSubscriberlId
	private Integer subscriberId;

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public Integer getParkZoneId() {
		return parkZoneId;
	}

	public void setParkZoneId(Integer parkZoneId) {
		this.parkZoneId = parkZoneId;
	}

	public String getParkingType() {
		return parkingType;
	}

	public void setParkingType(String parkingType) {
		this.parkingType = parkingType;
	}

//	public Boolean getIsAvailable() {
//		return isAvailable;
//	}
//
//	public void setIsAvailable(Boolean isAvailable) {
//		this.isAvailable = isAvailable;
//	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

}