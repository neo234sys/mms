package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmtech.mms.models.RentDueEntity;

public interface RentDueRepository extends JpaRepository<RentDueEntity, Long> {
}