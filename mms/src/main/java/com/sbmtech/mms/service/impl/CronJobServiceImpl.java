package com.sbmtech.mms.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmtech.mms.constant.SubscriptionStatus;
import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.Subscriptions;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.models.UnitReserveDetails;
import com.sbmtech.mms.models.UnitStatusEnum;
import com.sbmtech.mms.repository.BuildingRepository;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.SubscriptionRepository;
import com.sbmtech.mms.repository.UnitRepository;
import com.sbmtech.mms.repository.UnitReserveDetailsRepository;
import com.sbmtech.mms.repository.UnitStatusRepository;
import com.sbmtech.mms.service.CronJobService;

import software.amazon.awssdk.services.s3.model.Delete;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
@Transactional
public class CronJobServiceImpl implements CronJobService {

	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(CronJobServiceImpl.class);

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
	
	//@Autowired
	//private S3Client s3Client;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private S3DeletionService s3DeletionService;
	
	@Autowired
	private Environment environment;

	@Override
	public void expireSubscriptions() throws Exception {
		LocalDate now = LocalDate.now();

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

		} catch (Exception e) {
		}

	}

	@Override
	public void deleteUnusedS3Images() throws Exception {
		String prodProfile="prod"; // To void delete s3 files on prod env
		String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles) {
            if (!prodProfile.equals(profile)) {
            	logger.info("I am "+profile);
            	 List<String> fileNames = buildingRepository.findAllFileNames();
            	 logger.info("DB deleing fileList="+fileNames);
            	 s3DeletionService.deleteFilesNotInList(fileNames);
            }
        }
		
		
	}
	
	

}
