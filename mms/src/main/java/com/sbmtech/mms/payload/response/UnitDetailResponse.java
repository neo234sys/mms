package com.sbmtech.mms.payload.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sbmtech.mms.dto.BedspaceDTO;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class UnitDetailResponse {

	private Integer unitId;
	private Integer buildingId;
	private String floor;
	private String unitName;
	private Integer unitTypeId;
	private String unitTypeName;
	private Integer unitSubTypeId;
	private String unitSubTypeName;
	private String size;
	private Boolean hasBalcony;
	private Integer unitStatusId;
	private String unitStatusName;
	private Double rentMonth;
	private Double rentYear;
	private Double securityDeposit;
	private String waterConnNo;
	private String ebConnNo;
	private String unitMainPic1Link;
	private String unitPic2Link;
	private String unitPic3Link;
	private String unitPic4Link;
	private String unitPic5Link;
	private TenantDetailResponse tenantDetails;
	//private BedspaceDetailResponse bsDetails;
	private List<BedspaceDTO> bedspaces;

	

	@Override
	public String toString() {
		return "UnitDetailResponse [unitId=" + unitId + ", buildingId=" + buildingId + ", floor=" + floor
				+ ", unitName=" + unitName + ", unitTypeId=" + unitTypeId + ", unitTypeName=" + unitTypeName
				+ ", unitSubTypeId=" + unitSubTypeId + ", unitSubTypeName=" + unitSubTypeName + ", size=" + size
				+ ", hasBalcony=" + hasBalcony + ", unitStatusId=" + unitStatusId + ", unitStatusName=" + unitStatusName
				+ ", rentMonth=" + rentMonth + ", rentYear=" + rentYear + ", securityDeposit=" + securityDeposit
				+ ", waterConnNo=" + waterConnNo + ", ebConnNo=" + ebConnNo + ", unitMainPic1Link=" + unitMainPic1Link
				+ ", unitPic2Link=" + unitPic2Link + ", unitPic3Link=" + unitPic3Link + ", unitPic4Link=" + unitPic4Link
				+ ", unitPic5Link=" + unitPic5Link + ", tenantDetails=" + tenantDetails + "]";
	}

}