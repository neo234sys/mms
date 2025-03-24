package com.sbmtech.mms.payload.request;

public class DeleteBuildingRequest {

	private Integer buildingId;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	@Override
	public String toString() {
		return "DeleteBuildingRequest [buildingId=" + buildingId + "]";
	}

}
