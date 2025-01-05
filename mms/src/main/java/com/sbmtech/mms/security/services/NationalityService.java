package com.sbmtech.mms.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.models.Countries;
import com.sbmtech.mms.repository.CountriesRepository;

@Service
public class NationalityService {

	@Autowired
	private CountriesRepository nationalityRepository;

	public Countries saveNationality(Countries nationality) {
		return nationalityRepository.save(nationality);
	}

}
