package com.sbmtech.mms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.KeyMaster;

@Repository
public interface KeyMasterRepository extends JpaRepository<KeyMaster, Integer> {

	Optional<KeyMaster> findByKeyName(String keyName);

}