package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Custom query methods can be added here if needed
}