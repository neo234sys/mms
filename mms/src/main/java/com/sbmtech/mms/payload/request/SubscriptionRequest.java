package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidChannelId;
import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidFutureDate;
import com.sbmtech.mms.validator.ValidPlanId;
import com.sbmtech.mms.validator.ValidSubscriberlId;

public class SubscriptionRequest {

	@ValidPlanId
	private Integer planId;
	
	@ValidSubscriberlId
	private Integer subscriberId;
	
	@NotBlank (message = "startDate cannot be null")
	@NotEmpty(message = "startDate cannot be null")
	@ValidDateDDMMYYYY
	@ValidFutureDate
	private String startDate;
	
	
	@ValidChannelId
	private Integer channelId;

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
//
//	public LocalDateTime getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(LocalDateTime endDate) {
//		this.endDate = endDate;
//	}

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

//	public Boolean getIsFree() {
//		return isFree;
//	}
//
//	public void setIsFree(Boolean isFree) {
//		this.isFree = isFree;
//	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

}
