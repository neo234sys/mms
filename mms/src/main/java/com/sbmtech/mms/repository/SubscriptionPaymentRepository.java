package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.SubscriptionPayment;

@Repository
public interface SubscriptionPaymentRepository extends JpaRepository<SubscriptionPayment, Integer> {
}