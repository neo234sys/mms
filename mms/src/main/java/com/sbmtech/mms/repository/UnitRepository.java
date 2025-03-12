package com.sbmtech.mms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

	@Query(value = "SELECT U from Unit U join Building B ON B.buildingId=U.building.buildingId "
			+ " where U.unitId=?1 and B.subscriber.subscriberId =?2")
	public Unit findByUnitIdAndSubscriberId(Integer unitId, Integer subscriberId);

	Optional<Unit> findByUnitId(Integer unitId);

}