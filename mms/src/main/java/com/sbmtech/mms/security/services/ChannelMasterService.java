package com.sbmtech.mms.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.repository.ChannelMasterRepository;

@Service
public class ChannelMasterService {

	@Autowired
	private ChannelMasterRepository channelMasterRepository;

	public boolean isChannelIdValid(Integer channelId) {
		return channelMasterRepository.existsById(channelId);
	}
}