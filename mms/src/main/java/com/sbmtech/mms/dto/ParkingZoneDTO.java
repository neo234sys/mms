package com.sbmtech.mms.dto;

public class ParkingZoneDTO {

	private Integer parkZoneId;
	private String parkZoneName;

	public Integer getParkZoneId() {
		return parkZoneId;
	}

	public void setParkZoneId(Integer parkZoneId) {
		this.parkZoneId = parkZoneId;
	}

	public String getParkZoneName() {
		return parkZoneName;
	}

	public void setParkZoneName(String parkZoneName) {
		this.parkZoneName = parkZoneName;
	}

	@Override
	public String toString() {
		return "ParkingZoneDTO [parkZoneId=" + parkZoneId + ", parkZoneName=" + parkZoneName + "]";
	}

}
