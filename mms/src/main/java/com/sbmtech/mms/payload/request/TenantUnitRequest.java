package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidFutureDate;

public class TenantUnitRequest {

	@Min(value=1, message="min value 1")
	private Integer tenantId;
	
	@Min(value=1, message="min value 1")
	private Integer unitId;
	
	//@Min(value=1, message="min value 1")
	private Integer parkingId;
	

	
	@Min(value=1, message="min value 1")
	private Double securityDeposit;
	
	@Min(value=1, message="min value 1")
	private Double rent;
	
	@Min(value=1, message="min value 1")
	private Integer tenurePeriodMonth;
	
	@NotEmpty(message = "rentCycle cannot be null")
	private String rentCycle;
	
	@NotEmpty(message = "rentPaymentMode cannot be null")
	private String rentPaymentMode;
	
	@NotEmpty(message = "tenancyStartDate cannot be null")
	@ValidDateDDMMYYYY
	@ValidFutureDate
	private String tenancyStartDate;
	
	@NotEmpty(message = "reserved should not be empty")
    @Pattern(regexp = "^true$|^false$", message = "reserved: true or false")
    private String reserved;
	
	
	//@ValidSubscriberlId
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

	public Integer getTenurePeriodMonth() {
		return tenurePeriodMonth;
	}

	public void setTenurePeriodMonth(Integer tenurePeriodMonth) {
		this.tenurePeriodMonth = tenurePeriodMonth;
	}

	public String getTenancyStartDate() {
		return tenancyStartDate;
	}

	public void setTenancyStartDate(String tenancyStartDate) {
		this.tenancyStartDate = tenancyStartDate;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	
	
	

}
