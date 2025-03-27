package com.sbmtech.mms.payload.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingDetailResponse {

	private Integer parkingId;

	private String parkingName;

	private Integer parkZoneId;

	private String parkZoneName;

	private Integer buildingId;
	private String buildingName;

	private String parkingType;

	private Boolean isAvailable = true;

}
