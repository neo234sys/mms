package com.sbmtech.mms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.dto.BuildingNewDTO;
import com.sbmtech.mms.models.Building;

@Repository
public interface BuildingRepositoryCustom {

	public Page<BuildingNewDTO> searchWithSpecAndReturnDTO(Specification<Building> spec, Pageable pageable);

}