package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.SubscriptionPlanMaster;

@Repository
public interface SubscriptionPlanMasterRepository extends JpaRepository<SubscriptionPlanMaster, Integer> {
	
	public boolean existsByPlanIdAndActive(Integer planId,Integer active);
}
