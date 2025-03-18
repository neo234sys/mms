package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidChannelId;
import com.sbmtech.mms.validator.ValidCurrentAndFutureDate;
import com.sbmtech.mms.validator.ValidDateDDMMYYYY;
import com.sbmtech.mms.validator.ValidPlanId;

public class SubscriptionRequest {

	@ValidPlanId
	private Integer planId;

	private Integer subscriberId;

	@NotBlank(message = "startDate cannot be null")
	@NotEmpty(message = "startDate cannot be null")
	@ValidDateDDMMYYYY
	@ValidCurrentAndFutureDate
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

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

}