package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Countries;


@Repository
public interface CountriesRepository extends JpaRepository<Countries, Integer> {

}