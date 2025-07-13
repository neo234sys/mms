package com.sbmtech.mms.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteBedspaceRequest {

	private Long bedspaceId;
	private Integer subscriberId;

	

	@Override
	public String toString() {
		return "DeleteBedspaceRequest [bedspaceId=" + bedspaceId + "]";
	}

}
