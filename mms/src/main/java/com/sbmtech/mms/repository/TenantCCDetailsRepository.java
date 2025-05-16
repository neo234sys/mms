package com.sbmtech.mms.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.TenantCCDetails;



@Repository
public interface TenantCCDetailsRepository extends JpaRepository<TenantCCDetails, Long> {
	
	@Query("SELECT t FROM TenantCCDetails t WHERE t.tenantId = :tenantId AND t.tenantUnitId = :tenantUnitId")
    TenantCCDetails findCardByTenantIdAndTenantUnitId(@Param("tenantId") Integer tenantId,
                                                                 @Param("tenantUnitId") Integer tenantUnitId);
}