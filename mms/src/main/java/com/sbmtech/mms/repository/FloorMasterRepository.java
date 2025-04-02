package com.sbmtech.mms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.FloorMaster;

@Repository
public interface FloorMasterRepository extends JpaRepository<FloorMaster, String> {

	Optional<FloorMaster> findByFloorName(String floorName);

}