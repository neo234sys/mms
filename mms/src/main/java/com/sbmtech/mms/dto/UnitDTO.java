package com.sbmtech.mms.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class UnitDTO {

	private Integer unitId;
	private String unitName;
	private String floorName;
	private String unitType;
	private String unitSubType;
	private String size;
	private Boolean hasBalcony;
	private String unitStatus;
	private Double rentMonth;
	private Double rentYear;
	private Double securityDeposit;
	private String waterConnNo;
	private String ebConnNo;
	private List<String> unitImages;
	private TenantSimpleDTO tenant;
	private UnitReserveDetailsDTO reservation;
	private List<UnitKeyDTO> keys;
	private List<BedspaceDTO> bedspaces;



	@Override
	public String toString() {
		return "UnitDTO [unitId=" + unitId + ", unitName=" + unitName + ", floorName=" + floorName + ", unitType="
				+ unitType + ", unitSubType=" + unitSubType + ", size=" + size + ", hasBalcony=" + hasBalcony
				+ ", unitStatus=" + unitStatus + ", rentMonth=" + rentMonth + ", rentYear=" + rentYear
				+ ", securityDeposit=" + securityDeposit + ", waterConnNo=" + waterConnNo + ", ebConnNo=" + ebConnNo
				+ ", unitImages=" + unitImages + ", tenant=" + tenant + ", reservation=" + reservation + ", keys="
				+ keys + "]";
	}

}
