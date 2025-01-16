package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.TenantUnit;

@Repository
public interface TenantUnitRepository extends JpaRepository<TenantUnit, Integer> {

}
