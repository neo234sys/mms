package com.sbmtech.mms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Bedspace;

@Repository
public interface BedspaceRepository extends JpaRepository<Bedspace, Long>, JpaSpecificationExecutor<Bedspace> {	
	
	@Query("SELECT b FROM Bedspace b WHERE b.bedspaceId = :bedspaceId AND b.subscriber.subscriberId = :subscriberId")
	Optional<Bedspace> findByIdAndSubscriberId(@Param("bedspaceId") Long bedspaceId,
	                                           @Param("subscriberId") Integer subscriberId);
	
	
	 // 1️⃣ Get all bedspaces for single unit
    List<Bedspace> findByUnit_UnitId(Integer unitId);

    // 2️⃣ Get all bedspaces for multiple units
    List<Bedspace> findByUnit_UnitIdIn(List<Integer> unitIds);
}
