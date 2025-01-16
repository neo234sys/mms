package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

}
