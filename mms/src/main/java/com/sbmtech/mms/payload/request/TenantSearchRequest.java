package com.sbmtech.mms.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TenantSearchRequest {

	private String search;
	private String status;
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
