package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.SubscriptionPayment;
import com.sbmtech.mms.models.TenantChequeDetails;

@Repository
public interface TenantChequeDetailsRepository extends JpaRepository<TenantChequeDetails, Long> {
}