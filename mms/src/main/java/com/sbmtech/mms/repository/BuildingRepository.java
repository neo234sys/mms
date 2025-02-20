package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

	@Query(value="SELECT B from Building B where B.buildingId=?1 and B.subscriber.subscriberId =?2")
	public Building findByBuildingIdAndSubscriberId(Integer buildingId,Integer subscriberId);
}
