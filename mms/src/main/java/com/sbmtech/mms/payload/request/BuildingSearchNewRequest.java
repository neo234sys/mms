package com.sbmtech.mms.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BuildingSearchNewRequest {

	private Integer subscriberId;
	private String keyword;
    private Boolean hasGym;
    private Integer areaId;
   // private UnitFilterNewRequest unitFilter;
    private PaginationRequest paginationRequest;

}
