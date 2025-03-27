package com.sbmtech.mms.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class ParkingZoneResponse {

	
	private Integer buildingId;
	private String buildingName;
	private Integer parkZoneId;
	private String parkZoneName;
	


}