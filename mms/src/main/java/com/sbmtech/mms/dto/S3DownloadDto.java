package com.sbmtech.mms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class S3DownloadDto {
	private Integer subscriberId;
	private Integer buildingId;
	private Integer unitId;
	private String s3FileName;
	
	

}
