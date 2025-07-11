package com.sbmtech.mms.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class S3UploadDto {
	private Integer subscriberId;
	private Integer buildingId;
	private Integer unitId;
	private Long bsId;
	private String objectType; // bui
	private List<S3UploadObjectDto> s3UploadObjectDtoList;

}
