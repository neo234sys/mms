package com.sbmtech.mms.dto;

import com.sbmtech.mms.payload.request.PaginationRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BedspaceSearchCriteria {
	
	private PaginationRequest paginationRequest;
	
    private Integer subscriberId;
    private String area;
    private String city;
    private Integer areaId;
    private Integer cityId;
    private Integer buildingId;

    private Integer partitionId;
    private Integer bedspaceCategoryId;
    private Integer bedspaceBathroomTypeId;
    private String features;
    private String keyword;
    private String status;

    // ✅ NEW RENT FILTERS
    private Double minRentMonth;
    private Double maxRentMonth;

    private Double minRentDay;
    private Double maxRentDay;
    
   // private Integer page = 0;
   // private Integer size = 10;
    
    public String getStatus() {
    	return this.status.toLowerCase();
    }
    
}