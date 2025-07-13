package com.sbmtech.mms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ParkingDTO {

	private Integer parkingId;
	private String parkingName;
	private String parkingType;
	private Boolean isAvailable;
	private ParkingZoneSimpleDTO parkZone;

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public String getParkingType() {
		return parkingType;
	}

	public void setParkingType(String parkingType) {
		this.parkingType = parkingType;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public ParkingZoneSimpleDTO getParkZone() {
		return parkZone;
	}

	public void setParkZone(ParkingZoneSimpleDTO parkZone) {
		this.parkZone = parkZone;
	}

	@Override
	public String toString() {
		return "ParkingDTO [parkingId=" + parkingId + ", parkingName=" + parkingName + ", parkingType=" + parkingType
				+ ", isAvailable=" + isAvailable + ", parkZone=" + parkZone + "]";
	}

}
