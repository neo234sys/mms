package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sbmtech.mms.models.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT SUM(t.amount) FROM TransactionEntity t WHERE t.order.orderId = :orderId")
    Double getTotalTransactionAmountByOrderId(@Param("orderId") Long orderId);
}