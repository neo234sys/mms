package com.sbmtech.mms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.ProductConfig;

@Repository
public interface ProductConfigRepository extends JpaRepository<ProductConfig, Integer> {

	
	
	
	@Query(value="SELECT PC from ProductConfig PC join Subscriber S ON PC.subscriber.subscriberId=S.subscriberId "
			+ " where PC.configName=?1 and PC.subscriber.subscriberId =?2")
	public ProductConfig findByCofigNameAndSubscriberId(String configName,Integer subscriberId);
}
