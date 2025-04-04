package com.sbmtech.mms.dto;

public class UnitKeyDTO {

	private Integer unitKeysId;
	private String keyName;

	public Integer getUnitKeysId() {
		return unitKeysId;
	}

	public void setUnitKeysId(Integer unitKeysId) {
		this.unitKeysId = unitKeysId;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	@Override
	public String toString() {
		return "UnitKeyDTO [unitKeysId=" + unitKeysId + ", keyName=" + keyName + "]";
	}

}
