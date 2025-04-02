package com.sbmtech.mms.payload.request;

public class TenantFilterRequest extends PaginationRequest {

	private Integer buildingId;
	private String tenantName;
	private Integer nationalityId;
	private String unitName;
	private Integer unitId;
	private PaginationRequest paginationRequest;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public Integer getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public PaginationRequest getPaginationRequest() {
		return paginationRequest;
	}

	public void setPaginationRequest(PaginationRequest paginationRequest) {
		this.paginationRequest = paginationRequest;
	}

	@Override
	public String toString() {
		return "TenantFilterRequest [buildingId=" + buildingId + ", tenantName=" + tenantName + ", nationalityId="
				+ nationalityId + ", unitName=" + unitName + ", unitId=" + unitId + ", paginationRequest="
				+ paginationRequest + "]";
	}

}
