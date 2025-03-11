package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.UnitType;

@Repository
public interface UnitTypeRepository extends JpaRepository<UnitType, Integer> {
    // Custom query methods can be defined here if needed
}