package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidSubscriberlId;

public class ParkingZoneRequest {

	@NotEmpty(message = "parkZoneName cannot be null")
	private String parkZoneName;
	
	//@ValidSubscriberlId
	private Integer subscriberId;

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

}
