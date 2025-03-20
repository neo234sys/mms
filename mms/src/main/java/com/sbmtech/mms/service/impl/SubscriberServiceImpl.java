package com.sbmtech.mms.service.impl;

import static com.sbmtech.mms.constant.CommonConstants.DATE_ddMMyyyy;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_CODE;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_DESC;
import static com.sbmtech.mms.constant.CommonConstants.INT_ONE_YES;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_CODE;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_DESC;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmtech.mms.constant.CommonConstants;
import com.sbmtech.mms.constant.SubscriptionStatus;
import com.sbmtech.mms.dto.BuildingDetailDTO;
import com.sbmtech.mms.dto.KeyValuePairDTO;
import com.sbmtech.mms.dto.NotifEmailDTO;
import com.sbmtech.mms.dto.NotificationEmailResponseDTO;
import com.sbmtech.mms.exception.BusinessException;
import com.sbmtech.mms.models.Area;
import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.ChannelMaster;
import com.sbmtech.mms.models.City;
import com.sbmtech.mms.models.Community;
import com.sbmtech.mms.models.Countries;
import com.sbmtech.mms.models.DepartmentMaster;
import com.sbmtech.mms.models.Floor;
import com.sbmtech.mms.models.KeyMaster;
import com.sbmtech.mms.models.Otp;
import com.sbmtech.mms.models.Parking;
import com.sbmtech.mms.models.ParkingTypeEnum;
import com.sbmtech.mms.models.ParkingZone;
import com.sbmtech.mms.models.PaymentMethod;
import com.sbmtech.mms.models.PaymentMode;
import com.sbmtech.mms.models.ProductConfig;
import com.sbmtech.mms.models.RentCycle;
import com.sbmtech.mms.models.Role;
import com.sbmtech.mms.models.RoleEnum;
import com.sbmtech.mms.models.State;
import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.SubscriptionPayment;
import com.sbmtech.mms.models.SubscriptionPlanMaster;
import com.sbmtech.mms.models.Subscriptions;
import com.sbmtech.mms.models.Tenant;
import com.sbmtech.mms.models.TenantUnit;
import com.sbmtech.mms.models.TenureDetails;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.models.UnitKeys;
import com.sbmtech.mms.models.UnitReserveDetails;
import com.sbmtech.mms.models.UnitStatus;
import com.sbmtech.mms.models.UnitStatusEnum;
import com.sbmtech.mms.models.UnitSubType;
import com.sbmtech.mms.models.UnitType;
import com.sbmtech.mms.models.User;
import com.sbmtech.mms.models.UserTypeEnum;
import com.sbmtech.mms.models.UserTypeMaster;
import com.sbmtech.mms.payload.request.AdditionalDetailsRequest;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.BuildingRequest;
import com.sbmtech.mms.payload.request.CommunityRequest;
import com.sbmtech.mms.payload.request.CreateUserRequest;
import com.sbmtech.mms.payload.request.DepartmentRequest;
import com.sbmtech.mms.payload.request.FloorRequest;
import com.sbmtech.mms.payload.request.KeyMasterRequest;
import com.sbmtech.mms.payload.request.PaginationRequest;
import com.sbmtech.mms.payload.request.ParkingRequest;
import com.sbmtech.mms.payload.request.ParkingZoneRequest;
import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.ReserveUnitRequest;
import com.sbmtech.mms.payload.request.AreaRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.SubscriptionPaymentRequest;
import com.sbmtech.mms.payload.request.SubscriptionRequest;
import com.sbmtech.mms.payload.request.TenantUnitRequest;
import com.sbmtech.mms.payload.request.UnitKeysRequest;
import com.sbmtech.mms.payload.request.UnitRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.payload.response.BuildingResponse;
import com.sbmtech.mms.payload.response.CommunityResponse;
import com.sbmtech.mms.payload.response.DeptMasResponse;
import com.sbmtech.mms.payload.response.FloorResponse;
import com.sbmtech.mms.payload.response.KeyResponse;
import com.sbmtech.mms.payload.response.PaginationResponse;
import com.sbmtech.mms.payload.response.AreaResponse;
import com.sbmtech.mms.payload.response.ParkingResponse;
import com.sbmtech.mms.payload.response.SubscriptionPlans;
import com.sbmtech.mms.payload.response.TenantDetailResponse;
import com.sbmtech.mms.payload.response.TenantUnitResponse;
import com.sbmtech.mms.payload.response.UniKeyResponse;
import com.sbmtech.mms.payload.response.UnitDetailResponse;
import com.sbmtech.mms.payload.response.UnitResponse;
import com.sbmtech.mms.repository.AreaRepository;
import com.sbmtech.mms.repository.BuildingRepository;
import com.sbmtech.mms.repository.ChannelMasterRepository;
import com.sbmtech.mms.repository.CityRepository;
import com.sbmtech.mms.repository.CommunityRepository;
import com.sbmtech.mms.repository.CountriesRepository;
import com.sbmtech.mms.repository.DepartmentMasRepository;
import com.sbmtech.mms.repository.FloorRepository;
import com.sbmtech.mms.repository.KeyMasterRepository;
import com.sbmtech.mms.repository.OtpRepository;
import com.sbmtech.mms.repository.ParkingRepository;
import com.sbmtech.mms.repository.ParkingZoneRepository;
import com.sbmtech.mms.repository.PaymentModeRepository;
import com.sbmtech.mms.repository.ProductConfigRepository;
import com.sbmtech.mms.repository.RentCycleRepository;
import com.sbmtech.mms.repository.RoleRepository;
import com.sbmtech.mms.repository.StateRepository;
import com.sbmtech.mms.repository.AreaRepositoryCustom;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.SubscriptionPaymentRepository;
import com.sbmtech.mms.repository.SubscriptionPlanMasterRepository;
import com.sbmtech.mms.repository.SubscriptionRepository;
import com.sbmtech.mms.repository.TenantRepository;
import com.sbmtech.mms.repository.TenantUnitRepository;
import com.sbmtech.mms.repository.TenureDetailsRepository;
import com.sbmtech.mms.repository.UnitKeysRepository;
import com.sbmtech.mms.repository.UnitRepository;
import com.sbmtech.mms.repository.UnitReserveDetailsRepository;
import com.sbmtech.mms.repository.UnitStatusRepository;
import com.sbmtech.mms.repository.UnitSubTypeRepository;
import com.sbmtech.mms.repository.UnitTypeRepository;
import com.sbmtech.mms.repository.UserRepository;
import com.sbmtech.mms.repository.UserTypeMasterRepository;
import com.sbmtech.mms.security.services.UserDetailsImpl;
import com.sbmtech.mms.service.NotificationService;
import com.sbmtech.mms.service.SubscriberService;
import com.sbmtech.mms.util.CommonUtil;
import com.sbmtech.mms.util.PaginationUtils;

@Service
@Transactional
public class SubscriberServiceImpl implements SubscriberService {

