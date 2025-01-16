package com.sbmtech.mms.payload.request;

import java.math.BigDecimal;
import java.util.Date;

import com.sbmtech.mms.models.RentCycle;
import com.sbmtech.mms.models.RentPaymentMode;

public class TenantUnitRequest {

	private Integer tenantId;
	private Integer unitId;
	private Integer parkingId;
	private Integer unitKeysId;
	private Date registeredDate;
	private BigDecimal securityDeposit;
	private BigDecimal rent;
	private RentCycle rentCycle;
	private RentPaymentMode rentPaymentMode;

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public Integer getUnitKeysId() {
		return unitKeysId;
	}

	public void setUnitKeysId(Integer unitKeysId) {
		this.unitKeysId = unitKeysId;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public BigDecimal getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(BigDecimal securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public RentCycle getRentCycle() {
		return rentCycle;
	}

	public void setRentCycle(RentCycle rentCycle) {
		this.rentCycle = rentCycle;
	}

	public RentPaymentMode getRentPaymentMode() {
		return rentPaymentMode;
	}

	public void setRentPaymentMode(RentPaymentMode rentPaymentMode) {
		this.rentPaymentMode = rentPaymentMode;
	}

}
