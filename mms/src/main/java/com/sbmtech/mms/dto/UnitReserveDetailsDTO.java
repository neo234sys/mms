package com.sbmtech.mms.dto;

import java.util.Date;

public class UnitReserveDetailsDTO {

	private Integer unitReserveId;
	private Date reserveStartDate;
	private Date reserveEndDate;
	private Integer paymentRequired;
	private UserSimpleDTO reservedBy;

	public Integer getUnitReserveId() {
		return unitReserveId;
	}

	public void setUnitReserveId(Integer unitReserveId) {
		this.unitReserveId = unitReserveId;
	}

	public Date getReserveStartDate() {
		return reserveStartDate;
	}

	public void setReserveStartDate(Date reserveStartDate) {
		this.reserveStartDate = reserveStartDate;
	}

	public Date getReserveEndDate() {
		return reserveEndDate;
	}

	public void setReserveEndDate(Date reserveEndDate) {
		this.reserveEndDate = reserveEndDate;
	}

	public Integer getPaymentRequired() {
		return paymentRequired;
	}

	public void setPaymentRequired(Integer paymentRequired) {
		this.paymentRequired = paymentRequired;
	}

	public UserSimpleDTO getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(UserSimpleDTO reservedBy) {
		this.reservedBy = reservedBy;
	}

	@Override
	public String toString() {
		return "UnitReserveDetailsDTO [unitReserveId=" + unitReserveId + ", reserveStartDate=" + reserveStartDate
				+ ", reserveEndDate=" + reserveEndDate + ", paymentRequired=" + paymentRequired + ", reservedBy="
				+ reservedBy + "]";
	}

}
