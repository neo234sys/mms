package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}