package com.sbmtech.mms.payload.request;

public class BuildingUpdateRequest {

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
	private byte[] buildingLogo;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getHasGym() {
		return hasGym;
	}

	public void setHasGym(Boolean hasGym) {
		this.hasGym = hasGym;
	}

	public Boolean getHasSwimpool() {
		return hasSwimpool;
	}

	public void setHasSwimpool(Boolean hasSwimpool) {
		this.hasSwimpool = hasSwimpool;
	}

	public Boolean getHasKidsPlayground() {
		return hasKidsPlayground;
	}

	public void setHasKidsPlayground(Boolean hasKidsPlayground) {
		this.hasKidsPlayground = hasKidsPlayground;
	}

	public Boolean getHasPlaycourt() {
		return hasPlaycourt;
	}

	public void setHasPlaycourt(Boolean hasPlaycourt) {
		this.hasPlaycourt = hasPlaycourt;
	}

	public Integer getNoOfFloors() {
		return noOfFloors;
	}

	public void setNoOfFloors(Integer noOfFloors) {
		this.noOfFloors = noOfFloors;
	}

	public Integer getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(Integer noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "BuildingUpdateRequest [buildingId=" + buildingId + ", buildingName=" + buildingName + ", address="
				+ address + ", hasGym=" + hasGym + ", hasSwimpool=" + hasSwimpool + ", hasKidsPlayground="
				+ hasKidsPlayground + ", hasPlaycourt=" + hasPlaycourt + ", noOfFloors=" + noOfFloors + ", noOfUnits="
				+ noOfUnits + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
