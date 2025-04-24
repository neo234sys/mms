package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.UnitSubType;

@Repository
public interface UnitSubTypeRepository extends JpaRepository<UnitSubType, Integer> {

	public List<UnitSubType> findByUnitTypeUnitTypeId(Integer unitTypeId);

}