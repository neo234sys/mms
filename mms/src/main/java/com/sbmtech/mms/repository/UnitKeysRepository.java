package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.KeyMaster;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.models.UnitKeys;

@Repository
public interface UnitKeysRepository extends JpaRepository<UnitKeys, Integer> {

	boolean existsByUnitAndKeyMaster(Unit unit, KeyMaster keyMaster);

}