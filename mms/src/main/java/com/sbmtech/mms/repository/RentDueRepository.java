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
	
	
	@Query("SELECT r FROM RentDueEntity r " +
		       "WHERE r.tenure.tenantTenureId = :tenureId " +
		       "AND r.subscriber.subscriberId = :subscriberId " +
		       "AND r.active = :active")
	List<RentDueEntity> findByTenureIdAndSubscriberIdAndActive(
		        @Param("tenureId") Integer tenureId,
		        @Param("subscriberId") Integer subscriberId,
		        @Param("active") Integer active
		);
	
	
	
	
	@Query("SELECT r FROM RentDueEntity r " +
		       "WHERE r.tenure.tenantTenureId = :tenureId " +
		       "AND r.subscriber.subscriberId = :subscriberId " +
		       "AND r.active = 1")
	List<RentDueEntity> getActiveRentDues(@Param("tenureId") Integer tenureId,
		                                      @Param("subscriberId") Integer subscriberId);
	
	
	@Query("SELECT r FROM RentDueEntity r WHERE r.rentDueId IN :ids")
	List<RentDueEntity> findRentDuesByIds(@Param("ids") List<Long> ids);
	
	@Query("SELECT COUNT(DISTINCT r.paymentMode.paymentModeId) FROM RentDueEntity r WHERE r.rentDueId IN :ids")
	Long countDistinctPaymentModes(@Param("ids") List<Long> ids);
	
	@Query("SELECT COUNT(DISTINCT r.paymentPurpose.purposeId) FROM RentDueEntity r WHERE r.rentDueId IN :ids")
	Long countDistinctPaymentPurpose(@Param("ids") List<Long> ids);
	
	@Query("SELECT SUM(r.amount) FROM RentDueEntity r WHERE r.order.orderId = :orderId")
	Double getTotalAmountByOrderIdFromRentDue(@Param("orderId") Long orderId);
	
	@Query("SELECT r FROM RentDueEntity r WHERE r.order.orderId = :orderId")
    List<RentDueEntity> findByOrderId(@Param("orderId") Long orderId);
}