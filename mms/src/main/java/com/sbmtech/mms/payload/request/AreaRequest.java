package com.sbmtech.mms.payload.request;

public class AreaRequest {

	private String locationName;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "AreaRequest [locationName=" + locationName + ", countryId=" + countryId + ", stateId=" + stateId
				+ ", cityId=" + cityId + "]";
	}

}