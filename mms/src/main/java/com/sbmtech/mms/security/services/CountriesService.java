package com.sbmtech.mms.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.repository.CountriesRepository;

@Service
public class CountriesService {

	@Autowired
	private CountriesRepository countriesRepository;

	public boolean isNatIdValid(Integer natId) {
		return countriesRepository.existsById(natId);
	}
}