	private static final Logger logger = LogManager.getLogger(SubscriberServiceImpl.class);

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

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private SubscriptionPlanMasterRepository planMasterRepository;

	@Autowired
	private SubscriptionPaymentRepository paymentRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private AreaRepositoryCustom areaRepoCustom;

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private UnitRepository unitRepository;

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private ParkingZoneRepository parkingZoneRepository;

	@Autowired
	private ParkingRepository parkingRepository;

	@Autowired
	private KeyMasterRepository keyMasterRepository;

	@Autowired
	private UnitKeysRepository unitKeysRepository;

	@Autowired
	private TenantUnitRepository tenantUnitRepository;

	@Autowired
	private TenureDetailsRepository tenureDetailsRepository;

	@Autowired
	private DepartmentMasRepository departmentMasRepository;

	@Autowired
	private SubscriptionPlanMasterRepository subscriptionPlanRepository;

	@Autowired
	private UnitTypeRepository unitTypeRepository;

	@Autowired
	private UnitSubTypeRepository unitSubTypeRepository;

	@Autowired
	private UnitStatusRepository unitStatusRepository;

	@Autowired
	private PaymentModeRepository paymentModeRepository;

	@Autowired
	private RentCycleRepository rentCycleRepository;

	@Autowired
	private UnitReserveDetailsRepository unitReserveDetailsRepository;

	@Autowired
	private ProductConfigRepository productConfigRepository;

	@Override
	public Integer getSubscriberIdfromAuth(Authentication auth) throws Exception {
		User user = null;
		Integer subscriberId = null;
		UserDetailsImpl userPrincipal = (UserDetailsImpl) auth.getPrincipal();
		Optional<User> userOp = userRepository.findByEmail(userPrincipal.getUsername());
		if (userOp.isPresent()) {
			user = userOp.get();
			if (user != null && user.getRoles() != null) {
				Set<Role> roles = user.getRoles();
				for (Role role : roles) {
					if (role.getName().toString().equals(RoleEnum.ROLE_MGT_ADMIN.getName())) {
						subscriberId = user.getSubscriber().getSubscriberId();
						logger.info("getSubscriberIdfromAuth subscriber->= {}", user.getSubscriber());
						break;
					}
				}

			}
		}
		if (subscriberId != null && subscriberId.intValue() != 0) {
			boolean subscriberlIdExists = false;
			subscriberlIdExists = subscriberRepository.existsBySubscriberIdAndActive(subscriberId, 1);
			if (!subscriberlIdExists) {
				logger.info("Subscribtion subspended for subscriberId ->:{} ", subscriberId);

				throw new BusinessException("Subscribtion subspended", null);
			}

		} else {
			subscriberId = 0;
		}
		return subscriberId;
	}

	@Override
	public Map<String, Object> createSubscriber(SubscriberRequest request) throws Exception {

		ChannelMaster channelMaster = channelMasterRepository.findById(request.getChannelId()).orElse(null);
		Countries country = countriesRepository.findById(request.getNatId()).orElse(null);

		UserTypeMaster userType = userTypeMasterRepository.findById(CommonConstants.USERTYPE_SUBSCRIBER)
				.orElseThrow(() -> new RuntimeException("Default UserTypeMaster not found"));

		Subscriber existingSubscriber = subscriberRepository.findByCompanyEmail(request.getCompanyEmail());
		Map<String, Object> response = new HashMap<>();

		if (existingSubscriber != null) {
			boolean isOTPVerified = existingSubscriber.getOtpVerified() != null
					&& existingSubscriber.getOtpVerified() == 1;

			if (isOTPVerified) {
				response.put("responseCode", FAILURE_CODE);
				response.put("responseDesc", FAILURE_DESC);
				response.put("data", "Email is already registered and OTP is verified. Cannot register again.");
				response.put("isOTPVerified", true);
				response.put("subscriberId", existingSubscriber.getSubscriberId());
				return response;
			}

			if (existingSubscriber.getOtpVerified() == null || existingSubscriber.getOtpVerified() == 0) {
				response.put("responseCode", FAILURE_CODE);
				response.put("responseDesc", FAILURE_DESC);
				response.put("data",
						"Email address has been already associated with a business. We have sent an OTP to verify your email address..");
				response.put("isOTPVerified", false);
				response.put("subscriberId", existingSubscriber.getSubscriberId());
				return response;
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
				response.put("responseCode", SUCCESS_CODE);
				response.put("responseDesc", SUCCESS_DESC);
				response.put("data", "OTP sent to the registered email.");
				response.put("isOTPVerified", false); // OTP is not verified yet
				response.put("subscriberId", existingSubscriber.getSubscriberId());
				return response;
			} else {
				response.put("responseCode", FAILURE_CODE);
				response.put("responseDesc", FAILURE_DESC);
				response.put("data", "Failed to send OTP email.");
				return response;
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
			user.setMobileNo(Long.valueOf(country.getPhonecode() + mobileNo));
		} catch (NumberFormatException e) {
			response.put("responseCode", FAILURE_CODE);
			response.put("responseDesc", FAILURE_DESC);
			response.put("data", "Invalid mobile number format.");
			response.put("subscriberId", subscriber.getSubscriberId());
			return response;
		}

		user.setPassword(encoder.encode("123456")); // remove this
		user.setActive(true);
		user.setSubscriber(subscriber);
		user.setUserType(userType);
		user.setCreatedDate(new Date());
		Role defaultRole = roleRepository.findById(2)
				.orElseThrow(() -> new RuntimeException("Role not found for ID 2"));
		user.getRoles().add(defaultRole);
		user.setNationality(country);
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
			response.put("responseCode", SUCCESS_CODE);
			response.put("responseDesc", SUCCESS_DESC);
			response.put("data", "Subscriber and user created successfully. OTP sent.");
			response.put("isOTPVerified", false); // OTP is not verified yet
			response.put("subscriberId", subscriber.getSubscriberId());
			return response;
		} else {
			response.put("responseCode", FAILURE_CODE);
			response.put("responseDesc", FAILURE_DESC);
			response.put("data", "Subscription Not created.");
			return response;
		}
	}

	private Long generateOtp() {
		return (long) (Math.random() * 1000000);
	}

