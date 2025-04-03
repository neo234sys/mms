package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;

public class CommunityRequest {

	@NotEmpty(message = "communityName should not be empty")
	private String communityName;
	private Integer locationId;

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

	@Override
	public String toString() {
		return "CommunityRequest [communityName=" + communityName + ", locationId=" + locationId + "]";
	}

}
