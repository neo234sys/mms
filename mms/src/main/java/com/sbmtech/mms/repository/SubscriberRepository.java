package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmtech.mms.models.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {

	Subscriber findByCompanyEmail(String companyEmail);
	public boolean existsBySubscriberIdAndActive(Integer subscriberId,Integer active);
	
	
}