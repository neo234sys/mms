package com.sbmtech.mms.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BedspaceRequest {
	
	private Long bedspaceId;
	
    private String bedspaceName;

    private Integer unitId;

    private Integer partitionId;

    private Integer bedspaceCatId;

    private Integer bedspaceBathroomTypeId;

    private Integer subscriberId;

    private Double securityDeposit;

    private Double rentMonth;

    private Double rentDay;

    private Boolean hasWardrobe;

    private Boolean hasKitchen;

    private String features;
    
    private byte[] bsMainPic1;
	private byte[] bsPic2;
	private byte[] bsPic3;
	private byte[] bsPic4;
	private byte[] bsPic5;
}