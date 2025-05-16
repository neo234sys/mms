package com.sbmtech.mms.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sbmtech.mms.models.RentDueEntity;

public interface RentDueRepository extends JpaRepository<RentDueEntity, Long> {
	
	@Query("SELECT r FROM RentDueEntity r WHERE r.tenure.tenantTenureId = :tenureId")
	List<RentDueEntity> findByTenureId(@Param("tenureId") Integer tenureId);
	
	
	@Query(" SELECT r FROM RentDueEntity r WHERE r.tenure.tenantTenureId = :tenureId AND r.paymentPurpose.purposeId = :purposeId")
	List<RentDueEntity> findByTenureIdAndPaymentPurposeId(@Param("tenureId") Integer tenureId,@Param("purposeId") Integer purposeId);
	
	@Query("SELECT r FROM RentDueEntity r WHERE r.rentDueId = :rentDueId AND r.tenure.tenantTenureId = :tenureId ")
	RentDueEntity findByRentDueIdAndTenureId(@Param("rentDueId") Long rentDueId,@Param("tenureId") Integer tenureId);
	
	
	@Query(" SELECT r FROM RentDueEntity r  WHERE r.rentDueId IN :rentDueIds  AND r.tenure.tenantTenureId = :tenureId")
	List<RentDueEntity> findByRentDueIdsAndTenureId(@Param("rentDueIds") List<Long> rentDueIds,@Param("tenureId") Integer tenureId);
}