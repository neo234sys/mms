package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmtech.mms.models.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {

}