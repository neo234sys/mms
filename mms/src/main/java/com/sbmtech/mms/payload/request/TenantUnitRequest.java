package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sbmtech.mms.validator.ValidCurrentAndFutureDate;
import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidFutureDate;

public class TenantUnitRequest {

	@Min(value = 1, message = "min value 1")
	private Integer tenantId;

	@Min(value = 1, message = "min value 1")
	private Integer unitId;

	private Integer parkingId;

	@Min(value = 1, message = "min value 1")
	private Double securityDeposit;

	@Min(value = 1, message = "min value 1")
	private Double rent;

	@Min(value = 1, message = "min value 1")
	private Integer tenurePeriodMonth;

	@NotNull(message = "rentPaymentMode cannot be null")
	private Integer rentCycleId;

//	@NotNull(message = "rentPaymentMode cannot be null")
//	private Integer paymentModeId;

	@NotEmpty(message = "tenancyStartDate cannot be null")
	@ValidDateDDMMYYYY
	@ValidCurrentAndFutureDate
	private String tenancyStartDate;

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

	public Integer getTenurePeriodMonth() {
		return tenurePeriodMonth;
	}

	public void setTenurePeriodMonth(Integer tenurePeriodMonth) {
		this.tenurePeriodMonth = tenurePeriodMonth;
	}

	public Integer getRentCycleId() {
		return rentCycleId;
	}

	public void setRentCycleId(Integer rentCycleId) {
		this.rentCycleId = rentCycleId;
	}

//	public Integer getPaymentModeId() {
//		return paymentModeId;
//	}
//
//	public void setPaymentModeId(Integer paymentModeId) {
//		this.paymentModeId = paymentModeId;
//	}

	public String getTenancyStartDate() {
		return tenancyStartDate;
	}

	public void setTenancyStartDate(String tenancyStartDate) {
		this.tenancyStartDate = tenancyStartDate;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	@Override
	public String toString() {
		return "TenantUnitRequest [tenantId=" + tenantId + ", unitId=" + unitId + ", parkingId=" + parkingId
				+ ", securityDeposit=" + securityDeposit + ", rent=" + rent + ", tenurePeriodMonth=" + tenurePeriodMonth
				+ ", rentCycleId=" + rentCycleId +  ", tenancyStartDate="
				+ tenancyStartDate + ", subscriberId=" + subscriberId + "]";
	}

}
