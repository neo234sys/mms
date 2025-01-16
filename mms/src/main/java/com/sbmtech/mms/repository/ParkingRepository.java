package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

}