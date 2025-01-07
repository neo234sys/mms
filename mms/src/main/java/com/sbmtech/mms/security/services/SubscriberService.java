package com.sbmtech.mms.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.repository.SubscriberRepository;

@Service
public class SubscriberService {

	@Autowired
	private CountriesService countriesService;

	@Autowired
	private ChannelMasterService channelMasterService;

	@Autowired
	private SubscriberRepository subscriberRepository;

	public void createSubscriber(SubscriberRequest requestDTO) {
		if (!countriesService.isNatIdValid(requestDTO.getNatId())) {
			throw new IllegalArgumentException("Invalid natId: Not found in Countries table.");
		}

		if (!channelMasterService.isChannelIdValid(requestDTO.getChannelId())) {
			throw new IllegalArgumentException("Invalid channelId: Not found in ChannelMaster table.");
		}

		Subscriber subscriber = new Subscriber();
		subscriber.setSubscriberName(requestDTO.getSubscriberName());
		subscriber.setCompanyEmail(requestDTO.getCompanyEmail());
		subscriber.setCompanyMobileNo(requestDTO.getCompanyMobileNo());
		subscriber.setCompanyName(requestDTO.getCompanyName());
		subscriber.setChannelId(requestDTO.getChannelId());

		subscriberRepository.save(subscriber);
	}
}
