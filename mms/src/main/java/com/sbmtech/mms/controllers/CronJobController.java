package com.sbmtech.mms.controllers;

import com.sbmtech.mms.service.CronJobService;

import software.amazon.awssdk.services.s3.S3Client;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cron")
public class CronJobController {

	private static final Logger logger = LogManager.getLogger(CronJobController.class);
	
	// private final S3Client s3Client;

	@Autowired
	private CronJobService cronJobService;

	@Scheduled(cron = "0 0 * * * ?") // Every midnight (12:00 AM)
	public void expireSubscriptionsJob() throws Exception {

		logger.info("expireSubscriptionsJob invoked at=" + new Date());
		cronJobService.expireSubscriptions();
	}

	@GetMapping("/expireSubscriptionsRest")
	public void expireSubscriptionsRest() throws Exception {

		cronJobService.expireSubscriptions();
	}

	/**
	 * Checks and Unreserve the Reserved Units
	 */
	@Scheduled(cron = "0 0 1 * * *") // This runs every day at 1:00 AM
	public void releaseReservedUnits() throws Exception {
		cronJobService.releaseReservedUnits();
	}

	/**
	 * Checks the unused images and remove from S3
	 * @throws Exception 
	 */
	@Scheduled(cron = "0 0 1 * * *") // This runs every day at 1:00 AM
	public void deleteUnusedImages() throws Exception {
		cronJobService.deleteUnusedS3Images();
	}
	
	@GetMapping("/deleteUnusedImages")
	public void deleteUnusedImagesRest() throws Exception {

		cronJobService.deleteUnusedS3Images();
	}

	
	
	
}
