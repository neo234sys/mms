package com.sbmtech.mms.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TenantDTO {

	private Integer tenantId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date dateOfBirth;
	private Long emiratesId;
	private Date eidaExpiryDate;
	private String eidaCopyFileLink;
	private String passportNo;
	private Date passportExpiryDate;
	private String passportCopyFileLink;
	private String photoFileLink;
	private Integer nationalityId;
	private String nationality;
	private Date createdTime;
	private Date updatedTime;
	private Integer createdBy;
	private Integer updatedBy;
	private String tenantStatus;
	private List<TenantUnitDTO> tenantUnits;


}
