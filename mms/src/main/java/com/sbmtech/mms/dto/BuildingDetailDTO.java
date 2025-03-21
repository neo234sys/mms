package com.sbmtech.mms.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BuildingDetailDTO {


	private Integer buildingId;
	private String buildingName;
	private String address;
	//private byte[] buildingLogo;
	private Boolean hasGym;
	private Boolean hasSwimpool;
	private Boolean hasKidsPlayground;
	private Boolean hasPlaycourt;
	//private CommunityDetailsDTO community;
	//private AreaDTO area;
	
	private Integer noOfFloors;
	private Integer noOfUnits;
	private String latitude;
	private String longitude;
	
	
	private Integer communityId;
	private String communityName;
	private Integer areaId;
	private String areaName;
	private Integer countryId;
	private String countryName;
	private Integer stateId;
	private String stateName;
	private Integer cityId;
	private String cityName;
	private String buildingLogoLink;
	
}
