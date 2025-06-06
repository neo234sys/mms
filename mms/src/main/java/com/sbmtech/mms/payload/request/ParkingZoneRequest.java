package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;

public class ParkingZoneRequest {

	@NotEmpty(message = "parkZoneName cannot be null")
	private String parkZoneName;

	// @ValidSubscriberlId
	private Integer subscriberId;

	private Integer buildingId;

	public String getParkZoneName() {
		return parkZoneName;
	}

	public void setParkZoneName(String parkZoneName) {
		this.parkZoneName = parkZoneName;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

}
