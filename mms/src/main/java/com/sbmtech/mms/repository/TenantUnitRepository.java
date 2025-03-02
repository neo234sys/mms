package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.KeyMaster;
import com.sbmtech.mms.models.Tenant;
import com.sbmtech.mms.models.TenantUnit;
import com.sbmtech.mms.models.Unit;

@Repository
public interface TenantUnitRepository extends JpaRepository<TenantUnit, Integer> {
	

	
	boolean existsByTenantAndUnit(Tenant tenant, Unit unit);
	
	public TenantUnit findByUnit(Unit unit);

}
