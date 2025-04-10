package com.sbmtech.mms.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.dto.NotifEmailDTO;
import com.sbmtech.mms.dto.NotificationEmailResponseDTO;
import com.sbmtech.mms.service.EmailService;
import com.sbmtech.mms.service.NotificationService;
import com.sbmtech.mms.util.NotificationUtil;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	NotificationUtil util;
	
	@Autowired
	EmailService emailService;

	@Override
	public NotificationEmailResponseDTO sendOTPEmail(NotifEmailDTO dto) throws Exception {
		NotificationEmailResponseDTO emailResponseDTO = new NotificationEmailResponseDTO();
		NotifEmailDTO emailSenderDTO = new NotifEmailDTO();
		boolean isEmailSent = false;
		
		emailSenderDTO.setEmailTo(dto.getEmailTo());
		emailSenderDTO.setEmailBody(util.prepareOTPEmail(dto));
		emailSenderDTO.setSubject(util.getServiceNotifEmailSubject());
		emailSenderDTO.setAttachmentFileName(dto.getAttachmentFileName());
		emailSenderDTO.setAttachmentObj(dto.getAttachmentObj());
		emailSenderDTO.setCustomerName(dto.getCustomerName());
		emailSenderDTO.setOtpCode(dto.getOtpCode());
		
		//isEmailSent = emailSender.sendEmail(emailSenderDTO);
		
		isEmailSent =emailService.sendEmailWithMultiAttachments(emailSenderDTO);
		
		
		emailResponseDTO.setEmailSent(isEmailSent);
		emailResponseDTO.setEmail(emailSenderDTO.getEmailTo());
		
		return emailResponseDTO;
		
	}
	
	@Override
	public NotificationEmailResponseDTO sendTenentAccountCreationEmail(NotifEmailDTO dto) throws Exception {
		NotificationEmailResponseDTO emailResponseDTO = new NotificationEmailResponseDTO();
		NotifEmailDTO emailSenderDTO = new NotifEmailDTO();
		boolean isEmailSent = false;
		
		emailSenderDTO.setEmailTo(dto.getEmailTo());
		emailSenderDTO.setEmailBody(util.prepareTenantAcctCreationEmail(dto));
		emailSenderDTO.setSubject(util.getTenantAcctCreationSubject());
		emailSenderDTO.setAttachmentFileName(dto.getAttachmentFileName());
		emailSenderDTO.setAttachmentObj(dto.getAttachmentObj());
		emailSenderDTO.setCustomerName(dto.getCustomerName());
		emailSenderDTO.setPwd(dto.getPwd());
		
		//isEmailSent = emailSender.sendEmail(emailSenderDTO);
		
		isEmailSent =emailService.sendEmailWithMultiAttachments(emailSenderDTO);
		
		
		emailResponseDTO.setEmailSent(isEmailSent);
		emailResponseDTO.setEmail(emailSenderDTO.getEmailTo());
		
		return emailResponseDTO;
		
	}
	
	@Override
	public NotificationEmailResponseDTO sendTenentAccountCreationEmailExistingUser(NotifEmailDTO dto) throws Exception {
		NotificationEmailResponseDTO emailResponseDTO = new NotificationEmailResponseDTO();
		NotifEmailDTO emailSenderDTO = new NotifEmailDTO();
		boolean isEmailSent = false;
		
		emailSenderDTO.setEmailTo(dto.getEmailTo());
		emailSenderDTO.setEmailBody(util.prepareTenantAcctCreationEmailExistingUser(dto));
		emailSenderDTO.setSubject(util.getTenantAcctCreationSubject());
		emailSenderDTO.setAttachmentFileName(dto.getAttachmentFileName());
		emailSenderDTO.setAttachmentObj(dto.getAttachmentObj());
		emailSenderDTO.setCustomerName(dto.getCustomerName());
		
		isEmailSent =emailService.sendEmailWithMultiAttachments(emailSenderDTO);
		
		
		emailResponseDTO.setEmailSent(isEmailSent);
		emailResponseDTO.setEmail(emailSenderDTO.getEmailTo());
		
		return emailResponseDTO;
		
	}

	@Override
	public NotificationEmailResponseDTO sendAcctActivationEmail(NotifEmailDTO dto) throws Exception {
		NotificationEmailResponseDTO emailResponseDTO = new NotificationEmailResponseDTO();
		NotifEmailDTO emailSenderDTO = new NotifEmailDTO();
		boolean isEmailSent = false;
		
		emailSenderDTO.setEmailTo(dto.getEmailTo());
		emailSenderDTO.setEmailBody(util.prepareAcctActivationEmail(dto));
		emailSenderDTO.setSubject(dto.getSubject());
		emailSenderDTO.setAttachmentFileName(dto.getAttachmentFileName());
		emailSenderDTO.setAttachmentObj(dto.getAttachmentObj());
		emailSenderDTO.setCustomerName(dto.getCustomerName());
		
		
		isEmailSent =emailService.sendEmailWithMultiAttachments(emailSenderDTO);
		
		emailResponseDTO.setEmailSent(isEmailSent);
		emailResponseDTO.setEmail(emailSenderDTO.getEmailTo());
		
		return emailResponseDTO;
	}

	@Override
	public NotificationEmailResponseDTO sendUnitReservationEmail(NotifEmailDTO dto) throws Exception {
		NotificationEmailResponseDTO emailResponseDTO = new NotificationEmailResponseDTO();
		NotifEmailDTO emailSenderDTO = new NotifEmailDTO();
		boolean isEmailSent = false;
		
		BeanUtils.copyProperties(dto, emailSenderDTO);
		emailSenderDTO.setEmailBody(util.prepareReserveUnitEmail(dto));
		emailSenderDTO.setSubject(util.getReserveUnitNotifEmailSubject());

		isEmailSent =emailService.sendEmailWithMultiAttachments(emailSenderDTO);
		
		
		emailResponseDTO.setEmailSent(isEmailSent);
		emailResponseDTO.setEmail(emailSenderDTO.getEmailTo());
		
		return emailResponseDTO;
	}
	
	

}
