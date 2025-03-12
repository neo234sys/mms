package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.UnitReserveDetails;

@Repository
public interface UnitReserveDetailsRepository extends JpaRepository<UnitReserveDetails, Long> {

	@Query("SELECT urd FROM UnitReserveDetails urd WHERE urd.reserveEndDate < CURRENT_TIMESTAMP AND urd.unit.unitStatus.unitStatusName = 'RESERVED'")
	List<UnitReserveDetails> findReservedUnitsWithPastReserveEndDate();

}