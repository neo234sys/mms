package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sbmtech.mms.models.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {

	Subscriber findByCompanyEmail(String companyEmail);
	public boolean existsBySubscriberIdAndActive(Integer subscriberId,Integer active);
	
	
	//Total number of Building per subscriber
	@Query("SELECT COUNT(b) FROM Building b WHERE b.subscriber.subscriberId = :subscriberId AND b.isDeleted = false")
	public long countActiveBuildingsBySubscriberId(@Param("subscriberId") int subscriberId);
	
	
	
	//Total number of units per subscriber
	@Query("SELECT COUNT(u) FROM Unit u " +
		       "JOIN u.building b " +
		       "WHERE b.subscriber.subscriberId = :subscriberId AND u.isDeleted = false AND b.isDeleted = false")
	long countActiveUnitsBySubscriberId(@Param("subscriberId") int subscriberId);
	
	
	//Total number of Tenants per subscriber
	@Query("SELECT COUNT(tu) FROM TenantUnit tu " +
		       "JOIN tu.unit u " +
		       "JOIN u.building b " +
		       "WHERE tu.active = true " +
		       "AND tu.expired = false " +
		       "AND u.isDeleted = false " +
		       "AND b.isDeleted = false " +
		       "AND b.subscriber.subscriberId = :subscriberId")
	long countActiveTenantUnitsBySubscriberId(@Param("subscriberId") int subscriberId);
	
	
	
	//Total number of floors per Building
	@Query("SELECT COUNT(DISTINCT u.floor.floorName) FROM Unit u " +
		       "WHERE u.building.buildingId = :buildingId AND u.isDeleted = false")
	long countDistinctFloorsByBuildingId(@Param("buildingId") int buildingId);
	
	
	//Total number of units per Building
	@Query("SELECT COUNT(u) FROM Unit u " +
		       "WHERE u.building.buildingId = :buildingId AND u.isDeleted = false")
	long countUnitsByBuildingId(@Param("buildingId") int buildingId);
	
	
}