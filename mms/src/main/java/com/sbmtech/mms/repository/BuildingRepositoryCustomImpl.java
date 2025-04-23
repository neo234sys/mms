package com.sbmtech.mms.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.dto.BuildingDetailDTO;
import com.sbmtech.mms.dto.BuildingNewDTO;
import com.sbmtech.mms.dto.UnitNewDTO;
import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.payload.request.UnitFilterNewRequest;
import com.sbmtech.mms.util.PaginationUtils;

@Repository
public class BuildingRepositoryCustomImpl extends JdbcCommonDao implements BuildingRepositoryCustom {

	@Override
	public Page<BuildingNewDTO> searchWithSpecAndReturnDTO(Specification<Building> spec, Pageable pageable) {

		  CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
		    CriteriaQuery<Building> query = cb.createQuery(Building.class);
		    Root<Building> root = query.from(Building.class);
		    root.fetch("area", JoinType.LEFT); // fetch area for efficiency

		    query.select(root).where(spec.toPredicate(root, query, cb));

		    List<Building> buildings = this.getEm().createQuery(query)
		        .setFirstResult((int) pageable.getOffset())
		        .setMaxResults(pageable.getPageSize())
		        .getResultList();

		    // Map buildings to DTOs and apply unit filters with pagination
		    List<BuildingNewDTO> buildingDTOs = buildings.stream().map(building -> {
		        // Fetch paginated units for each building with filters
//		        Pageable unitPageable = PageRequest.of(0, Integer.MAX_VALUE); // You can adjust this size
//		        Page<UnitNewDTO> unitPage = getFilteredUnitsForBuilding(building.getBuildingId(), unitFilter, unitPageable);

		        // Map building to DTO including the filtered units
		       // List<UnitNewDTO> units = unitPage.getContent();

		        return new BuildingNewDTO(
		            building.getBuildingId(),
		            building.getBuildingName(),
		            building.getAddress(),
		            building.getLatitude(),
		            building.getLongitude(),
		            building.getArea() != null ? building.getArea().getAreaName() : null,
		            //units // Nested units
		            		null
		        );
		    }).collect(Collectors.toList());

		    // Count query for pagination
		    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		    Root<Building> countRoot = countQuery.from(Building.class);
		    countQuery.select(cb.count(countRoot)).where(spec.toPredicate(countRoot, countQuery, cb));
		    long total = this.getEm().createQuery(countQuery).getSingleResult();

		    //return new PageImpl<>(buildingDTOs, pageable, total);
		   // convertListToPage
		    Page<BuildingNewDTO> pageBD = PaginationUtils.convertListToPage(buildingDTOs, pageable);
		    return pageBD;
	}
	
	
	public Page<UnitNewDTO> getFilteredUnitsForBuilding(Integer buildingId, UnitFilterNewRequest unitFilter, Pageable pageable) {
	    CriteriaBuilder cb =  this.getEm().getCriteriaBuilder();
	    CriteriaQuery<Unit> unitQuery = cb.createQuery(Unit.class);
	    Root<Unit> unitRoot = unitQuery.from(Unit.class);

	    List<Predicate> predicates = new ArrayList<>();

	    // Filter by building
	    predicates.add(cb.equal(unitRoot.get("building").get("buildingId"), buildingId));

	    // Filter by floor name
	    if (StringUtils.isNoneBlank(unitFilter.getFloorName())) {
	        predicates.add(cb.equal(unitRoot.get("floor").get("floorName"), unitFilter.getFloorName()));
	    }

	    // Filter by unit status --vacant, occupied
	    if (StringUtils.isNoneBlank(unitFilter.getUnitStatus())) {
	        predicates.add(cb.equal(unitRoot.get("unitStatus").get("unitStatusName"), unitFilter.getUnitStatus() ));
	    }

	    // Filter by unit number
//	    if (unitFilter.getUnitNumberLike() != null) {
//	        predicates.add(cb.like(cb.lower(unitRoot.get("unitNumber")), "%" + unitFilter.getUnitNumberLike().toLowerCase() + "%"));
//	    }
	    
	    
	    // Filter by unit type (e.g., villa, Apartment, commercial)
	    if (StringUtils.isNoneBlank(unitFilter.getUnitType())) {
	        predicates.add(cb.equal(unitRoot.get("unitType").get("unitTypeName"), unitFilter.getUnitType()));
	    }
	    
	    // Filter by unit type (e.g., studio, 1BHK, 2BHK)
	    if (StringUtils.isNoneBlank(unitFilter.getUnitSubtype())) {
	        predicates.add(cb.equal(unitRoot.get("unitSubType").get("unitSubtypeName"), unitFilter.getUnitSubtype()));
	    }

	    unitQuery.select(unitRoot).where(cb.and(predicates.toArray(new Predicate[0])));

	    // Paginated query for units
	    List<Unit> units = this.getEm().createQuery(unitQuery)
	        .setFirstResult((int) pageable.getOffset())
	        .setMaxResults(pageable.getPageSize())
	        .getResultList();

	    // Convert units to DTOs
	    List<UnitNewDTO> unitDTOs = units.stream()
	        .map(unit -> new UnitNewDTO(unit.getUnitId(), unit.getUnitName() , unit.getFloor().getFloorName() , unit.getUnitStatus().getUnitStatusName() , unit.getUnitType().getUnitTypeName() ,
	        		unit.getUnitSubType().getUnitSubtypeName()))
	        .collect(Collectors.toList());

	    // Count query for pagination
	    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
	    Root<Unit> countRoot = countQuery.from(Unit.class);
	    countQuery.select(cb.count(countRoot)).where(cb.and(predicates.toArray(new Predicate[0])));
	    long total = this.getEm().createQuery(countQuery).getSingleResult();

	    return new PageImpl<>(unitDTOs, pageable, total);
	}

}
