package com.sbmtech.mms.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmtech.mms.dto.NotifEmailDTO;
import com.sbmtech.mms.dto.NotificationEmailResponseDTO;
import com.sbmtech.mms.models.ChannelMaster;
import com.sbmtech.mms.models.Countries;
import com.sbmtech.mms.models.Otp;
import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.User;
import com.sbmtech.mms.models.UserTypeMaster;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.repository.ChannelMasterRepository;
import com.sbmtech.mms.repository.CountriesRepository;
import com.sbmtech.mms.repository.OtpRepository;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.UserRepository;
import com.sbmtech.mms.repository.UserTypeMasterRepository;
import com.sbmtech.mms.service.NotificationService;
import com.sbmtech.mms.service.SubscriberService;

@Service
public class SubscriberServiceImpl implements SubscriberService {

	@Autowired
	private SubscriberRepository subscriberRepository;

	@Autowired
	private ChannelMasterRepository channelMasterRepository;

	@Autowired
	private CountriesRepository countriesRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTypeMasterRepository userTypeMasterRepository;

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	PasswordEncoder encoder;

	@Transactional
	@Override
	public ApiResponse<String> createSubscriber(SubscriberRequest request) throws Exception {
		if (request.getNatId() == null || !countriesRepository.existsById(request.getNatId())) {
			return new ApiResponse<>(0, "failure", "Invalid natId: " + request.getNatId(), null, null);
		}

		boolean channelExists = channelMasterRepository.existsById(request.getChannelId());
		if (!channelExists) {
			return new ApiResponse<>(0, "failure", "Invalid channelId: " + request.getChannelId(), null, null);
		}

		ChannelMaster channelMaster = channelMasterRepository.findById(request.getChannelId()).orElse(null);
		if (channelMaster == null) {
			return new ApiResponse<>(0, "failure", "ChannelMaster not found for channelId: " + request.getChannelId(),
					null, null);
		}

		Countries country = countriesRepository.findById(request.getNatId()).orElse(null);
		if (country == null) {
			return new ApiResponse<>(0, "failure", "Country with id " + request.getNatId() + " not found.", null, null);
		}

		UserTypeMaster userType = userTypeMasterRepository.findById(2)
				.orElseThrow(() -> new RuntimeException("Default UserTypeMaster not found"));

		Subscriber existingSubscriber = subscriberRepository.findByCompanyEmail(request.getCompanyEmail());
		if (existingSubscriber != null) {
			if (existingSubscriber.getOtpVerified() != null && existingSubscriber.getOtpVerified() == 1) {
				return new ApiResponse<>(0, "failure",
						"Email is already registered and OTP is verified. Cannot register again.", null,
						existingSubscriber.getSubscriberId());
			}

			Long otpCode = generateOtp();

			Otp otp = new Otp();
			otp.setOtpCode(otpCode);
			otp.setOtpType("S");
			otp.setReferenceId(existingSubscriber.getSubscriberId());
			otp.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			otp.setExpiresAt(new Timestamp(calculateOtpExpiryTime().getTime()));
			otp.setVerified(false);
			otpRepository.save(otp);

			NotifEmailDTO dto = new NotifEmailDTO();
			dto.setEmailTo(request.getCompanyEmail());
			dto.setCustomerName(request.getSubscriberName());
			dto.setOtpCode(otpCode);

			NotificationEmailResponseDTO resp = notificationService.sendOTPEmail(dto);

			if (resp != null && resp.isEmailSent()) {
				return new ApiResponse<>(1, "success", "OTP sent to the registered email.", null,
						existingSubscriber.getSubscriberId());
			} else {
				return new ApiResponse<>(0, "failure", "Failed to send OTP email.", null, null);
			}
		}

		Subscriber subscriber = new Subscriber();
		subscriber.setSubscriberName(request.getSubscriberName());
		subscriber.setCompanyEmail(request.getCompanyEmail());
		subscriber.setCompanyMobileNo(request.getCompanyMobileNo());
		subscriber.setCompanyName(request.getCompanyName());
		subscriber.setChannelMaster(channelMaster);
		subscriber.setActive(1);
		subscriber.setCreatedDate(new Date());
		subscriberRepository.save(subscriber);

		User user = new User();
		user.setEmail(request.getCompanyEmail());

		try {
			Long mobileNo = Long.parseLong(request.getCompanyMobileNo());
			user.setMobileNo(mobileNo);
		} catch (NumberFormatException e) {
			return new ApiResponse<>(0, "failure", "Invalid mobile number format.", null, subscriber.getSubscriberId());
		}

		user.setPassword(request.getPassword());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setActive(true);
		user.setSubscriber(subscriber);
		user.setUserType(userType);
		user.setCreatedDate(new Date());
		userRepository.save(user);

		Long otpCode = generateOtp();

		Otp otp = new Otp();
		otp.setOtpCode(otpCode);
		otp.setOtpType("S");
		otp.setReferenceId(subscriber.getSubscriberId());
		otp.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		otp.setExpiresAt(new Timestamp(calculateOtpExpiryTime().getTime()));
		otp.setVerified(false);
		otpRepository.save(otp);

		NotifEmailDTO dto = new NotifEmailDTO();
		dto.setEmailTo(request.getCompanyEmail());
		dto.setCustomerName(request.getSubscriberName());
		dto.setOtpCode(otpCode);

		NotificationEmailResponseDTO resp = notificationService.sendOTPEmail(dto);

		if (resp != null && resp.isEmailSent()) {
			return new ApiResponse<>(1, "success", "Subscriber and user created successfully. OTP sent.",
					user.getUserId(), subscriber.getSubscriberId());
		} else {
			return new ApiResponse<>(0, "failure", "Failed to send OTP email.", null, subscriber.getSubscriberId());
		}
	}

	private Long generateOtp() {
		return (long) (Math.random() * 1000000);
	}

	private Date calculateOtpExpiryTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 5);
		return calendar.getTime();
	}

	public ApiResponse<String> verifyOtp(VerifyOtpRequest request) {
		Otp otp = otpRepository.findByReferenceIdAndOtpCode(request.getSubscriberId(), request.getOtpCode());

		if (otp == null) {
			return new ApiResponse<String>(0, "failure", "Invalid OTP code.", null, null);
		}

		if (otp.getExpiresAt().before(new Timestamp(System.currentTimeMillis()))) {
			return new ApiResponse<String>(0, "failure", "OTP has expired.", null, null);
		}

		otp.setVerified(true);
		otpRepository.save(otp);

		Subscriber subscriber = subscriberRepository.findById(request.getSubscriberId()).orElse(null);
		if (subscriber != null) {
			subscriber.setOtpVerified(1);
			subscriberRepository.save(subscriber);
		}

		return new ApiResponse<String>(1, "success", "OTP verified successfully.", null, request.getSubscriberId());
	}

}