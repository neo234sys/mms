package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.PaymentMode;
import com.sbmtech.mms.models.UnitType;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode, Integer> {
    // Custom query methods can be defined here if needed
}