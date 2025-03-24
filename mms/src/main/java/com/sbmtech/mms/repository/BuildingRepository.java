package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

	@Query(value = "SELECT B from Building B where B.buildingId=?1 and B.subscriber.subscriberId =?2")
	public Building findByBuildingIdAndSubscriberId(Integer buildingId, Integer subscriberId);

	Page<Building> findAll(Pageable pageable);

	@Query("SELECT b FROM Building b WHERE b.subscriber.id = :subscriberId AND b.isDeleted = false")
	List<Building> findAllBySubscriberIdAndIsDeletedFalse(@Param("subscriberId") Integer subscriberId);

}