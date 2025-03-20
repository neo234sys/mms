package com.sbmtech.mms.payload.request;

public class DeleteUnitRequest {

	private Integer buildingId;
	private Integer subscriberId;
	private Integer unitId;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	@Override
	public String toString() {
		return "BuildingIdRequest [buildingId=" + buildingId + "]";
	}

}
