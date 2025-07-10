package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.BedspaceCatMaster;

@Repository
public interface BedspaceCatMasterRepository extends JpaRepository<BedspaceCatMaster, Integer> {
}
