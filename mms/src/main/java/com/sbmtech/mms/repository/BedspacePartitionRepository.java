package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.BedspacePartitionMaster;

@Repository
public interface BedspacePartitionRepository extends JpaRepository<BedspacePartitionMaster, Integer> {
    // You can add custom query methods if needed
}