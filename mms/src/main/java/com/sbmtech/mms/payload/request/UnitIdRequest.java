package com.sbmtech.mms.payload.request;

public class UnitIdRequest {
	private Integer subscriberId;
	private Integer buildingId;
	private Integer unitId;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	@Override
	public String toString() {
		return "UnitIdRequest [unitId=" + unitId + "]";
	}

}
