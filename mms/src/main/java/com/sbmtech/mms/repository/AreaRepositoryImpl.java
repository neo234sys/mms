package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Area;


@Repository
public class AreaRepositoryImpl extends JdbcCommonDao  implements AreaRepositoryCustom {

	@Override
	public Area findByAreaIdAndSubscriberId(Integer areaId, Integer subscriberId) {
		@SuppressWarnings("unchecked")
		List<Area> list = this.getEm().createQuery("SELECT A from Area A "
				
				+ "where A.locationId=:locationId and A.subscriber.subscriberId =:subscriberId ")
				.setParameter("subscriberId", subscriberId)
				.setParameter("areaId", areaId)
				.getResultList();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}

	
}
