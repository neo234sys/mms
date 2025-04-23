package com.sbmtech.mms.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnitFilterNewRequest {
	  private String floorName;
	    private String unitStatus;
	    private String unitNumberLike;
	    private String unitType;
	    private String unitSubtype;
}
