package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmtech.mms.models.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	
	List<City> findByStateStateIdAndCountryCountryId(Integer stateId, Integer countryId);

}