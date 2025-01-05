package com.sbmtech.mms.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	private static final Logger logger = LogManager.getLogger(TestController.class);

	@GetMapping("/all")
	public String allAccess() {
		logger.debug("Debug Message Logged !!!");
		logger.info("Info Message Logged !!!");
		
		return "Public Content.";
	}

	@Operation(summary = "mms endpoint", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/mgtAdmin")
	@PreAuthorize("hasRole('MGT_ADMIN') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "Mangament Admin Content.";
	}

	@GetMapping("/mgtManager")
	@PreAuthorize("hasRole('MGT_MANAGER')")
	public String moderatorAccess() {
		return "Management manager Content.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin.";
	}
}
