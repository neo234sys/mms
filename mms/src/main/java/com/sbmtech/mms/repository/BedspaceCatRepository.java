package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.BedspaceCatMaster;

@Repository
public interface BedspaceCatRepository extends JpaRepository<BedspaceCatMaster, Integer> {
    // Add custom queries if needed
}