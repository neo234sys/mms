package com.sbmtech.mms.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmtech.mms.dto.NotifEmailDTO;
import com.sbmtech.mms.dto.NotificationEmailResponseDTO;
import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.ChannelMaster;
import com.sbmtech.mms.models.City;
import com.sbmtech.mms.models.Community;
import com.sbmtech.mms.models.Countries;
import com.sbmtech.mms.models.Floor;
import com.sbmtech.mms.models.KeyMaster;
import com.sbmtech.mms.models.Otp;
import com.sbmtech.mms.models.Parking;
import com.sbmtech.mms.models.ParkingZone;
import com.sbmtech.mms.models.PaymentMethod;
import com.sbmtech.mms.models.Role;
import com.sbmtech.mms.models.State;
import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.SubscriberLocation;
import com.sbmtech.mms.models.SubscriptionPayment;
import com.sbmtech.mms.models.SubscriptionPlanMaster;
import com.sbmtech.mms.models.Subscriptions;
import com.sbmtech.mms.models.Tenant;
import com.sbmtech.mms.models.TenantUnit;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.models.UnitKeys;
import com.sbmtech.mms.models.User;
import com.sbmtech.mms.models.UserTypeMaster;
import com.sbmtech.mms.payload.request.AdditionalDetailsRequest;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.BuildingRequest;
import com.sbmtech.mms.payload.request.CommunityRequest;
import com.sbmtech.mms.payload.request.CreateUserRequest;
import com.sbmtech.mms.payload.request.FloorRequest;
import com.sbmtech.mms.payload.request.KeyMasterRequest;
import com.sbmtech.mms.payload.request.ParkingRequest;
import com.sbmtech.mms.payload.request.ParkingZoneRequest;
import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.SubscriberLocationRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.SubscriptionPaymentRequest;
import com.sbmtech.mms.payload.request.SubscriptionRequest;
import com.sbmtech.mms.payload.request.TenantUnitRequest;
import com.sbmtech.mms.payload.request.UnitKeysRequest;
import com.sbmtech.mms.payload.request.UnitRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.payload.response.CityResponse;
import com.sbmtech.mms.payload.response.StateResponse;
import com.sbmtech.mms.repository.BuildingRepository;
import com.sbmtech.mms.repository.ChannelMasterRepository;
import com.sbmtech.mms.repository.CityRepository;
import com.sbmtech.mms.repository.CommunityRepository;
import com.sbmtech.mms.repository.CountriesRepository;
import com.sbmtech.mms.repository.FloorRepository;
import com.sbmtech.mms.repository.KeyMasterRepository;
import com.sbmtech.mms.repository.OtpRepository;
import com.sbmtech.mms.repository.ParkingRepository;
import com.sbmtech.mms.repository.ParkingZoneRepository;
import com.sbmtech.mms.repository.RoleRepository;
import com.sbmtech.mms.repository.StateRepository;
import com.sbmtech.mms.repository.SubscriberLocationRepository;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.SubscriptionPaymentRepository;
import com.sbmtech.mms.repository.SubscriptionPlanMasterRepository;
import com.sbmtech.mms.repository.SubscriptionRepository;
import com.sbmtech.mms.repository.TenantRepository;
import com.sbmtech.mms.repository.TenantUnitRepository;
import com.sbmtech.mms.repository.UnitKeysRepository;
import com.sbmtech.mms.repository.UnitRepository;
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
	private SubscriberLocationRepository subscriberLocationRepository;

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
		Role defaultRole = roleRepository.findById(2)
				.orElseThrow(() -> new RuntimeException("Role not found for ID 2"));
		user.getRoles().add(defaultRole);
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

	@Transactional
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

			Otp otp = otpRepository.findByReferenceId(request.getSubscriberId());
			if (otp == null) {
				otp = new Otp();
				otp.setReferenceId(request.getSubscriberId());
			}
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
				return new ApiResponse<>(1, "success", "OTP resent successfully.", null, subscriber.getSubscriberId());
			} else {
				return new ApiResponse<>(0, "failure", "Failed to send OTP email.", null, null);
			}

		} catch (Exception e) {
			return new ApiResponse<>(0, "failure", "An error occurred: " + e.getMessage(), null, null);
		}
	}

	public ApiResponse<List<ChannelMaster>> getAllChannels() {
		List<ChannelMaster> channels = channelMasterRepository.findAll();
		if (channels.isEmpty()) {
			return new ApiResponse<>(0, "failure", null, null, null);
		}
		return new ApiResponse<>(1, "success", channels, null, null);
	}

	public ApiResponse<List<Countries>> getAllCountries() {
		List<Countries> countries = countriesRepository.findAll();
		if (countries.isEmpty()) {
			return new ApiResponse<>(0, "failure", null, null, null);
		}
		return new ApiResponse<>(1, "Success", countries, null, null);
	}

	@Transactional
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

		return new ApiResponse<>(1, "success", "Additional details added successfully.", null,
				subscriber.getSubscriberId());
	}

	@Transactional
	public ApiResponse<String> saveSubscription(SubscriptionRequest subscriptionRequest) {
		if (subscriptionRequest.getPlanId() == null
				|| !planMasterRepository.existsById(subscriptionRequest.getPlanId())) {
			return new ApiResponse<>(0, "Invalid plan_id", null, null, null);
		}

		if (subscriptionRequest.getSubscriberId() == null
				|| !subscriberRepository.existsById(subscriptionRequest.getSubscriberId())) {
			return new ApiResponse<>(0, "Invalid subscriber_id", null, null, null);
		}

		if (subscriptionRequest.getChannelId() == null
				|| !channelMasterRepository.existsById(subscriptionRequest.getChannelId())) {
			return new ApiResponse<>(0, "Invalid channel_id", null, null, null);
		}

		Subscriptions existingSubscription = subscriptionRepository
				.findTopBySubscriber_SubscriberIdAndStatusOrderByStartDateDesc(subscriptionRequest.getSubscriberId(),
						"ACTIVE");

		if (existingSubscription != null) {
			return new ApiResponse<>(0, "Subscriber already has an active subscription", null, null, null);
		}

		existingSubscription = subscriptionRepository.findTopBySubscriber_SubscriberIdAndStatusOrderByStartDateDesc(
				subscriptionRequest.getSubscriberId(), "PAYMENT_PROCEEDED");

		if (existingSubscription != null) {
			return new ApiResponse<>(0, "Subscriber already has a subscription with status PAYMENT_PROCEEDED", null,
					null, null);
		}

		Subscriptions subscription = new Subscriptions();

		subscription.setStartDate(subscriptionRequest.getStartDate());
		subscription.setEndDate(subscriptionRequest.getEndDate());
		subscription.setStatus("PAYMENT_PROCEEDED"); // Status is set as PaymentProceeded initially
		subscription.setIsFree(subscriptionRequest.getIsFree());

		SubscriptionPlanMaster plan = new SubscriptionPlanMaster();
		plan.setPlanId(subscriptionRequest.getPlanId());
		subscription.setPlan(plan);

		Subscriber subscriber = new Subscriber();
		subscriber.setSubscriberId(subscriptionRequest.getSubscriberId());
		subscription.setSubscriber(subscriber);

		subscription.setChannelId(subscriptionRequest.getChannelId());
		subscription.setCreatedDate(LocalDateTime.now());

		subscriptionRepository.save(subscription);

		return new ApiResponse<>(1, "success", "Subscription saved successfully", null,
				subscription.getSubscriber().getSubscriberId());
	}

	public ApiResponse<List<SubscriptionPlanMaster>> getAllSubscriptionPlans() {
		List<SubscriptionPlanMaster> plans = planMasterRepository.findAll();
		if (plans.isEmpty()) {
			return new ApiResponse<>(0, "failure", null, null, null);
		}
		return new ApiResponse<>(1, "success", plans, null, null);
	}

	@Transactional
	public ApiResponse<String> makePayment(SubscriptionPaymentRequest paymentRequest) {
		Subscriptions subscription = subscriptionRepository
				.findTopBySubscriber_SubscriberIdAndStatusOrderByStartDateDesc(paymentRequest.getSubscriberId(),
						"PAYMENT_PROCEEDED");

		if (subscription == null) {
			return new ApiResponse<>(0, "No payment proceeded subscription found for this subscriber", null, null,
					null);
		}

		SubscriptionPayment payment = new SubscriptionPayment();
		payment.setAmount(paymentRequest.getAmount());

		PaymentMethod paymentMethod = PaymentMethod.fromValue(paymentRequest.getPaymentMethod());
		payment.setPaymentMethod(paymentMethod);

		payment.setTransactionId(paymentRequest.getTransactionId());
		payment.setPaymentDate(Timestamp.valueOf(LocalDateTime.now()));
		payment.setStatus("SUCCESS");

		payment.setSubscription(subscription);
		payment.setSubscriber(subscription.getSubscriber());

		paymentRepository.save(payment);

		subscription.setStatus("ACTIVE");
		subscriptionRepository.save(subscription);

		return new ApiResponse<>(1, "success", "Payment processed and subscription activated", null,
				paymentRequest.getSubscriberId());
	}

	public ApiResponse<List<StateResponse>> getStatesByCountryId(Integer countryId) {
		List<State> states = stateRepository.findByCountryCountryId(countryId);
		List<StateResponse> responses = new ArrayList<>();

		if (states.isEmpty()) {
			return new ApiResponse<>(0, "failure", null, null, null);
		}

		for (State state : states) {

			StateResponse stateResponse = new StateResponse();
			stateResponse.setStateId(state.getStateId());
			stateResponse.setName(state.getName());

			responses.add(stateResponse);
		}

		return new ApiResponse<>(1, "success", responses, null, null);
	}

	public ApiResponse<List<CityResponse>> getCitiesByStateAndCountryId(Integer stateId, Integer countryId) {
		List<City> cities = cityRepository.findByStateStateIdAndCountryCountryId(stateId, countryId);
		List<CityResponse> responses = new ArrayList<>();

		if (cities.isEmpty()) {
			return new ApiResponse<>(0, "failure", null, null, null);
		}

		for (City city : cities) {
			CityResponse cityResponse = new CityResponse();
			cityResponse.setCityId(city.getCityId());
			cityResponse.setName(city.getName());

			responses.add(cityResponse);
		}

		return new ApiResponse<>(1, "success", responses, null, null);
	}

	@Transactional
	public ApiResponse<String> addSubscriberLocation(SubscriberLocationRequest request) {
		Optional<Subscriber> subscriberOpt = subscriberRepository.findById(request.getSubscriberId());
		if (!subscriberOpt.isPresent()) {
			return new ApiResponse<>(0, "failure", "Subscriber not found with id: " + request.getSubscriberId(), null,
					null);
		}

		Optional<Countries> countryOpt = countriesRepository.findById(request.getCountryId());
		if (!countryOpt.isPresent()) {
			return new ApiResponse<>(0, "failure", "Country not found with id: " + request.getCountryId(), null, null);
		}

		Optional<State> stateOpt = stateRepository.findById(request.getStateId());
		if (!stateOpt.isPresent()) {
			return new ApiResponse<>(0, "failure", "State not found with id: " + request.getStateId(), null, null);
		}

		Optional<City> cityOpt = cityRepository.findById(request.getCityId());
		if (!cityOpt.isPresent()) {
			return new ApiResponse<>(0, "failure", "City not found with id: " + request.getCityId(), null, null);
		}

		Subscriber subscriber = subscriberOpt.get();
		Countries country = countryOpt.get();
		State state = stateOpt.get();
		City city = cityOpt.get();

		SubscriberLocation subscriberLocation = new SubscriberLocation();
		subscriberLocation.setLocationName(request.getLocationName());
		subscriberLocation.setCountry(country);
		subscriberLocation.setState(state);
		subscriberLocation.setCity(city);
		subscriberLocation.setSubscriber(subscriber);

		subscriberLocationRepository.save(subscriberLocation);

		return new ApiResponse<>(1, "success", "Subscriber location added successfully.", null,
				subscriberLocation.getSubscriber().getSubscriberId());
	}

	@Transactional
	public ApiResponse<String> addCommunity(CommunityRequest request) {
		Optional<SubscriberLocation> locationOptional = subscriberLocationRepository.findById(request.getLocationId());
		if (!locationOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Location not found with id: " + request.getLocationId(), null,
					null);
		}
		SubscriberLocation location = locationOptional.get();

		Optional<Subscriber> subscriberOptional = subscriberRepository.findById(request.getSubscriberId());
		if (!subscriberOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Subscriber not found with id: " + request.getSubscriberId(), null,
					null);
		}
		Subscriber subscriber = subscriberOptional.get();

		Community community = new Community();
		community.setCommunityName(request.getCommunityName());
		community.setLocation(location);
		community.setSubscriber(subscriber);

		communityRepository.save(community);

		return new ApiResponse<>(1, "success", "Community added successfully.", null, request.getSubscriberId());
	}

	@Transactional
	public ApiResponse<String> addBuilding(BuildingRequest request) {
		Optional<Community> communityOptional = communityRepository.findById(request.getCommunityId());
		if (!communityOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Community not found with id: " + request.getCommunityId(), null,
					null);
		}
		Community community = communityOptional.get();

		Optional<Subscriber> subscriberOptional = subscriberRepository.findById(request.getSubscriberId());
		if (!subscriberOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Subscriber not found with id: " + request.getSubscriberId(), null,
					null);
		}
		Subscriber subscriber = subscriberOptional.get();

		Building building = new Building();
		building.setBuildingName(request.getBuildingName());
		building.setAddress(request.getAddress());
		building.setBuildingLogo(request.getBuildingLogo());
		building.setHasGym(request.getHasGym());
		building.setHasSwimpool(request.getHasSwimpool());
		building.setHasKidsPlayground(request.getHasKidsPlayground());
		building.setHasPlaycourt(request.getHasPlaycourt());
		building.setCommunity(community);
		building.setSubscriber(subscriber);
		building.setCreatedAt(new Timestamp(System.currentTimeMillis()));

		buildingRepository.save(building);

		return new ApiResponse<>(1, "success", "Building added successfully.", null, request.getSubscriberId());
	}

	@Transactional
	public ApiResponse<String> addFloor(FloorRequest request) {
		Optional<Building> buildingOptional = buildingRepository.findById(request.getBuildingId());
		if (!buildingOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Building not found with id: " + request.getBuildingId(), null,
					null);
		}
		Building building = buildingOptional.get();

		Floor floor = new Floor();
		floor.setFloorName(request.getFloorName());
		floor.setBuilding(building);

		floorRepository.save(floor);

		return new ApiResponse<>(1, "success", "Floor added successfully.", null, request.getBuildingId());
	}

	@Transactional
	public ApiResponse<String> addUnit(UnitRequest request) {
		Optional<Building> buildingOptional = buildingRepository.findById(request.getBuildingId());
		if (!buildingOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Building not found with id: " + request.getBuildingId(), null,
					null);
		}
		Building building = buildingOptional.get();

		Optional<Floor> floorOptional = floorRepository.findById(request.getFloorId());
		if (!floorOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Floor not found with id: " + request.getFloorId(), null, null);
		}
		Floor floor = floorOptional.get();

		Unit unit = new Unit();
		unit.setBuilding(building);
		unit.setFloor(floor);
		unit.setUnitName(request.getUnitName());
		unit.setUnitType(request.getUnitType());
		unit.setUnitSubType(request.getUnitSubType());
		unit.setSize(request.getSize());
		unit.setHasBalcony(request.getHasBalcony());
		unit.setIsOccupied(request.getIsOccupied());
		unit.setIsUnderMaintenance(request.getIsUnderMaintenance());
		unit.setUnitMainPic1(request.getUnitMainPic1());
		unit.setUnitPic2(request.getUnitPic2());
		unit.setUnitPic3(request.getUnitPic3());
		unit.setUnitPic4(request.getUnitPic4());
		unit.setUnitPic5(request.getUnitPic5());

		unitRepository.save(unit);

		return new ApiResponse<>(1, "success", "Unit added successfully.", null, null);
	}

	@Transactional
	public ApiResponse<String> createUserAndMergeTenant(CreateUserRequest request) {
		Countries nationality = countriesRepository.findById(request.getNationalityId()).orElse(null);
		if (nationality == null) {
			return new ApiResponse<>(0, "failure", "Country not found with ID: " + request.getNationalityId(), null,
					null);
		}

		UserTypeMaster userType = userTypeMasterRepository.findById(4).orElse(null);
		if (userType == null) {
			return new ApiResponse<>(0, "failure", "User Type not found with ID 4", null, null);
		}

		Subscriber subscriber = subscriberRepository.findById(request.getSubscriberId()).orElse(null);
		if (subscriber == null) {
			return new ApiResponse<>(0, "failure", "Subscriber not found with ID: " + request.getSubscriberId(), null,
					null);
		}

		Role role = roleRepository.findById(8).orElse(null);
		if (role == null) {
			return new ApiResponse<>(0, "failure", "Role not found with ID 8", null, null);
		}

		Tenant tenant = new Tenant();
		tenant.setFirstName(request.getFirstName());
		tenant.setLastName(request.getLastName());
		tenant.setEmail(request.getEmail());
		tenant.setPhoneNumber(request.getPhoneNumber());
		tenant.setDateOfBirth(request.getDob());
		tenant.setEmiratesId(request.getEmiratesId());
		tenant.setEidaExpiryDate(request.getEidaExpiryDate());
		tenant.setEidaCopy(request.getEidaCopy());
		tenant.setPassportNo(request.getPassportNo());
		tenant.setPassportExpiryDate(request.getPassportExpiryDate());
		tenant.setPassportCopy(request.getPassportCopy());
		tenant.setPhoto(request.getPhoto());
		tenant.setNationality(nationality);
		tenant = tenantRepository.save(tenant);

		User user = new User();
		user.setEmail(request.getEmail());
		user.setMobileNo(request.getMobileNo());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setActive(true);
		user.setEmiratesId(request.getEmiratesId());
		user.setDob(request.getDob());
		user.setGender(request.getGender());
		user.setAddress(request.getAddress());
		user.setEidaCopy(request.getEidaCopy());
		user.setNationality(nationality);
		user.setUserType(userType);
		user.setSubscriber(subscriber);
		user.setCreatedDate(new Date());

		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);

		user.setTenantId(tenant.getTenantId());
		user = userRepository.save(user);

		return new ApiResponse<>(1, "success", "Tenant and User created successfully.", user.getUserId(),
				user.getSubscriber().getSubscriberId());
	}

	public ApiResponse<String> createParkingZone(ParkingZoneRequest request) {
		Optional<Subscriber> subscriberOptional = subscriberRepository.findById(request.getSubscriberId());
		if (!subscriberOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Subscriber not found with ID: " + request.getSubscriberId(), null,
					null);
		}

		ParkingZone parkingZone = new ParkingZone();
		parkingZone.setParkZoneName(request.getParkZoneName());
		parkingZone.setSubscriber(subscriberOptional.get());

		parkingZoneRepository.save(parkingZone);

		return new ApiResponse<>(1, "success", "Parking Zone created successfully.", null, null);
	}

	@Transactional
	public ApiResponse<String> createParking(ParkingRequest request) {
		Optional<ParkingZone> parkZoneOptional = parkingZoneRepository.findById(request.getParkZoneId());
		if (!parkZoneOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Parking Zone not found", null, null);
		}

		ParkingZone parkZone = parkZoneOptional.get();

		Parking parking = new Parking();
		parking.setParkingName(request.getParkingName());
		parking.setParkZone(parkZone);
		parking.setParkingType(request.getParkingType());
		parking.setIsAvailable(request.getIsAvailable());

		parkingRepository.save(parking);

		return new ApiResponse<>(1, "success", "Parking created successfully.", null, null);
	}

	@Transactional
	public ApiResponse<String> addKey(KeyMasterRequest request) {
		if (keyMasterRepository.findByKeyName(request.getKeyName()).isPresent()) {
			return new ApiResponse<>(0, "failure", "Key name already exists.", null, null);
		}

		KeyMaster keyMaster = new KeyMaster();
		keyMaster.setKeyName(request.getKeyName());
		keyMasterRepository.save(keyMaster);

		return new ApiResponse<>(1, "success", "Key added successfully.", null, null);
	}

	@Transactional
	public ApiResponse<String> addUnitKey(UnitKeysRequest request) {
		Optional<Unit> unitOptional = unitRepository.findById(request.getUnitId());
		if (!unitOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Unit not found with ID: " + request.getUnitId(), null, null);
		}

		Optional<KeyMaster> keyMasterOptional = keyMasterRepository.findById(request.getKeyId());
		if (!keyMasterOptional.isPresent()) {
			return new ApiResponse<>(0, "failure", "Key not found with ID: " + request.getKeyId(), null, null);
		}

		Unit unit = unitOptional.get();
		KeyMaster keyMaster = keyMasterOptional.get();

		boolean mappingExists = unitKeysRepository.existsByUnitAndKeyMaster(unit, keyMaster);
		if (mappingExists) {
			return new ApiResponse<>(0, "failure", "Unit-Key mapping already exists.", null, null);
		}

		UnitKeys unitKeys = new UnitKeys();
		unitKeys.setUnit(unit);
		unitKeys.setKeyMaster(keyMaster);
		unitKeysRepository.save(unitKeys);

		return new ApiResponse<>(1, "success", "Unit-Key mapping added successfully.", null, null);
	}

	@Transactional
	public ApiResponse<String> addTenantUnit(TenantUnitRequest request) {
		Tenant tenant = tenantRepository.findById(request.getTenantId()).orElse(null);
		if (tenant == null) {
			return new ApiResponse<>(0, "failure", "Tenant not found with ID: " + request.getTenantId(), null, null);
		}

		Unit unit = unitRepository.findById(request.getUnitId()).orElse(null);
		if (unit == null) {
			return new ApiResponse<>(0, "failure", "Unit not found with ID: " + request.getUnitId(), null, null);
		}

		Parking parking = parkingRepository.findById(request.getParkingId()).orElse(null);
		if (parking == null) {
			return new ApiResponse<>(0, "failure", "Parking not found with ID: " + request.getParkingId(), null, null);
		}

		UnitKeys unitKeys = unitKeysRepository.findById(request.getUnitKeysId()).orElse(null);
		if (unitKeys == null) {
			return new ApiResponse<>(0, "failure", "Unit Keys not found with ID: " + request.getUnitKeysId(), null,
					null);
		}

		TenantUnit tenantUnit = new TenantUnit();
		tenantUnit.setTenant(tenant);
		tenantUnit.setUnit(unit);
		tenantUnit.setParking(parking);
		tenantUnit.setUnitKeys(unitKeys);
		tenantUnit.setRegisteredDate(request.getRegisteredDate());
		tenantUnit.setSecurityDeposit(request.getSecurityDeposit());
		tenantUnit.setRent(request.getRent());
		tenantUnit.setRentCycle(request.getRentCycle());
		tenantUnit.setExpired(false);
		tenantUnit.setActive(true);
		tenantUnit.setRentPaymentMode(request.getRentPaymentMode());
		tenantUnit.setCreatedTime(new Date());

		tenantUnitRepository.save(tenantUnit);

		return new ApiResponse<>(1, "success", "Tenant-Unit mapping added successfully.", null, null);
	}

}