package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.TenureDetails;

@Repository
public interface TenureDetailsRepository extends JpaRepository<TenureDetails, Integer> {
	

	
//	boolean existsByTenantAndUnit(Tenant tenant, Unit unit);

}
