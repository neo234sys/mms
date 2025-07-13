package com.sbmtech.mms.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteBuildingRequest {

	private Integer buildingId;
	private Integer subscriberId;

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
