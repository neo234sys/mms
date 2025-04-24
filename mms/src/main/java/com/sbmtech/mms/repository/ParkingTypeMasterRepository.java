package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.ParkingTypeMaster;

@Repository
public interface ParkingTypeMasterRepository extends JpaRepository<ParkingTypeMaster, String> {
	// Custom query methods can be defined here if needed
}