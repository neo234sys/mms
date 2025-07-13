package com.sbmtech.mms.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonInclude(Include.NON_NULL)
public class BuildingDetailDTO {

	private Integer buildingId;
	private String buildingName;
	private String address;
	private Boolean hasGym;
	private Boolean hasSwimpool;
	private Boolean hasKidsPlayground;
	private Boolean hasPlaycourt;
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
	private List<FloorDTO> floors;
	private List<ParkingDTO> parkings;
	private List<ParkingZoneDTO> parkingZones;
	private List<UnitDTO> units;
	private List<BedspaceDTO> bedspaces;

}