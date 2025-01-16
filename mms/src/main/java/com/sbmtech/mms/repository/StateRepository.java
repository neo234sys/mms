package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	List<State> findByCountryCountryId(Integer countryId);

}