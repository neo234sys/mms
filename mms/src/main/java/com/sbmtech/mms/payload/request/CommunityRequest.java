package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidSubscriberlId;

public class CommunityRequest {

	@NotEmpty(message = "communityName should not be empty")
	private String communityName;
	private Integer locationId;
	
	@ValidSubscriberlId
	private Integer subscriberId;

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

}
