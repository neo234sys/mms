package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BSBuildingRequest {

	private Integer countryId;
	private Integer stateId;
	private Integer buildingId;

	@NotEmpty(message = "cityName must not be empty and null")
	private String cityName;

	@NotEmpty(message = "areaName must not be empty and null")
	private String areaName;

	@NotEmpty(message = "buildingName must not be empty and null")
	private String buildingName;

	private String communityName;

	@NotEmpty(message = "address must not be empty and null")
	private String address;

	private byte[] buildingLogo; // BLOB field

	

	private Integer subscriberId;

	private String latitude;
	private String longitude;

	
}
