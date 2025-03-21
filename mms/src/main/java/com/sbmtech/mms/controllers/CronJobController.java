package com.sbmtech.mms.controllers;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private SubscriberRepository subscriberRepository;

	@Autowired
	private UnitReserveDetailsRepository unitReserveDetailsRepository;

	@Autowired
	private UnitStatusRepository unitStatusRepository;

	@Autowired
	private UnitRepository unitRepository;

//	@Scheduled(cron = "0 */5 * * * ?") // every 5 hrs
	public void expireSubscriptionsEvery5Seconds() {
		LocalDateTime now = LocalDateTime.now();

		List<Subscriptions> subscriptionsToExpire = subscriptionRepository.findByStatusInAndEndDateBefore(
				List.of(SubscriptionStatus.ACTIVE.toString(), SubscriptionStatus.TRIAL.toString()), now);

		for (Subscriptions subscription : subscriptionsToExpire) {
			subscription.setStatus(SubscriptionStatus.EXPIRED.toString());

			subscriptionRepository.save(subscription);
			Subscriber subscriber = subscription.getSubscriber();
			subscriber.setActive(0);
			subscriberRepository.save(subscriber);

		}
	}

	/**
	 * Checks and Unreserve the Reserved Units
	 * */
	@Scheduled(cron = "0 0 1 * * *") // This runs every day at 1:00 AM
	public void releaseReservedUnits() {
		try {
			List<UnitReserveDetails> reservedUnits = unitReserveDetailsRepository
					.findReservedUnitsWithPastReserveEndDate();

			for (UnitReserveDetails reserveDetails : reservedUnits) {
				if (reserveDetails.getReserveEndDate().before(new Date())) {
					Unit unit = reserveDetails.getUnit();
					unit.setUnitStatus(unitStatusRepository.findByUnitStatusName(UnitStatusEnum.VACANT.toString()));
					unitRepository.save(unit);
				}
			}

			System.out.println("Checked and released reserved units.");
		} catch (Exception e) {
			System.err.println("Error while releasing reserved units: " + e.getMessage());
		}
	}
	
	
	/**
	 * Checks the unused images and remove from S3
	 * */
	@Scheduled(cron = "0 0 1 * * *") // This runs every day at 1:00 AM
	public void deleteUnusedImages() {
		try {
			List<UnitReserveDetails> reservedUnits = unitReserveDetailsRepository
					.findReservedUnitsWithPastReserveEndDate();

			for (UnitReserveDetails reserveDetails : reservedUnits) {
				if (reserveDetails.getReserveEndDate().before(new Date())) {
					Unit unit = reserveDetails.getUnit();
					unit.setUnitStatus(unitStatusRepository.findByUnitStatusName(UnitStatusEnum.VACANT.toString()));
					unitRepository.save(unit);
				}
			}

			System.out.println("Checked and released reserved units.");
		} catch (Exception e) {
			System.err.println("Error while releasing reserved units: " + e.getMessage());
		}
	}
}
