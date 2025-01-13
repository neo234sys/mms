package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.UserTypeMaster;

@Repository
public interface UserTypeMasterRepository extends JpaRepository<UserTypeMaster, Integer> {
}