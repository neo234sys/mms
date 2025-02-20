package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.SubscriberLocation;

@Repository
public class SubscriberLocationRepositoryImpl extends JdbcCommonDao  implements SubscriberLocationRepositoryCustom {

	@Override
	public SubscriberLocation findByLocationIdAndSubscriberId(Integer locationId, Integer subscriberId) {
		@SuppressWarnings("unchecked")
		List<SubscriberLocation> list = this.getEm().createQuery("SELECT SL from SubscriberLocation SL "
				
				+ "where SL.locationId=:locationId and SL.subscriber.subscriberId =:subscriberId ")
				.setParameter("subscriberId", subscriberId)
				.setParameter("locationId", locationId)
				.getResultList();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}

	
}
