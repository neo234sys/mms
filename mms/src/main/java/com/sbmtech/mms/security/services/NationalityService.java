package com.sbmtech.mms.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.models.Nationality;
import com.sbmtech.mms.repository.NationalityRepository;

@Service
public class NationalityService {

	@Autowired
	private NationalityRepository nationalityRepository;

	public Nationality saveNationality(Nationality nationality) {
		return nationalityRepository.save(nationality);
	}

}
