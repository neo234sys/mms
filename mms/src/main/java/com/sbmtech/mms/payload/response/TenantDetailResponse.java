package com.sbmtech.mms.payload.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class TenantDetailResponse {

	private Integer tenantId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date dateOfBirth;
	private Long emiratesId;
	private Date eidaExpiryDate;
	private String passportNo;
	private Date passportExpiryDate;
	private Integer nationalityId;
	private Integer unitId;
	private String unitName;
	private Integer buildingId;
	private String buildingName;
	private Integer parkingId;
	private String parkingName;
	private String parkingType;
	private Integer parkingZoneId;
	private String parkingZoneName;


}