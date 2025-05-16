package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.TenantChequeDetails;

@Repository
public interface TenantChequeDetailsRepository extends JpaRepository<TenantChequeDetails, Long> {
	
	 @Query("SELECT c FROM TenantChequeDetails c WHERE c.tenantId = :tenantId AND c.tenantUnitId = :tenantUnitId")
	 TenantChequeDetails findCardDetailsByTenantIdAndTenantUnitId(@Param("tenantId") Integer tenantId,
	                                                              @Param("tenantUnitId") Integer tenantUnitId);
}