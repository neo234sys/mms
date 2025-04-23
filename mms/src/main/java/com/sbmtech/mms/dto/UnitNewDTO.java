package com.sbmtech.mms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnitNewDTO {

	private Integer unitId;
	private String unitNumber;
	private String floorNumber;
	private String isAvailable;
	 private String unitType;
	 private String unitSubtype;
}
