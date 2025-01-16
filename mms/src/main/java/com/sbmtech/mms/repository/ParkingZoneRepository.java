package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.ParkingZone;

@Repository
public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Integer> {

}
