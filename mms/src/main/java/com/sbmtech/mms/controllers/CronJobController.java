package com.sbmtech.mms.controllers;

import java.time.LocalDateTime;
import com.sbmtech.mms.service.CronJobService;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.constant.SubscriptionStatus;
import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.Subscriptions;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.models.UnitReserveDetails;
import com.sbmtech.mms.models.UnitStatusEnum;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.SubscriptionRepository;
import com.sbmtech.mms.repository.UnitRepository;
import com.sbmtech.mms.repository.UnitReserveDetailsRepository;
import com.sbmtech.mms.repository.UnitStatusRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cron")
public class CronJobController {


	@Autowired
	private CronJobService cronJobService;
	
	
	@Scheduled(cron = "0 */5 * * * ?") // every 5 hrs
	public void expireSubscriptionsJob()throws Exception {

		
		cronJobService.expireSubscriptions();
	}
	
	@GetMapping("/expireSubscriptionsRest")
	public void expireSubscriptionsRest()throws Exception {

		
		cronJobService.expireSubscriptions();
	}

	/**
	 * Checks and Unreserve the Reserved Units
	 * */
	@Scheduled(cron = "0 0 1 * * *") // This runs every day at 1:00 AM
	public void releaseReservedUnits()throws Exception {
		cronJobService.releaseReservedUnits();
	}
	
	
	/**
	 * Checks the unused images and remove from S3
	 * */
	@Scheduled(cron = "0 0 1 * * *") // This runs every day at 1:00 AM
	public void deleteUnusedImages() {
		
	}
}
