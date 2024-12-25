package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Nationality;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {

}