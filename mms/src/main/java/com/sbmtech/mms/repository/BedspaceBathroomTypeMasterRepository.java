package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.BedspaceBathroomTypeMaster;

@Repository
public interface BedspaceBathroomTypeMasterRepository extends JpaRepository<BedspaceBathroomTypeMaster, Integer> {
}
