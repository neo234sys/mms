package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbmtech.mms.models.Community;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

	
	@Query(value="SELECT C from Community C where C.communityId=?1 and C.subscriber.subscriberId =?2")
	public Community findByCommunityIdAndSubscriberId(Integer communityId,Integer subscriberId);
}