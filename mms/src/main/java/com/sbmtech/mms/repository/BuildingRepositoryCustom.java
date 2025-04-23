package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.dto.BuildingNewDTO;
import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.payload.request.UnitFilterNewRequest;

@Repository
public interface BuildingRepositoryCustom  {

	
	
	public Page<BuildingNewDTO> searchWithSpecAndReturnDTO(Specification<Building> spec, Pageable pageable) ;

}