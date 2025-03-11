package com.sbmtech.mms.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbmtech.mms.constant.ErrorConstant;
import com.sbmtech.mms.constant.SubscriptionStatus;
import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.Subscriptions;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.SubscriptionRepository;
import com.sbmtech.mms.service.SubscriberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cron")
@PreAuthorize("hasRole(@securityService.MgtAdmin)")
public class CronJobController {

	@Autowired
	private SubscriberService subscriberService;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private SubscriberRepository subscriberRepository;

	@Scheduled(cron = "0 */5 * * * ?") //every 5 hrs
	public void expireSubscriptionsEvery5Seconds() {
		LocalDateTime now = LocalDateTime.now();

		List<Subscriptions> subscriptionsToExpire = subscriptionRepository
				.findByStatusInAndEndDateBefore(List.of(SubscriptionStatus.ACTIVE.toString(), SubscriptionStatus.TRIAL.toString()), now);

		for (Subscriptions subscription : subscriptionsToExpire) {
			subscription.setStatus(SubscriptionStatus.EXPIRED.toString());
		
			subscriptionRepository.save(subscription);
			Subscriber subscriber=subscription.getSubscriber();
			subscriber.setActive(0);
			subscriberRepository.save(subscriber);
			
		}
	}
}
