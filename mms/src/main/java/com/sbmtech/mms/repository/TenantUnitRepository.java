package com.sbmtech.mms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.sbmtech.mms.models.Tenant;
import com.sbmtech.mms.models.TenantUnit;
import com.sbmtech.mms.models.Unit;

@Repository
public interface TenantUnitRepository extends JpaRepository<TenantUnit, Integer> {

	boolean existsByTenantAndUnit(Tenant tenant, Unit unit);

	public TenantUnit findByUnit(Unit unit);

	@Query("SELECT tu FROM TenantUnit tu WHERE tu.tenant.isDeleted = false AND tu.unit.building.buildingId = :buildingId")
	Page<TenantUnit> findTenantsByBuildingIdWithPagination(@Param("buildingId") Integer buildingId, Pageable pageable);

	@Query("SELECT tu FROM TenantUnit tu WHERE tu.unit = :unit AND tu.active = true")
	Optional<TenantUnit> findByUnitAndActiveTrue(@Param("unit") Unit unit);

	@Query("SELECT tu FROM TenantUnit tu JOIN tu.unit u WHERE u.building.buildingId = :buildingId and u.unitId= :unitId "
			+ " and  u.building.subscriber.subscriberId= :subscriberId ")
	Optional<TenantUnit> findTenantsByUnitId(@Param("buildingId") Integer buildingId, @Param("unitId") Integer unitId,
			@Param("subscriberId") Integer subscriberId);

	List<TenantUnit> findByTenantTenantId(Integer tenantId);

	List<TenantUnit> findByUnitUnitId(Integer unitId);

	@Query(value = "SELECT DISTINCT tu FROM TenantUnit tu " + "LEFT JOIN FETCH tu.tenureDetails " + "JOIN tu.unit u "
			+ "JOIN u.building b " + "JOIN tu.tenant t " + "WHERE b.buildingId = :buildingId "
			+ "AND t.isDeleted = false " + "AND u.isDeleted = false "
			+ "AND (:tenantName IS NULL OR CONCAT(t.firstName, ' ', t.lastName) LIKE %:tenantName%) "
			+ "AND (:nationalityId IS NULL OR t.nationalityId = :nationalityId) "
			+ "AND (:unitName IS NULL OR u.unitName LIKE %:unitName%) "
			+ "AND (:unitId IS NULL OR u.unitId = :unitId)", countQuery = "SELECT COUNT(DISTINCT tu) FROM TenantUnit tu "
					+ "JOIN tu.unit u " + "JOIN u.building b " + "JOIN tu.tenant t "
					+ "WHERE b.buildingId = :buildingId " + "AND t.isDeleted = false " + "AND u.isDeleted = false "
					+ "AND (:tenantName IS NULL OR CONCAT(t.firstName, ' ', t.lastName) LIKE %:tenantName%) "
					+ "AND (:nationalityId IS NULL OR t.nationalityId = :nationalityId) "
					+ "AND (:unitName IS NULL OR u.unitName LIKE %:unitName%) "
					+ "AND (:unitId IS NULL OR u.unitId = :unitId)")
	Page<TenantUnit> findTenantsByBuildingIdWithFilters(@Param("buildingId") Integer buildingId,
			@Param("tenantName") String tenantName, @Param("nationalityId") Integer nationalityId,
			@Param("unitName") String unitName, @Param("unitId") Integer unitId, Pageable pageable);

}