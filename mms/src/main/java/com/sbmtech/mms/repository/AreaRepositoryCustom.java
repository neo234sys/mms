package com.sbmtech.mms.repository;

import com.sbmtech.mms.models.Area;

public interface AreaRepositoryCustom  {

	public Area findByAreaIdAndSubscriberId(Integer areaId,Integer subscriberId);
}
