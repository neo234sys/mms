package com.sbmtech.mms.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.dto.NotifEmailDTO;
import com.sbmtech.mms.dto.NotificationEmailResponseDTO;
import com.sbmtech.mms.payload.request.TenantSearchRequest;
import com.sbmtech.mms.service.EmailService;
import com.sbmtech.mms.service.NotificationService;
import com.sbmtech.mms.service.impl.InvoiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	EmailService emailService;

	@Autowired
	NotificationService notificationService;
	
	@Autowired
	InvoiceService invoiceService;

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

	@GetMapping("/testMail")
	// @PreAuthorize("hasRole('MGT_ADMIN') or hasRole('ADMIN')")
	public String testMail() throws Exception {
		NotifEmailDTO dto = new NotifEmailDTO();
		dto.setEmailTo("hasan234abu@gmail.com");
		dto.setCustomerName("Hasan");
		dto.setOtpCode(12345L);
		// dto.setSubject("test mail subject "+new Date());
		// dto.setEmailBody("This is test body "+new Date());
		// emailService.sendEmailWithMultiAttachments(dto);
		NotificationEmailResponseDTO resp = notificationService.sendOTPEmail(dto);
		return "email send successfully " + resp.isEmailSent();
	}
	
	
	@PostMapping("/testSequence")
	public ResponseEntity<?> testSequence() throws Exception {
		
		
		invoiceService.createUnit("test custname");
		invoiceService.createTenant("test custname");
		return ResponseEntity.ok(invoiceService.createBuilding("test custname"));
	}
}
