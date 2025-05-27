package com.sbmtech.mms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.RentDueEntity;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.models.UnitReserveDetails;

@Repository
public interface UnitReserveDetailsRepository extends JpaRepository<UnitReserveDetails, Integer> {

	@Query("SELECT urd FROM UnitReserveDetails urd WHERE urd.reserveEndDate < CURRENT_TIMESTAMP AND urd.unit.unitStatus.unitStatusName = 'RESERVED' and urd.unitReserveStatus='RESERVED'")
	List<UnitReserveDetails> findReservedUnitsWithPastReserveEndDate();
	
	
   @Query("SELECT urd FROM UnitReserveDetails urd WHERE urd.unit.unitId = :unitId AND :currentDate BETWEEN urd.reserveStartDate AND urd.reserveEndDate")
   List<UnitReserveDetails> findReservationsForDate(@Param("unitId") Integer unitId, @Param("currentDate") Date currentDate);


	List<UnitReserveDetails> findByUnitUnitId(Integer unitId);

	UnitReserveDetails findByUnit(Unit unit);

	@Query("SELECT urd FROM UnitReserveDetails urd WHERE urd.order.orderId = :orderId")
    UnitReserveDetails findByOrderId(@Param("orderId") Long orderId);
}