package com.sbmtech.mms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.Unit;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("rawtypes")
@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer>, JpaSpecificationExecutor {

	@Query(value = "SELECT U from Unit U join Building B ON B.buildingId=U.building.buildingId "
			+ " where U.unitId=?1 and B.subscriber.subscriberId =?2")
	public Unit findByUnitIdAndSubscriberId(Integer unitId, Integer subscriberId);

	Optional<Unit> findByUnitId(Integer unitId);

	@Query("SELECT u FROM Unit u WHERE u.building.buildingId = :buildingId AND u.building.subscriber.subscriberId = :subscriberId AND u.isDeleted = false")
	Page<Unit> findUnitsByBuildingIdWithPagination(@Param("buildingId") Integer buildingId,
			@Param("subscriberId") Integer subscriberId, Pageable pageable);

	@Query("SELECT COUNT(u) > 0 FROM Unit u WHERE u.building.buildingId = :buildingId")
	boolean existsByBuildingBuildingId(@Param("buildingId") Integer buildingId);

	@Query("SELECT u FROM Unit u WHERE u.building.buildingId = :buildingId")
	List<Unit> getAllUnitsByBuildingId(@Param("buildingId") Integer buildingId);

	List<Unit> findByBuildingBuildingId(Integer buildingId);

	List<Unit> findByBuildingAndIsDeletedFalse(Building building);

}