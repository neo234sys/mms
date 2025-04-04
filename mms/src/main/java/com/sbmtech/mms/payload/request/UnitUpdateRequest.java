package com.sbmtech.mms.payload.request;

import com.sbmtech.mms.models.UnitSubType;
import com.sbmtech.mms.models.UnitType;

public class UnitUpdateRequest {

	private Integer unitId;
	private String unitName;
	private UnitType unitType;
	private UnitSubType unitSubType;
	private String size;
	private Boolean hasBalcony;
	private Double rentMonth;
	private Double rentYear;
	private Double securityDeposit;
	private String waterConnNo;
	private String ebConnNo;
	private Integer subscriberId;
	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	public UnitSubType getUnitSubType() {
		return unitSubType;
	}

	public void setUnitSubType(UnitSubType unitSubType) {
		this.unitSubType = unitSubType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Boolean getHasBalcony() {
		return hasBalcony;
	}

	public void setHasBalcony(Boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

	public Double getRentMonth() {
		return rentMonth;
	}

	public void setRentMonth(Double rentMonth) {
		this.rentMonth = rentMonth;
	}

	public Double getRentYear() {
		return rentYear;
	}

	public void setRentYear(Double rentYear) {
		this.rentYear = rentYear;
	}

	public Double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public String getWaterConnNo() {
		return waterConnNo;
	}

	public void setWaterConnNo(String waterConnNo) {
		this.waterConnNo = waterConnNo;
	}

	public String getEbConnNo() {
		return ebConnNo;
	}

	public void setEbConnNo(String ebConnNo) {
		this.ebConnNo = ebConnNo;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	@Override
	public String toString() {
		return "UnitUpdateRequest [unitId=" + unitId + ", unitName=" + unitName + ", unitType=" + unitType
				+ ", unitSubType=" + unitSubType + ", size=" + size + ", hasBalcony=" + hasBalcony + ", rentMonth="
				+ rentMonth + ", rentYear=" + rentYear + ", securityDeposit=" + securityDeposit + ", waterConnNo="
				+ waterConnNo + ", ebConnNo=" + ebConnNo + "]";
	}

}
