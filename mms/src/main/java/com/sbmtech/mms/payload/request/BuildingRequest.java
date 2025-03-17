package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingRequest {
	
	
	
	private Integer countryId;
	private Integer stateId;
	//private Integer cityId;
	
	
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
	
	private Boolean hasGym;
	
	private Boolean hasSwimpool;
	
	private Boolean hasKidsPlayground;
	
	private Boolean hasPlaycourt;
	

	private Integer subscriberId;
	
	private String latitude;
	private String longitude;
	
	@Min(value=1, message="noOfFloors min value should 1")
	@NotNull(message = "noOfFloors field cannot be null")
	private Integer noOfFloors;
	
	@Min(value=1, message="noOfunits min value should 1")
	@NotNull(message = "noOfunits field cannot be null")
	private Integer noOfunits;

	

}
