package com.sbmtech.mms.repository;

import com.sbmtech.mms.models.SubscriberLocation;

public interface SubscriberLocationRepositoryCustom  {

	public SubscriberLocation findByLocationIdAndSubscriberId(Integer locationId,Integer subscriberId);
}
