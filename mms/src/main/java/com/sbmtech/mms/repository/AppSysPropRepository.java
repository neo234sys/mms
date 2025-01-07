package com.sbmtech.mms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.AppProperties;

@Repository
public interface AppSysPropRepository extends JpaRepository<AppProperties, Integer> {

}
