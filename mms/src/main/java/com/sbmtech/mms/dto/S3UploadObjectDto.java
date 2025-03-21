package com.sbmtech.mms.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class S3UploadObjectDto {
	
	private String objectName;
	private String contentType;
	private String fileExt;
	private String objectBase64;
	private String s3FileName;
	//Map<String,String> mapBase64Files; //Key-Object name, Value-base64

	public S3UploadObjectDto(String s3FileName){
		this.s3FileName=s3FileName;
	}
}
