package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidFutureDate;
import com.sbmtech.mms.validator.ValidSubscriberlId;

public class TenantUnitRequest {

	@Min(value=1, message="min value 1")
	private Integer tenantId;
	
	@Min(value=1, message="min value 1")
	private Integer unitId;
	
	@Min(value=1, message="min value 1")
	private Integer parkingId;
	

	
	@ValidDateDDMMYYYY
	@ValidFutureDate
	private String tenureFromDate;
	
	@Min(value=1, message="min value 1")
	private Double securityDeposit;
	
	@Min(value=1, message="min value 1")
	private Double rent;
	
	@NotEmpty(message = "rentCycle cannot be null")
	private String rentCycle;
	
	@NotEmpty(message = "rentPaymentMode cannot be null")
	private String rentPaymentMode;
	
	@ValidSubscriberlId
	private Integer subscriberId;

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


	

	public String getTenureFromDate() {
		return tenureFromDate;
	}

	public void setTenureFromDate(String tenureFromDate) {
		this.tenureFromDate = tenureFromDate;
	}

	public Double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public Double getRent() {
		return rent;
	}

	public void setRent(Double rent) {
		this.rent = rent;
	}

	public String getRentCycle() {
		return rentCycle;
	}

	public void setRentCycle(String rentCycle) {
		this.rentCycle = rentCycle;
	}

	public String getRentPaymentMode() {
		return rentPaymentMode;
	}

	public void setRentPaymentMode(String rentPaymentMode) {
		this.rentPaymentMode = rentPaymentMode;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

}
