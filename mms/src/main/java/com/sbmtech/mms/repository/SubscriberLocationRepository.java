package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmtech.mms.models.SubscriberLocation;

public interface SubscriberLocationRepository extends JpaRepository<SubscriberLocation, Integer> {

	//public SubscriberLocation findByLocationIdAndSubscriberId(Integer locationId,Integer subscriberId);
}
