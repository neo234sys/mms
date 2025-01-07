package com.sbmtech.mms.service;

import com.sbmtech.mms.dto.NotifEmailDTO;

public interface EmailService {
	
	
	public boolean sendEmailWithMultiAttachments(NotifEmailDTO dto);
	public void sendPlainTextEmail(String subject, String message) throws Exception;

}
