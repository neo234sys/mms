package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.PaymentPurpose;

@Repository
public interface PaymentPurposeRepository extends JpaRepository<PaymentPurpose, Integer> {
	
	
		
   
}