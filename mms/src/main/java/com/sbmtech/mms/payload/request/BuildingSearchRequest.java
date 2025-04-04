package com.sbmtech.mms.payload.request;

public class BuildingSearchRequest {

	private Integer buildingId;
	private String buildingName;
	private String cityName;
	private String areaName;
	private PaginationRequest paginationRequest;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public PaginationRequest getPaginationRequest() {
		return paginationRequest;
	}

	public void setPaginationRequest(PaginationRequest paginationRequest) {
		this.paginationRequest = paginationRequest;
	}

	@Override
	public String toString() {
		return "BuildingSearchRequest [buildingId=" + buildingId + ", buildingName=" + buildingName + ", cityName="
				+ cityName + ", areaName=" + areaName + ", paginationRequest=" + paginationRequest + "]";
	}

}
