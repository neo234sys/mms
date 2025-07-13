package com.sbmtech.mms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FloorDTO {

	private Integer floorId;
	private String floorName;

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	@Override
	public String toString() {
		return "FloorDTO [floorId=" + floorId + ", floorName=" + floorName + "]";
	}

}
