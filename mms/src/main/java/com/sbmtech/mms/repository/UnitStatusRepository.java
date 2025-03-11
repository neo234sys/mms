package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.UnitStatus;
import com.sbmtech.mms.models.UnitType;

@Repository
public interface UnitStatusRepository extends JpaRepository<UnitStatus, Integer> {

	
	
	public UnitStatus findByUnitStatusName(String unitStatus);
}