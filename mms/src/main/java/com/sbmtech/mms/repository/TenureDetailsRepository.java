package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.TenantUnit;
import com.sbmtech.mms.models.TenureDetails;

@Repository
public interface TenureDetailsRepository extends JpaRepository<TenureDetails, Integer> {

	List<TenureDetails> findByTenantUnitTenantTenantId(Integer tenantId);

	List<TenureDetails> findByTenantUnit(TenantUnit tenantUnit);

}