package com.sbmtech.mms.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.sbmtech.mms.service.CronJobService;

@Service
public class CronJobServiceImpl implements CronJobService {
	
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

	@Override
	public void expireSubscriptions() throws Exception {
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

	@Override
	public void releaseReservedUnits() throws Exception {
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

	@Override
	public void deleteUnusedS3Images() throws Exception {
		// TODO Auto-generated method stub

	}

}
