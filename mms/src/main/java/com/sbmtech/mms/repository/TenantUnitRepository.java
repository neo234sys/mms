package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.KeyMaster;
import com.sbmtech.mms.models.TenantUnit;

@Repository
public interface TenantUnitRepository extends JpaRepository<TenantUnit, Integer> {
	
	@Query(value="SELECT B from KeyMaster B where B.keyName=?1 and B.subscriber.subscriberId =?2")
	public KeyMaster findByKeyNameAndSubscriberId(String keyName,Integer subscriberId);
	

}
