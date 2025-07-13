package com.sbmtech.mms.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteUnitRequest {

	private Integer unitId;
	private Integer subscriberId;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	@Override
	public String toString() {
		return "DeleteUnitRequest [unitId=" + unitId + "]";
	}

}
