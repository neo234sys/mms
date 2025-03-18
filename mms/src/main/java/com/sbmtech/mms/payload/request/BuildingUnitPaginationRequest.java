package com.sbmtech.mms.payload.request;

public class BuildingUnitPaginationRequest {

	private Integer buildingId;
	private PaginationRequest paginationRequest;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public PaginationRequest getPaginationRequest() {
		return paginationRequest;
	}

	public void setPaginationRequest(PaginationRequest paginationRequest) {
		this.paginationRequest = paginationRequest;
	}

	@Override
	public String toString() {
		return "BuildingUnitPaginationRequest [buildingId=" + buildingId + ", paginationRequest=" + paginationRequest
				+ "]";
	}

}
