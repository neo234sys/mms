package com.sbmtech.mms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.KeyMaster;

@Repository
public interface KeyMasterRepository extends JpaRepository<KeyMaster, Integer> {

	Optional<KeyMaster> findByKeyName(String keyName);
	
	@Query(value="SELECT B from KeyMaster B where B.keyName=?1 and B.subscriber.subscriberId =?2")
	public KeyMaster findByKeyNameAndSubscriberId(String keyName,Integer subscriberId);
	
	@Query(value="SELECT B from KeyMaster B where B.keyId=?1 and B.subscriber.subscriberId =?2")
	public KeyMaster findBykeyIdAndSubscriberId(Integer keyId,Integer subscriberId);
	
	@Query(value="SELECT B from KeyMaster B where B.subscriber.subscriberId =?1")
	public List<KeyMaster> findAllKeysBySubscriberId(Integer subscriberId);

}