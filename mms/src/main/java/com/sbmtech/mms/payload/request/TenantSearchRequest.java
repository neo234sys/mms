package com.sbmtech.mms.payload.request;

public class TenantSearchRequest {

	private String search;
	private PaginationRequest paginationRequest;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public PaginationRequest getPaginationRequest() {
		return paginationRequest;
	}

	public void setPaginationRequest(PaginationRequest paginationRequest) {
		this.paginationRequest = paginationRequest;
	}

	@Override
	public String toString() {
		return "TenantSearchRequest [search=" + search + ", paginationRequest=" + paginationRequest + "]";
	}

}
