package com.sbmtech.mms.payload.request;

import javax.validation.constraints.NotEmpty;

import com.sbmtech.mms.validator.ValidSubscriberlId;

public class BuildingRequest {

	@NotEmpty(message = "buildingName must not be empty and null")
	private String buildingName;
	
	@NotEmpty(message = "address must not be empty and null")
	private String address;
	
	private byte[] buildingLogo; // BLOB field
	
	private Boolean hasGym;
	
	private Boolean hasSwimpool;
	
	private Boolean hasKidsPlayground;
	
	private Boolean hasPlaycourt;
	
	private Integer communityId;
	
	//@ValidSubscriberlId
	private Integer subscriberId;

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getBuildingLogo() {
		return buildingLogo;
	}

	public void setBuildingLogo(byte[] buildingLogo) {
		this.buildingLogo = buildingLogo;
	}

	public Boolean getHasGym() {
		return hasGym;
	}

	public void setHasGym(Boolean hasGym) {
		this.hasGym = hasGym;
	}

	public Boolean getHasSwimpool() {
		return hasSwimpool;
	}

	public void setHasSwimpool(Boolean hasSwimpool) {
		this.hasSwimpool = hasSwimpool;
	}

	public Boolean getHasKidsPlayground() {
		return hasKidsPlayground;
	}

	public void setHasKidsPlayground(Boolean hasKidsPlayground) {
		this.hasKidsPlayground = hasKidsPlayground;
	}

	public Boolean getHasPlaycourt() {
		return hasPlaycourt;
	}

	public void setHasPlaycourt(Boolean hasPlaycourt) {
		this.hasPlaycourt = hasPlaycourt;
	}

	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

}
