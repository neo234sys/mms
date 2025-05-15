package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.PaymentOrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<PaymentOrderEntity, Integer> {

	//List<OrderEntity> findByTenantTenantId(Integer tenantId);
	
	
//	@Query("SELECT o FROM OrderEntity o WHERE o.rentDue.tenure.tenantUnit.id = :tenantUnitId")
//	List<OrderEntity> findOrdersByTenantUnitId(@Param("tenantUnitId") Integer tenantUnitId);
	
}