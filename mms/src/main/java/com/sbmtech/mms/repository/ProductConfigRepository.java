package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.ProductConfig;

@Repository
public interface ProductConfigRepository extends JpaRepository<ProductConfig, Integer> {
	ProductConfig findBySubscriberId(Integer subscriberId);
}