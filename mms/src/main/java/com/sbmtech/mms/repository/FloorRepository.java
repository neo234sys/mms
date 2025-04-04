package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.Floor;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {

	List<Floor> findByBuildingBuildingId(Integer buildingId);

	List<Floor> findByBuilding(Building building);

}