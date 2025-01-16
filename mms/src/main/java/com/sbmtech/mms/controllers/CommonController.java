package com.sbmtech.mms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.service.SubscriberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/common")
public class CommonController {

	@Autowired
	private SubscriberService subscriberService;

	@GetMapping("/states")
	public ResponseEntity<?> getStatesByCountryId(@RequestParam("countryId") Integer countryId) {
		return ResponseEntity.ok(subscriberService.getStatesByCountryId(countryId));
	}

	@GetMapping("/cities")
	public ResponseEntity<?> getCitiesByStateAndCountryId(@RequestParam("stateId") Integer stateId,
			@RequestParam("countryId") Integer countryId) {
		return ResponseEntity.ok(subscriberService.getCitiesByStateAndCountryId(stateId, countryId));
	}

}