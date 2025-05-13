package com.sbmtech.mms.payload.request;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.PaymentScheduleDetails;

@Repository
public interface PaymentScheduleDetailsRepository extends JpaRepository<PaymentScheduleDetails, Integer> {
    
    // Example custom method: find by tenant unit ID
    List<PaymentScheduleDetails> findByTenantUnit_TenantUnitId(Integer tenantUnitId);
}