	private Date calculateOtpExpiryTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 10);
		return calendar.getTime();
	}

	public ApiResponse<String> verifyOtp(VerifyOtpRequest request) {
		Otp otp = otpRepository.findByReferenceIdAndOtpCode(request.getSubscriberId(), request.getOtpCode());

		if (otp == null) {
			throw new BusinessException("Invalid OTP code", null);
		}

		if (otp.getExpiresAt().before(new Timestamp(System.currentTimeMillis()))) {
			throw new BusinessException("OTP has expired", null);
		}

		otp.setVerified(true);
		otpRepository.save(otp);

		Subscriber subscriber = subscriberRepository.findById(request.getSubscriberId()).orElse(null);
		if (subscriber != null) {
			subscriber.setOtpVerified(1);
			subscriberRepository.save(subscriber);
		}

		return new ApiResponse<String>(SUCCESS_CODE, SUCCESS_DESC, "OTP verified successfully.", null,
				request.getSubscriberId());
	}

	public ApiResponse<String> resendOtp(ResendOtpRequest request) {
		try {
			Subscriber subscriber = subscriberRepository.findById(request.getSubscriberId()).orElseThrow(
					() -> new RuntimeException("Subscriber not found with ID: " + request.getSubscriberId()));

			if (subscriber.getOtpVerified() != null && subscriber.getOtpVerified() == 1) {
				return new ApiResponse<>(0, "failure", "OTP is already verified. Resending is not allowed.", null,
						null);
			}

			Long newOtpCode = generateOtp();
			Timestamp expiryTime = new Timestamp(calculateOtpExpiryTime().getTime());

			Otp otp = new Otp();
			otp.setReferenceId(request.getSubscriberId());
			otp.setOtpCode(newOtpCode);
			otp.setOtpType("S");
			otp.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			otp.setExpiresAt(expiryTime);
			otp.setVerified(false);
			otpRepository.save(otp);

			NotifEmailDTO dto = new NotifEmailDTO();
			dto.setEmailTo(subscriber.getCompanyEmail());
			dto.setCustomerName(subscriber.getSubscriberName());
			dto.setOtpCode(newOtpCode);

			NotificationEmailResponseDTO resp = notificationService.sendOTPEmail(dto);

			if (resp != null && resp.isEmailSent()) {
				return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "OTP resent successfully.", null,
						subscriber.getSubscriberId());
			} else {
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "Failed to send OTP email.", null, null);
			}

		} catch (Exception e) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "An error occurred: " + e.getMessage(), null, null);
		}
	}

	public ApiResponse<List<ChannelMaster>> getAllChannels() {
		List<ChannelMaster> channels = channelMasterRepository.findAll();
		if (channels.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, channels, null, null);
	}

	public ApiResponse<List<Countries>> getAllCountries() {
		List<Countries> countries = countriesRepository.findAll();
		if (countries.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, countries, null, null);
	}

	public ApiResponse<String> addAdditionalDetails(AdditionalDetailsRequest request) {
		Subscriber subscriber = subscriberRepository.findById(request.getSubscriberId())
				.orElseThrow(() -> new RuntimeException("Subscriber not found with id: " + request.getSubscriberId()));

		if (request.getCompanyAddress() != null) {
			subscriber.setCompanyAddress(request.getCompanyAddress());
		}
		if (request.getCompanyContactName() != null) {
			subscriber.setCompanyContactName(request.getCompanyContactName());
		}
		if (request.getCompanyLandlineNo() != null) {
			subscriber.setCompanyLandlineNo(request.getCompanyLandlineNo());
		}
		if (request.getCompanyTradeLicense() != null) {
			subscriber.setCompanyTradeLicense(request.getCompanyTradeLicense());
		}
		if (request.getCompanyTradeLicenseCopy() != null) {
			subscriber.setCompanyTradeLicenseCopy(request.getCompanyTradeLicenseCopy());
		}
		if (request.getCompanyLogo() != null) {
			subscriber.setCompanyLogo(request.getCompanyLogo());
		}

		subscriber.setUpdatedDate(new Date());
		subscriberRepository.save(subscriber);

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Additional details added successfully.", null,
				subscriber.getSubscriberId());
	}

	public ApiResponse<String> saveSubscription(SubscriptionRequest subscriptionRequest) {

		SubscriptionPlanMaster planMaster = planMasterRepository.findById(subscriptionRequest.getPlanId()).orElse(null);

		Subscriptions existingSubscription = subscriptionRepository
				.findTopBySubscriber_SubscriberIdOrderBySubscriptionIdDesc(subscriptionRequest.getSubscriberId());

		if (existingSubscription != null

				&& CommonUtil.getCurrentLocalDate().isAfter(existingSubscription.getStartDate().minusDays(1))
				&& CommonUtil.getCurrentLocalDate().isBefore(existingSubscription.getEndDate())) {
			throw new BusinessException(
					"Subscriber already has a subscription with status " + existingSubscription.getStatus(), null);
		}

		Subscriptions subscription = new Subscriptions();

		subscription.setStartDate(CommonUtil.getLocalDatefromString(subscriptionRequest.getStartDate(), DATE_ddMMyyyy));
		subscription.setEndDate(subscription.getStartDate().plusDays((planMaster.getDurationInDays())));
		subscription.setStatus(SubscriptionStatus.TRIAL.toString());

		SubscriptionPlanMaster plan = new SubscriptionPlanMaster();
		plan.setPlanId(subscriptionRequest.getPlanId());
		subscription.setPlan(plan);

		Subscriber subscriber = new Subscriber();
		subscriber.setSubscriberId(subscriptionRequest.getSubscriberId());
		subscription.setSubscriber(subscriber);

		subscription.setChannelId(subscriptionRequest.getChannelId());
		subscription.setCreatedDate(LocalDateTime.now());

		subscriptionRepository.save(subscription);

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Subscription saved successfully", null,
				subscription.getSubscriber().getSubscriberId());
	}

	public ApiResponse<List<SubscriptionPlans>> getAllSubscriptionPlans() {
		List<SubscriptionPlanMaster> plansList = subscriptionPlanRepository.findAll();
		List<SubscriptionPlans> result = plansList.stream().filter(e -> e.getActive() == 1)
				.map(e -> new SubscriptionPlans(e.getPlanId(), e.getPlanName(), e.getPriceMonth(), e.getPriceYear(),
						e.getCurrency(), e.getDurationInDays(), e.getTrialDays(), e.getDescription(), e.getFeatures(),
						e.getMetadata()))
				.collect(Collectors.toList());
		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, result, null, null);
	}

	public ApiResponse<String> makePayment(SubscriptionPaymentRequest paymentRequest) {
		Subscriptions subscription = subscriptionRepository
				.findTopBySubscriber_SubscriberIdAndStatusOrderByStartDateDesc(paymentRequest.getSubscriberId(),
						"PAYMENT_PROCEEDED");

		if (subscription == null) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
					"No payment proceeded subscription found for this subscriber", null, null);
		}

		SubscriptionPayment payment = new SubscriptionPayment();
		payment.setAmount(paymentRequest.getAmount());

		PaymentMethod paymentMethod = PaymentMethod.fromValue(paymentRequest.getPaymentMethod());
		payment.setPaymentMethod(paymentMethod);

		payment.setTransactionId(paymentRequest.getTransactionId());
		payment.setPaymentDate(Timestamp.valueOf(LocalDateTime.now()));
		payment.setStatus(CommonConstants.SUCCESS_DESC);

		payment.setSubscription(subscription);
		payment.setSubscriber(subscription.getSubscriber());

		paymentRepository.save(payment);

		subscription.setStatus(SubscriptionStatus.ACTIVE.toString());
		subscriptionRepository.save(subscription);

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Payment processed and subscription activated", null,
				paymentRequest.getSubscriberId());
	}

	public ApiResponse<Object> getStatesByCountryId(Integer countryId) {
		List<State> states = stateRepository.findByCountryCountryId(countryId);
		List<KeyValuePairDTO> responses = new ArrayList<>();

		if (states.isEmpty()) {
			throw new BusinessException("Invalid CountryId", null);
		}

		for (State state : states) {

			KeyValuePairDTO stateResponse = new KeyValuePairDTO();
			stateResponse.setKey(state.getStateId());
			stateResponse.setValue(state.getName());

			responses.add(stateResponse);
		}

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, responses, null, null);
	}

	public ApiResponse<Object> getCitiesByStateAndCountryId(Integer stateId, Integer countryId) {
		List<City> cities = cityRepository.findByStateStateIdAndCountryCountryId(stateId, countryId);
		List<KeyValuePairDTO> responses = new ArrayList<>();

		if (cities.isEmpty()) {
			throw new BusinessException("Invalid Country/City Id", null);
		}

		for (City city : cities) {
			KeyValuePairDTO cityResponse = new KeyValuePairDTO();
			cityResponse.setKey(city.getCityId());
			cityResponse.setValue(city.getName());

			responses.add(cityResponse);
		}

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, responses, null, null);
	}

	public ApiResponse<Object> addArea(AreaRequest request) {
		Optional<Subscriber> subscriberOpt = subscriberRepository.findById(request.getSubscriberId());

		Optional<Countries> countryOpt = countriesRepository.findById(request.getCountryId());
		if (!countryOpt.isPresent()) {
			throw new BusinessException("Country not found with id: " + request.getCountryId(), null);
		}

		Optional<State> stateOpt = stateRepository.findById(request.getStateId());
		if (!stateOpt.isPresent()) {
			throw new BusinessException("State not found with id: " + request.getStateId(), null);
		}

		Optional<City> cityOpt = cityRepository.findById(request.getCityId());
		if (!cityOpt.isPresent()) {
			throw new BusinessException("City not found with id: " + request.getCityId(), null);
		}

		Subscriber subscriber = subscriberOpt.get();
		Countries country = countryOpt.get();
		State state = stateOpt.get();
		City city = cityOpt.get();

		Area area = new Area();
		area.setAreaName(request.getLocationName());
		area.setCountry(country);
		area.setState(state);
		area.setCity(city);
		area.setSubscriber(subscriber);

		areaRepository.save(area);

		AreaResponse locResp = new AreaResponse();
		BeanUtils.copyProperties(area, locResp);

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, locResp, null, area.getSubscriber().getSubscriberId());
	}

	public ApiResponse<Object> addCommunity(CommunityRequest request) {

		Area location = areaRepoCustom.findByAreaIdAndSubscriberId(request.getLocationId(), request.getSubscriberId());
		if (ObjectUtils.isEmpty(location)) {
			throw new BusinessException("Area not found with id: " + request.getLocationId(), null);
		}

		Optional<Subscriber> subscriberOptional = subscriberRepository.findById(request.getSubscriberId());

		Subscriber subscriber = subscriberOptional.get();

		Community community = new Community();
		community.setCommunityName(request.getCommunityName());
		// community.setLocation(location);
		community.setSubscriber(subscriber);

		communityRepository.save(community);

		CommunityResponse commuResp = new CommunityResponse();
		BeanUtils.copyProperties(community, commuResp);
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, commuResp, null, request.getSubscriberId());
	}

	public ApiResponse<Object> addBuilding(BuildingRequest request) {

		Countries country = null;
		State state = null;
		// City city = null;
		Subscriber subscriber = null;

		Optional<Subscriber> subscriberOpt = subscriberRepository.findById(request.getSubscriberId());

		Optional<Countries> countryOpt = countriesRepository.findById(request.getCountryId());
		if (!countryOpt.isPresent()) {
			throw new BusinessException("Country not found with id: " + request.getCountryId(), null);
		}

		Optional<State> stateOpt = stateRepository.findById(request.getStateId());
		if (!stateOpt.isPresent()) {
			throw new BusinessException("State not found with id: " + request.getStateId(), null);
		} else {
			state = stateOpt.get();
			country = state.getCountry();
			if (country.getCountryId().intValue() != request.getCountryId().intValue()) {
				throw new BusinessException("Invalid stateId /stateId not matching with CountryId", null);
			}
		}
		/*
		 * Optional<City> cityOpt = cityRepository.findById(request.getCityId()); if
		 * (!cityOpt.isPresent()) { throw new
		 * BusinessException("City not found with id: " + request.getCityId(), null); }
		 * else { city = cityOpt.get(); state = city.getState(); country =
		 * city.getCountry(); if (country.getCountryId().intValue() !=
		 * request.getCountryId().intValue() || state.getStateId().intValue() !=
		 * request.getStateId().intValue() || city.getCityId().intValue() !=
		 * request.getCityId().intValue()) { throw new
		 * BusinessException("countryId / stateId / cityId is not matching each other",
		 * null); } }
		 */

		subscriber = subscriberOpt.get();

		City city = new City();
		city.setName(request.getCityName());
		city.setCountry(country);
		city.setState(state);
		city.setSubscriber(subscriber);

		cityRepository.save(city);

		Area area = new Area();
		area.setAreaName(request.getAreaName());
		area.setCountry(country);
		area.setState(state);
		area.setCity(city);
		area.setSubscriber(subscriber);

		areaRepository.save(area);

		Community community = null;
		if (area != null && area.getAreaId() != null) {
			if (StringUtils.isNotBlank(request.getCommunityName())) {
				community = new Community();
				community.setCommunityName(request.getCommunityName());
				community.setArea(area);
				community.setSubscriber(subscriber);
				communityRepository.save(community);

			}

		}

		BuildingResponse buildingResp;
		try {
			Building building = new Building();
			building.setBuildingName(request.getBuildingName());
			building.setAddress(request.getAddress());
			building.setBuildingLogo(request.getBuildingLogo());
			building.setHasGym(request.getHasGym());
			building.setHasSwimpool(request.getHasSwimpool());
			building.setHasKidsPlayground(request.getHasKidsPlayground());
			building.setHasPlaycourt(request.getHasPlaycourt());
			building.setNoOfFloors(request.getNoOfFloors());
			building.setNoOfUnits(request.getNoOfunits());
			building.setArea(area);
			building.setCommunity((community != null) ? community : null);
			building.setSubscriber(subscriber);
			building.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			building.setLatitude(request.getLatitude());
			building.setLongitude(request.getLongitude());
			buildingRepository.save(building);

			buildingResp = new BuildingResponse();
			BeanUtils.copyProperties(building, buildingResp);
		} catch (Exception e) {
			throw new BusinessException("addBuilding failed ", e);
		}

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, buildingResp, null, request.getSubscriberId());
	}

	public ApiResponse<Object> addFloor(FloorRequest request) {
		Optional<Building> buildingOptional = buildingRepository.findById(request.getBuildingId());
		if (!buildingOptional.isPresent()) {
			throw new BusinessException("Building not found with id: " + request.getBuildingId(), null);
		}
		Building building = buildingOptional.get();

		Floor floor = new Floor();
		floor.setFloorName(request.getFloorName());
		floor.setBuilding(building);

		floorRepository.save(floor);

		FloorResponse floorResp = new FloorResponse();
		BeanUtils.copyProperties(floor, floorResp);

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, floorResp, null, null);
	}

	public ApiResponse<Object> addUnit(UnitRequest request) {

		Optional<UnitType> unitTypeOptional = unitTypeRepository.findById(request.getUnitTypeId());
		if (!unitTypeOptional.isPresent()) {
			throw new BusinessException("UnitType not found with id: " + request.getUnitTypeId(), null);
		}

		Optional<UnitSubType> unitSubTypeOptional = unitSubTypeRepository.findById(request.getUnitSubTypeId());
		if (!unitSubTypeOptional.isPresent()) {
			throw new BusinessException("UnitSubType not found with id: " + request.getUnitSubTypeId(), null);
		}

		UnitType unitType = unitSubTypeOptional.get().getUnitType();
		if (unitType.getUnitTypeId() != request.getUnitTypeId().intValue()) {
			throw new BusinessException("UnitTypeId not match with unitSubTypeId", null);
		}

		Optional<Building> buildingOptional = buildingRepository.findById(request.getBuildingId());
		if (!buildingOptional.isPresent()) {
			throw new BusinessException("Building not found with id: " + request.getBuildingId(), null);
		}
		Building building = buildingOptional.get();

		Optional<UnitStatus> unitStatusTypeOp = unitStatusRepository.findById(1);

		if (!unitStatusTypeOp.isPresent()) {
			throw new BusinessException("UnitStatus not found", null);
		}

		Optional<Floor> floorOptional = floorRepository.findById(request.getFloorId());
		if (!floorOptional.isPresent()) {
			throw new BusinessException("Floor not found with id: " + request.getFloorId(), null);
		}
		Floor floor = floorOptional.get();

		Unit unit = new Unit();
		unit.setBuilding(building);
		unit.setFloor(floor);
		unit.setUnitName(request.getUnitName());
		unit.setUnitType(unitType);
		unit.setUnitSubType(unitSubTypeOptional.get());
		unit.setSize(request.getSize());
		unit.setHasBalcony(request.getHasBalcony());
		unit.setUnitStatus(unitStatusTypeOp.get());

		unit.setUnitMainPic1(request.getUnitMainPic1());
		unit.setUnitPic2(request.getUnitPic2());
		unit.setUnitPic3(request.getUnitPic3());
		unit.setUnitPic4(request.getUnitPic4());
		unit.setUnitPic5(request.getUnitPic5());
		unit.setRentMonth(request.getRentMonth());
		unit.setRentYear(request.getRentYear());
		unit.setEbConnNo(request.getEbConnNo());
		unit.setWaterConnNo(request.getWaterConnNo());
		unit.setSecurityDeposit(request.getSecurityDeposit());

		unitRepository.save(unit);

		UnitResponse unitResp = new UnitResponse();
		BeanUtils.copyProperties(unit, unitResp);
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, unitResp, null, null);
	}

	public ApiResponse<String> createUserAndMergeTenant(CreateUserRequest request) throws Exception {
		User existingUser = null;
		User user = null;
		boolean existingTenant = false;
		boolean existingSubscriber = false;
		NotificationEmailResponseDTO resp = null;
		Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
		if (userOptional.isPresent()) {
			existingUser = userOptional.get();
			Set<Role> exisingRoles = existingUser.getRoles();
			for (Role r : exisingRoles) {
				if (r.getRoleId() == RoleEnum.ROLE_TENANT.getValue()) {
					existingTenant = true;
				} else if (r.getRoleId() == RoleEnum.ROLE_MGT_SUPERVISOR.getValue()) {
					existingSubscriber = true;
				}
			}

			if (existingTenant) {
				throw new BusinessException("Tenant already Registered", null);
			}

		}
		Countries nationality = countriesRepository.findById(request.getNationalityId()).orElse(null);

		UserTypeMaster userType = userTypeMasterRepository.findById(UserTypeEnum.TENANT.getValue()).orElse(null);
		if (userType == null) {
			throw new BusinessException("User Type not found", null);
		}

		Subscriber subscriber = subscriberRepository.findById(request.getSubscriberId()).orElse(null);

		Role role = roleRepository.findById(RoleEnum.ROLE_TENANT.getValue()).orElse(null);
		if (role == null) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, RoleEnum.ROLE_TENANT.getName() + " Role not found",
					null, null);
		}

		Tenant tenant = new Tenant();
		tenant.setFirstName(request.getFirstName());
		tenant.setLastName(request.getLastName());
		tenant.setEmail(request.getEmail());
		tenant.setPhoneNumber(request.getPhoneNumber());
		tenant.setDateOfBirth(CommonUtil.getDatefromString(request.getDob(), DATE_ddMMyyyy));
		tenant.setEmiratesId(CommonUtil.getLongValofObject(request.getEmiratesId()));
		tenant.setEidaExpiryDate(CommonUtil.getDatefromString(request.getEidaExpiryDate(), DATE_ddMMyyyy));
		tenant.setEidaCopy(request.getEidaCopy());
		tenant.setPassportNo(request.getPassportNo());
		tenant.setPassportExpiryDate(CommonUtil.getDatefromString(request.getPassportExpiryDate(), DATE_ddMMyyyy));
		tenant.setPassportCopy(request.getPassportCopy());
		tenant.setPhoto(request.getPhoto());
		tenant.setNationality(nationality);
		tenant = tenantRepository.save(tenant);
		String pwd = CommonUtil.generateRandomPwd();
		pwd = "123456";// remove this
		if (existingUser != null) {
			// BeanUtils.copyProperties(request, existingUser);

			existingUser.setMobileNo(Long.valueOf(request.getMobileNo()));
			existingUser.setActive(true);
			existingUser.setActive(true);
			existingUser.setEmiratesId(CommonUtil.getLongValofObject(request.getEmiratesId()));
			existingUser.setDob(CommonUtil.getDatefromString(request.getDob(), DATE_ddMMyyyy));
			existingUser.setGender(request.getGender());
			existingUser.setAddress(request.getAddress());
			if (request.getEidaCopy() != null && request.getEidaCopy().length > 1) {
				existingUser.setEidaCopy(request.getEidaCopy());
			}
			existingUser.setNationality(nationality);

			// user.setUserType(userType);
			existingUser.setSubscriber(subscriber);
			existingUser.setUpdatedDate(new Date());
			Set<Role> exisingRoles = existingUser.getRoles();
			exisingRoles.add(role);
			existingUser.setTenantId(tenant.getTenantId());
			user = userRepository.save(existingUser);

			NotifEmailDTO dto = new NotifEmailDTO();
			dto.setEmailTo(request.getEmail());
			dto.setCustomerName(request.getFirstName());
			resp = notificationService.sendTenentAccountCreationEmailExistingUser(dto);
		} else {
			user = new User();
			user.setEmail(request.getEmail());
			user.setMobileNo(Long.valueOf(request.getMobileNo()));
			user.setPassword(encoder.encode(pwd));
			user.setActive(true);
			user.setEmiratesId(CommonUtil.getLongValofObject(request.getEmiratesId()));
			user.setDob(CommonUtil.getDatefromString(request.getDob(), DATE_ddMMyyyy));
			user.setGender(request.getGender());
			user.setAddress(request.getAddress());
			user.setEidaCopy(request.getEidaCopy());
			user.setNationality(nationality);
			// user.setUserType(userType);
			user.setSubscriber(subscriber);
			user.setCreatedDate(new Date());

			Set<Role> roles = new HashSet<>();
			roles.add(role);
			user.setRoles(roles);

			user.setTenantId(tenant.getTenantId());
			user = userRepository.save(user);

			NotifEmailDTO dto = new NotifEmailDTO();
			dto.setEmailTo(request.getEmail());
			dto.setCustomerName(request.getFirstName());
			dto.setPwd(pwd);
			resp = notificationService.sendTenentAccountCreationEmail(dto);
		}

		if (resp != null && resp.isEmailSent()) {
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Tenant created successfully.", user.getUserId(),
					null);
		} else {
			throw new BusinessException("Tenant Not created", null);
		}

	}

	public ApiResponse<String> createParkingZone(ParkingZoneRequest request) {
		Optional<Subscriber> subscriberOptional = subscriberRepository.findById(request.getSubscriberId());
		if (!subscriberOptional.isPresent()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
					"Subscriber not found with ID: " + request.getSubscriberId(), null, null);
		}

		ParkingZone parkingZone = new ParkingZone();
		parkingZone.setParkZoneName(request.getParkZoneName());
		parkingZone.setSubscriber(subscriberOptional.get());

		parkingZoneRepository.save(parkingZone);

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Parking Zone created successfully.", null, null);
	}

	public ApiResponse<Object> createParking(ParkingRequest request) {

		Building building = buildingRepository.findByBuildingIdAndSubscriberId(request.getBuildingId(),
				request.getSubscriberId());
		if (ObjectUtils.isEmpty(building)) {
			throw new BusinessException("Building not found with id: " + request.getBuildingId(), null);
		}

		ParkingZone parkZone = parkingZoneRepository.findByParkZoneIdAndSubscriberId(request.getParkZoneId(),
				request.getSubscriberId());
		if (ObjectUtils.isEmpty(parkZone)) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "Parking Zone not found", null, null);
		}

		Optional<ParkingTypeEnum> ops = Arrays.stream(ParkingTypeEnum.values())
				.filter(status -> status.getValue().toString().equals(request.getParkingType())).findAny();

		if (!ops.isPresent()) {
			throw new BusinessException("Invalid ParkingType, can be any one COVERED/OPEN/GARAGE", null);
		}

		Parking parking = new Parking();
		parking.setParkingName(request.getParkingName());
		parking.setParkZone(parkZone);
		parking.setParkingType(request.getParkingType());
		parking.setIsAvailable(request.getIsAvailable());
		parking.setBuilding(building);

		parkingRepository.save(parking);

		ParkingResponse parkResp = new ParkingResponse();
		BeanUtils.copyProperties(parking, parkResp);
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, parkResp, null, null);
	}

	public ApiResponse<Object> addKey(KeyMasterRequest request) {
		if (keyMasterRepository.findByKeyNameAndSubscriberId(request.getKeyName(), request.getSubscriberId()) != null) {

			throw new BusinessException("Key name already exists.", null);
		}

		Optional<Subscriber> subscriberOptional = subscriberRepository.findById(request.getSubscriberId());

		Subscriber subscriber = subscriberOptional.get();

		KeyMaster keyMaster = new KeyMaster();
		keyMaster.setKeyName(request.getKeyName());
		keyMaster.setSubscriber(subscriber);
		keyMasterRepository.save(keyMaster);

		KeyResponse keyResp = new KeyResponse();
		BeanUtils.copyProperties(keyMaster, keyResp);
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, keyResp, null, null);
	}

	public ApiResponse<Object> addUnitKey(UnitKeysRequest request) {

		Unit unit = unitRepository.findByUnitIdAndSubscriberId(request.getUnitId(), request.getSubscriberId());

		if (ObjectUtils.isEmpty(unit)) {

			throw new BusinessException("Unit not found with ID: " + request.getUnitId(), null);
		}

		List<KeyMaster> masterKeys = keyMasterRepository.findAllKeysBySubscriberId(request.getSubscriberId());
		if (ObjectUtils.isNotEmpty(masterKeys) && ObjectUtils.isNotEmpty(request.getKeyIds())) {
			List<Integer> masterKeysId = masterKeys.stream().map(KeyMaster::getKeyId).collect(Collectors.toList());
			boolean validKeys = masterKeysId.containsAll(request.getKeyIds());
			if (!validKeys) {

				throw new BusinessException("One of the keyIds not found", null);
			}
		}

		UnitKeys unitKeys = null;
		if (ObjectUtils.isNotEmpty(request.getKeyId())) {
			KeyMaster keyMaster = keyMasterRepository.findBykeyIdAndSubscriberId(request.getKeyId(),
					request.getSubscriberId());
			if (ObjectUtils.isEmpty(keyMaster)) {

				throw new BusinessException("Key not found with ID: " + request.getKeyId(), null);
			}
			unitKeys = new UnitKeys();
			unitKeys.setUnit(unit);
			unitKeys.setKeyMaster(keyMaster);
			unitKeysRepository.save(unitKeys);
		} else if (ObjectUtils.isNotEmpty(request.getKeyIds())) {
			for (Integer keyId : request.getKeyIds()) {
				KeyMaster keyMaster = keyMasterRepository.findBykeyIdAndSubscriberId(keyId, request.getSubscriberId());
				if (ObjectUtils.isEmpty(keyMaster)) {

					throw new BusinessException("Key not found with ID: " + keyId, null);
				}
				unitKeys = new UnitKeys();
				unitKeys.setUnit(unit);
				unitKeys.setKeyMaster(keyMaster);
				unitKeysRepository.save(unitKeys);
			}
		}

		if (unitKeys != null) {
			UniKeyResponse unitKeyResp = new UniKeyResponse();
			BeanUtils.copyProperties(unitKeys, unitKeyResp);
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, unitKeyResp, null, null);
		}
		return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
	}

	public ApiResponse<Object> addTenantUnit(TenantUnitRequest request) {
		TenantUnit tenantUnit = new TenantUnit();
		Tenant tenant = tenantRepository.findByTenantIdAndSubscriberId(request.getTenantId(),
				request.getSubscriberId());

		if (ObjectUtils.isEmpty(tenant)) {
			throw new BusinessException("Tenant not found with ID: " + request.getTenantId(), null);
		}

		Unit unit = unitRepository.findByUnitIdAndSubscriberId(request.getUnitId(), request.getSubscriberId());
		if (unit == null) {
			throw new BusinessException("Unit not found with ID: " + request.getUnitId(), null);
		}

		UnitStatus unitStatus = unit.getUnitStatus();
		if (unitStatus != null && unitStatus.getUnitStatusId() == UnitStatusEnum.OCCUPIED.getValue()
				|| unitStatus.getUnitStatusId() == UnitStatusEnum.RESERVED.getValue()) { // Occupied or Reserved
			logger.info("Already this Unit registered/reserved for another tenant,unit id->{}", unit.getUnitId());
			throw new BusinessException("Already this Unit registered / reserved for another tenant", null);
		}

		if (request.getParkingId() != null) {
			Parking parking = parkingRepository.findByParkingIdAndSubscriberId(request.getParkingId(),
					request.getSubscriberId());

			if (parking == null) {

				throw new BusinessException("Parking not found with ID: " + request.getParkingId(), null);
			}
			tenantUnit.setParking(parking);
		}

		Optional<PaymentMode> paymentModeOp = paymentModeRepository.findById(request.getPaymentModeId());
		if (!paymentModeOp.isPresent()) {
			throw new BusinessException("paymentMode not found with id: " + request.getPaymentModeId(), null);
		}

		PaymentMode paymentMode = paymentModeOp.get();

		Optional<RentCycle> rentCycleOp = rentCycleRepository.findById(request.getRentCycleId());
		if (!rentCycleOp.isPresent()) {
			throw new BusinessException("rentCycle not found with id: " + request.getRentCycleId(), null);
		}

		RentCycle rentCycle = rentCycleOp.get();

		Optional<UnitStatus> unitStatusop = unitStatusRepository.findById(UnitStatusEnum.OCCUPIED.getValue());
		if (!unitStatusop.isPresent()) {
			throw new BusinessException("Invalid UnitStatus", null);
		}

		unit.setUnitStatus(unitStatusop.get());

		tenantUnit.setTenant(tenant);
		tenantUnit.setUnit(unit);
		tenantUnit.setTenurePeriodMonth(request.getTenurePeriodMonth());
		tenantUnit.setRentCycle(rentCycle);
		tenantUnit.setExpired(false);
		tenantUnit.setActive(true);
		tenantUnit.setPaymentMode(paymentMode);
		tenantUnit.setCreatedTime(new Date());
		tenantUnit.setCreatedBy(request.getSubscriberId());

		tenantUnitRepository.save(tenantUnit);

		TenureDetails tenureDetails = new TenureDetails();
		tenureDetails.setTenancyStartDate(CommonUtil.getDatefromString(request.getTenancyStartDate(), DATE_ddMMyyyy));
		tenureDetails.setTenantUnit(tenantUnit);
		tenureDetails.setTenancyEndDate(calculateTenancyEndDate(request));
		tenureDetails.setCreatedBy(request.getSubscriberId());

		tenureDetailsRepository.save(tenureDetails);

		if (tenureDetails != null && tenureDetails.getTenantTenureId() != null) {
			TenantUnitResponse tenantUnitResp = new TenantUnitResponse();
			BeanUtils.copyProperties(tenantUnit, tenantUnitResp);
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, tenantUnitResp, null, null);
		}
		return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
	}

	private Date calculateTenancyEndDate(TenantUnitRequest request) {

		LocalDate tenStartDate = CommonUtil.getLocalDatefromString(request.getTenancyStartDate(),
				CommonConstants.DATE_ddMMyyyy);
		LocalDate tenEndDate = tenStartDate.plusMonths(request.getTenurePeriodMonth());
		return CommonUtil.getDatefromLocalDate(tenEndDate);

	}

	@Override
	public ApiResponse<Object> addDepartment(@Valid DepartmentRequest request) {

		if (departmentMasRepository.findByDeptNameAndSubscriberId(request.getDeptName(),
				request.getSubscriberId()) != null) {
			throw new BusinessException("Dept name already exists.", null);
		}
		Optional<Subscriber> subscriberOptional = subscriberRepository.findById(request.getSubscriberId());

		Subscriber subscriber = subscriberOptional.get();

		DepartmentMaster deptMas = new DepartmentMaster();
		deptMas.setDeptName(request.getDeptName());
		deptMas.setActive(INT_ONE_YES);
		deptMas.setSubscriber(subscriber);

		departmentMasRepository.save(deptMas);
		if (deptMas != null) {
			DeptMasResponse deptMasResp = new DeptMasResponse();
			BeanUtils.copyProperties(deptMas, deptMasResp);
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, deptMasResp, null, null);
		}
		return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
	}

	public ApiResponse<Object> reserveUnit(Integer subscriberId, ReserveUnitRequest request) {

		try {

			ProductConfig productConfig = productConfigRepository.findBySubscriberId(subscriberId);
			if (productConfig == null || (productConfig != null && productConfig.getConfigjson() == null)) {
				throw new BusinessException("Product configuration not found for subscriberId: " + subscriberId, null);
			}
			Map<String, Object> configJson = productConfig.getConfigjson();

			Boolean unitReserveOption = (Boolean) configJson.get("unitReserveOption");

			if (unitReserveOption == null || (!unitReserveOption)) {
				throw new BusinessException("unitReserveOption not enabled for this subscriber", null);
			}

			Unit unit = unitRepository.findByUnitId(request.getUnitId())
					.orElseThrow(() -> new BusinessException("Unit not found", null));

			if (!unit.getUnitStatus().getUnitStatusName().equals(UnitStatusEnum.VACANT.toString())) {
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Unit is not available for reservation. Status: " + unit.getUnitStatus().getUnitStatusName(),
						null, null);
			}

			@SuppressWarnings("unchecked")
			Map<String, Object> unitReserve = (Map<String, Object>) configJson.get("unitReserve");
			if (unitReserve == null) {
				throw new BusinessException("unitReserve not found in product config", null);
			}

			Boolean unitReservePaymentOption = (Boolean) unitReserve.get("unitReservePaymentOption");

			Integer unitReserveDays = (unitReserve.get("unitReserveDays") != null)
					? (Integer) unitReserve.get("unitReserveDays")
					: null;

			if (unitReserveDays == null) {
				throw new BusinessException("unitReserveDays not found in product config", null);
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, unitReserveDays);
			Date reserveEndDate = calendar.getTime();

			User newUser = new User();
			newUser.setEmail(request.getEmail());
			newUser.setMobileNo(Long.valueOf(request.getMobileNo()));
			newUser.setPassword("123456");
			newUser.setActive(false);
			newUser.setEmiratesId(CommonUtil.getLongValofObject(request.getEmiratesId()));
			newUser.setDob(CommonUtil.getDatefromString(request.getDob(), DATE_ddMMyyyy));
			newUser.setGender(request.getGender());
			newUser.setAddress(request.getAddress());
			newUser.setEidaCopy(request.getEidaCopy());

			Countries nationality = countriesRepository.findById(request.getNationalityId()).orElse(null);

			newUser.setNationality(nationality);
			userRepository.save(newUser);

			unit.setUnitStatus(unitStatusRepository.findByUnitStatusName(UnitStatusEnum.RESERVED.toString()));
			unitRepository.save(unit);

			UnitReserveDetails reserveDetails = new UnitReserveDetails();
			reserveDetails.setUnit(unit);
			reserveDetails.setUser(newUser);
			reserveDetails.setReserveStartDate(new Date());
			reserveDetails.setReserveEndDate(reserveEndDate);
			reserveDetails.setPaymentRequired((unitReservePaymentOption) ? 1 : 0);
			unitReserveDetailsRepository.save(reserveDetails);

			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Unit successfully reserved", null, null);

		} catch (Exception e) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "Failed to reserve unit: " + e.getMessage(), null,
					null);
		}
	}

	@SuppressWarnings("rawtypes")
	public ApiResponse<Object> getAllBuildings(Integer subscriberId, PaginationRequest paginationRequest) {

		Sort sort = paginationRequest.getSortDirection().equalsIgnoreCase("desc")
				? Sort.by(paginationRequest.getSortBy()).descending()
				: Sort.by(paginationRequest.getSortBy()).ascending();
		PageRequest pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), sort);
		List<Building> listOfBuildings = buildingRepository.findAllBySubscriberId(subscriberId);

		List<BuildingDetailDTO> listBD = new ArrayList<>();

		for (Building b : listOfBuildings) {
			BuildingDetailDTO bt = new BuildingDetailDTO();
			BeanUtils.copyProperties(b, bt);
			if (b.getCommunity() != null) {
				Community ct = b.getCommunity();
				bt.setCommunityId(ct.getCommunityId());
				ct.setCommunityName(ct.getCommunityName());
				if (ct.getArea() != null) {
					Area ae = b.getArea();
					bt.setAreaId(ae.getAreaId());
					bt.setAreaName(ae.getAreaName());
					Countries country = ae.getCountry();
					bt.setCountryId(country.getCountryId());
					bt.setCountryName(country.getName());
					State st = ae.getState();
					bt.setStateId(st.getStateId());
					bt.setStateName(st.getName());
					City city = ae.getCity();
					bt.setCityId(city.getCityId());
					bt.setCityName(city.getName());

				}
			} else if (b.getArea() != null) {
				Area ae = b.getArea();
				bt.setAreaId(ae.getAreaId());
				bt.setAreaName(ae.getAreaName());
				Countries country = ae.getCountry();
				bt.setCountryId(country.getCountryId());
				bt.setCountryName(country.getName());
				State st = ae.getState();
				bt.setStateId(st.getStateId());
				bt.setStateName(st.getName());
				City city = ae.getCity();
				bt.setCityId(city.getCityId());
				bt.setCityName(city.getName());

			}
			listBD.add(bt);
		}

		Page<BuildingDetailDTO> pageBD = PaginationUtils.convertListToPage(listBD, pageable);

		PaginationResponse pgResp = new PaginationResponse<>(pageBD.getContent(), pageBD.getNumber(),
				pageBD.getTotalPages(), pageBD.getTotalElements(), pageBD.isFirst(), pageBD.isLast());
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, pgResp, null, null);

	}

	@SuppressWarnings("rawtypes")
	public ApiResponse<Object> getAllUnitsByBuildingId(Integer subscriberId, Integer buildingId,
			PaginationRequest paginationRequest) {
		Sort sort = paginationRequest.getSortDirection().equalsIgnoreCase("desc")
				? Sort.by(paginationRequest.getSortBy()).descending()
				: Sort.by(paginationRequest.getSortBy()).ascending();
		PageRequest pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), sort);
		Page<Unit> unitPage = unitRepository.findUnitsByBuildingIdWithPagination(buildingId, pageable);

		List<UnitDetailResponse> unitDTOs = unitPage.getContent().stream().map(unit -> {
			UnitDetailResponse dto = new UnitDetailResponse();
			BeanUtils.copyProperties(unit, dto);
			if (unit.getBuilding() != null)
				dto.setBuildingId(unit.getBuilding().getBuildingId());
			if (unit.getFloor() != null)
				dto.setFloorId(unit.getFloor().getFloorId());
			if (unit.getUnitType() != null)
				dto.setUnitTypeId(unit.getUnitType().getUnitTypeId());
			if (unit.getUnitSubType() != null)
				dto.setUnitSubTypeId(unit.getUnitSubType().getUnitSubtypeId());
			if (unit.getUnitStatus() != null)
				dto.setUnitStatusId(unit.getUnitStatus().getUnitStatusId());
			return dto;
		}).collect(Collectors.toList());

		PaginationResponse response = new PaginationResponse<>(unitDTOs, unitPage.getNumber(), unitPage.getTotalPages(),
				unitPage.getTotalElements(), unitPage.isFirst(), unitPage.isLast());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, response, null, null);
	}

	@SuppressWarnings("rawtypes")
	public ApiResponse<Object> getAllTenantsByBuildingId(Integer subscriberId, Integer buildingId,
			PaginationRequest paginationRequest) {
		Sort sort = paginationRequest.getSortDirection().equalsIgnoreCase("desc")
				? Sort.by(paginationRequest.getSortBy()).descending()
				: Sort.by(paginationRequest.getSortBy()).ascending();
		PageRequest pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), sort);
		Page<TenantUnit> tenantUnitPage = tenantUnitRepository.findTenantsByBuildingIdWithPagination(buildingId,
				pageable);

		List<TenantDetailResponse> tenantDTOs = tenantUnitPage.getContent().stream().map(tenantUnit -> {
			TenantDetailResponse dto = new TenantDetailResponse();
			Tenant tenant = tenantUnit.getTenant();
			BeanUtils.copyProperties(tenant, dto);
			if (tenantUnit.getUnit() != null) {
				dto.setUnitId(tenantUnit.getUnit().getUnitId());
				if (tenantUnit.getUnit().getBuilding() != null) {
					dto.setBuildingId(tenantUnit.getUnit().getBuilding().getBuildingId());
				}
			}
			if (tenantUnit.getParking() != null) {
				dto.setParkingId(tenantUnit.getParking().getParkingId());
			}
			return dto;
		}).collect(Collectors.toList());

		PaginationResponse response = new PaginationResponse<>(tenantDTOs, tenantUnitPage.getNumber(),
				tenantUnitPage.getTotalPages(), tenantUnitPage.getTotalElements(), tenantUnitPage.isFirst(),
				tenantUnitPage.isLast());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, response, null, null);
	}

}