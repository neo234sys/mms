package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sbmtech.mms.models.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer>, JpaSpecificationExecutor<Tenant> {

	@Query(value = "SELECT T from Tenant T join User U ON T.tenantId=U.tenantId "
			+ " where T.tenantId=?1 and U.subscriber.subscriberId =?2")
	public Tenant findByTenantIdAndSubscriberId(Integer unitId, Integer subscriberId);

}