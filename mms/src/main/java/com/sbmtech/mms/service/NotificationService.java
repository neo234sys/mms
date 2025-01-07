package com.sbmtech.mms.service;

import com.sbmtech.mms.dto.NotifEmailDTO;
import com.sbmtech.mms.dto.NotificationEmailResponseDTO;

public interface NotificationService {
	public NotificationEmailResponseDTO sendOTPEmail(NotifEmailDTO dto)throws Exception;
	public NotificationEmailResponseDTO sendAcctActivationEmail(NotifEmailDTO dto)throws Exception;
	
}
