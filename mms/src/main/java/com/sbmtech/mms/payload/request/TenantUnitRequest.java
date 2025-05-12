package com.sbmtech.mms.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sbmtech.mms.validator.ValidCurrentAndFutureDate;
import com.sbmtech.mms.validator.ValidDateDDMMYYYY;

public class TenantUnitRequest {

	@Min(value = 1, message = "min value 1")
	private Integer tenantId;

	@Min(value = 1, message = "min value 1")
	private Integer unitId;

	private Integer parkingId;

	//@Min(value = 1, message = "min value 1")
	private Double discount=0.0d;

	//@Min(value = 1, message = "min value 1")
	//private Double rent;

	@Min(value = 1, message = "min value 1")
	private Integer tenurePeriodMonth;

	@NotNull(message = "rentPaymentMode cannot be null")
	private Integer rentCycleId;

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

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "TenantUnitRequest [tenantId=" + tenantId + ", unitId=" + unitId + ", parkingId=" + parkingId
				+ ", tenurePeriodMonth=" + tenurePeriodMonth
				+ ", rentCycleId=" + rentCycleId + ", tenancyStartDate=" + tenancyStartDate + ", subscriberId="
				+ subscriberId + "]";
	}

}