package com.sbmtech.mms.models;

public enum S3UploadObjTypeEnum {
	
	BUILDING("BUILDING"),
	UNIT("UNIT"),
	MAINTAINANCE("MAINTAINANCE"),
	COMPANYLOGO("COMPANYLOGO"),
	COMPANYTRADELICENSE("COMPANYTRADELICENSE"),
	EID("EID"),
	PHOTO("PHOTO"),
	PASSPORT("PASSPORT"),
	CHEQUE("CHEQUE"),
	BS("BS");

	private String value;

	private S3UploadObjTypeEnum(String value) {

		this.value = value;
	}

	public String getValue() {

		return this.value;
	}
}