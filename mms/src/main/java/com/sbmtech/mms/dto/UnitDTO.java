package com.sbmtech.mms.dto;

import java.util.List;

public class UnitDTO {

	private Integer unitId;
	private String unitName;
	private String floorName;
	private String unitType;
	private String unitSubType;
	private String size;
	private Boolean hasBalcony;
	private String unitStatus;
	private Double rentMonth;
	private Double rentYear;
	private Double securityDeposit;
	private String waterConnNo;
	private String ebConnNo;
	private List<String> unitImages;
	private TenantSimpleDTO tenant;
	private UnitReserveDetailsDTO reservation;
	private List<UnitKeyDTO> keys;

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

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getUnitSubType() {
		return unitSubType;
	}

	public void setUnitSubType(String unitSubType) {
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

	public String getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(String unitStatus) {
		this.unitStatus = unitStatus;
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

	public List<String> getUnitImages() {
		return unitImages;
	}

	public void setUnitImages(List<String> unitImages) {
		this.unitImages = unitImages;
	}

	public TenantSimpleDTO getTenant() {
		return tenant;
	}

	public void setTenant(TenantSimpleDTO tenant) {
		this.tenant = tenant;
	}

	public UnitReserveDetailsDTO getReservation() {
		return reservation;
	}

	public void setReservation(UnitReserveDetailsDTO reservation) {
		this.reservation = reservation;
	}

	public List<UnitKeyDTO> getKeys() {
		return keys;
	}

	public void setKeys(List<UnitKeyDTO> keys) {
		this.keys = keys;
	}

	@Override
	public String toString() {
		return "UnitDTO [unitId=" + unitId + ", unitName=" + unitName + ", floorName=" + floorName + ", unitType="
				+ unitType + ", unitSubType=" + unitSubType + ", size=" + size + ", hasBalcony=" + hasBalcony
				+ ", unitStatus=" + unitStatus + ", rentMonth=" + rentMonth + ", rentYear=" + rentYear
				+ ", securityDeposit=" + securityDeposit + ", waterConnNo=" + waterConnNo + ", ebConnNo=" + ebConnNo
				+ ", unitImages=" + unitImages + ", tenant=" + tenant + ", reservation=" + reservation + ", keys="
				+ keys + "]";
	}

}
