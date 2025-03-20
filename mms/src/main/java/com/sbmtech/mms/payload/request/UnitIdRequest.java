package com.sbmtech.mms.payload.request;

public class UnitIdRequest {

	private Integer unitId;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	@Override
	public String toString() {
		return "UnitIdRequest [unitId=" + unitId + "]";
	}

}
