package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmtech.mms.models.PaymentOrderEntity;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrderEntity, Long> {
    // You can define custom queries here if needed
}