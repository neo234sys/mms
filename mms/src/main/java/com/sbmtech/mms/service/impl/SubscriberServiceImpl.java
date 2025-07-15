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
import java.util.Base64;
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
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmtech.mms.constant.CommonConstants;
import com.sbmtech.mms.constant.SubscriptionStatus;
import com.sbmtech.mms.dto.BedspaceDTO;
import com.sbmtech.mms.dto.BedspaceSearchCriteria;
import com.sbmtech.mms.dto.BuildingDetailDTO;
import com.sbmtech.mms.dto.BuildingSearchDto;
import com.sbmtech.mms.dto.FloorDTO;
import com.sbmtech.mms.dto.KeyValuePairDTO;
import com.sbmtech.mms.dto.NotifEmailDTO;
import com.sbmtech.mms.dto.NotificationEmailResponseDTO;
import com.sbmtech.mms.dto.ParkingDTO;
import com.sbmtech.mms.dto.ParkingZoneDTO;
import com.sbmtech.mms.dto.ParkingZoneSimpleDTO;
import com.sbmtech.mms.dto.S3DownloadDto;
import com.sbmtech.mms.dto.S3UploadDto;
import com.sbmtech.mms.dto.S3UploadObjectDto;
import com.sbmtech.mms.dto.TenantDTO;
import com.sbmtech.mms.dto.TenantSimpleDTO;
import com.sbmtech.mms.dto.TenantUnitDTO;
import com.sbmtech.mms.dto.TenureDetailsDTO;
import com.sbmtech.mms.dto.UnitAllDTO;
import com.sbmtech.mms.dto.UnitDTO;
import com.sbmtech.mms.dto.UnitKeyDTO;
import com.sbmtech.mms.dto.UnitReserveDetailsDTO;
import com.sbmtech.mms.dto.UserSimpleDTO;
import com.sbmtech.mms.exception.BusinessException;
import com.sbmtech.mms.models.Area;
import com.sbmtech.mms.models.Bedspace;
import com.sbmtech.mms.models.BedspaceBathroomTypeMaster;
import com.sbmtech.mms.models.BedspaceCatMaster;
import com.sbmtech.mms.models.BedspacePartitionMaster;
import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.ChannelMaster;
import com.sbmtech.mms.models.City;
import com.sbmtech.mms.models.Community;
import com.sbmtech.mms.models.Countries;
import com.sbmtech.mms.models.DepartmentMaster;
//import com.sbmtech.mms.models.Floor;
import com.sbmtech.mms.models.FloorMaster;
import com.sbmtech.mms.models.KeyMaster;
import com.sbmtech.mms.models.Otp;
import com.sbmtech.mms.models.Parking;
import com.sbmtech.mms.models.ParkingTypeEnum;
import com.sbmtech.mms.models.ParkingZone;
import com.sbmtech.mms.models.PaymentMethod;
import com.sbmtech.mms.models.ProductConfig;
import com.sbmtech.mms.models.RentCycle;
import com.sbmtech.mms.models.Role;
import com.sbmtech.mms.models.RoleEnum;
import com.sbmtech.mms.models.S3UploadObjTypeEnum;
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
import com.sbmtech.mms.payload.request.BuildingSearchRequest;
import com.sbmtech.mms.payload.request.CommunityRequest;
import com.sbmtech.mms.payload.request.CreateUserRequest;
import com.sbmtech.mms.payload.request.DeleteBedspaceRequest;
import com.sbmtech.mms.payload.request.DeleteBuildingRequest;
import com.sbmtech.mms.payload.request.DeleteUnitRequest;
import com.sbmtech.mms.payload.request.DepartmentRequest;
import com.sbmtech.mms.payload.request.KeyMasterRequest;
import com.sbmtech.mms.payload.request.PaginationRequest;
import com.sbmtech.mms.payload.request.ParkingRequest;
import com.sbmtech.mms.payload.request.ParkingZoneRequest;
import com.sbmtech.mms.payload.request.ResendOtpRequest;
import com.sbmtech.mms.payload.request.ReserveUnitRequest;
import com.sbmtech.mms.payload.request.AreaRequest;
import com.sbmtech.mms.payload.request.BSUnitRequest;
import com.sbmtech.mms.payload.request.BedspaceRequest;
import com.sbmtech.mms.payload.request.SubscriberRequest;
import com.sbmtech.mms.payload.request.SubscriptionPaymentRequest;
import com.sbmtech.mms.payload.request.SubscriptionRequest;
import com.sbmtech.mms.payload.request.TenantIdRequest;
import com.sbmtech.mms.payload.request.TenantSearchRequest;
import com.sbmtech.mms.payload.request.TenantUnitRequest;
import com.sbmtech.mms.payload.request.TenantUpdateRequest;
import com.sbmtech.mms.payload.request.UnitKeysRequest;
import com.sbmtech.mms.payload.request.UnitPaginationRequest;
import com.sbmtech.mms.payload.request.UnitRequest;
import com.sbmtech.mms.payload.request.UnitUpdateRequest;
import com.sbmtech.mms.payload.request.UpdateUserRequest;
import com.sbmtech.mms.payload.request.VerifyOtpRequest;
import com.sbmtech.mms.payload.response.BuildingResponse;
import com.sbmtech.mms.payload.response.CommunityResponse;
import com.sbmtech.mms.payload.response.DeptMasResponse;
import com.sbmtech.mms.payload.response.GenericResponse;
import com.sbmtech.mms.payload.response.KeyResponse;
import com.sbmtech.mms.payload.response.PaginationResponse;
import com.sbmtech.mms.payload.response.ParkingDetailResponse;
import com.sbmtech.mms.payload.response.AreaResponse;
import com.sbmtech.mms.payload.response.BedspaceDetailResponse;
import com.sbmtech.mms.payload.response.BedspaceResponse;
import com.sbmtech.mms.payload.response.ParkingResponse;
import com.sbmtech.mms.payload.response.ParkingZoneResponse;
import com.sbmtech.mms.payload.response.SubscriptionPlans;
import com.sbmtech.mms.payload.response.TenantDetailResponse;
import com.sbmtech.mms.payload.response.TenantUnitResponse;
import com.sbmtech.mms.payload.response.UniKeyResponse;
import com.sbmtech.mms.payload.response.UnitDetailResponse;
import com.sbmtech.mms.payload.response.UnitReserveResponse;
import com.sbmtech.mms.payload.response.UnitResponse;
import com.sbmtech.mms.repository.AreaRepository;
import com.sbmtech.mms.repository.BuildingRepository;
import com.sbmtech.mms.repository.BuildingSpecification;
import com.sbmtech.mms.repository.ChannelMasterRepository;
import com.sbmtech.mms.repository.CityRepository;
import com.sbmtech.mms.repository.CommunityRepository;
import com.sbmtech.mms.repository.CountriesRepository;
import com.sbmtech.mms.repository.DepartmentMasRepository;
//import com.sbmtech.mms.repository.FloorRepository;
import com.sbmtech.mms.repository.FloorMasterRepository;
import com.sbmtech.mms.repository.KeyMasterRepository;
import com.sbmtech.mms.repository.OrderRepository;
import com.sbmtech.mms.repository.OtpRepository;
import com.sbmtech.mms.repository.ParkingRepository;
import com.sbmtech.mms.repository.ParkingZoneRepository;
import com.sbmtech.mms.repository.ProductConfigRepository;
import com.sbmtech.mms.repository.RentCycleRepository;
import com.sbmtech.mms.repository.RoleRepository;
import com.sbmtech.mms.repository.StateRepository;
import com.sbmtech.mms.repository.AreaRepositoryCustom;
import com.sbmtech.mms.repository.BedspaceBathroomTypeRepository;
import com.sbmtech.mms.repository.BedspaceCatRepository;
import com.sbmtech.mms.repository.BedspacePartitionMasterRepository;
import com.sbmtech.mms.repository.BedspaceRepository;
import com.sbmtech.mms.repository.BedspaceSpecifications;
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
import com.sbmtech.mms.service.S3Service;
import com.sbmtech.mms.service.SubscriberService;
import com.sbmtech.mms.util.CommonUtil;
import com.sbmtech.mms.util.DtoConverter;
import com.sbmtech.mms.util.DtoMapperUtil;
import com.sbmtech.mms.util.PaginationUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

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
	private FloorMasterRepository floorMasterRepository;

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
	private RentCycleRepository rentCycleRepository;

	@Autowired
	private UnitReserveDetailsRepository unitReserveDetailsRepository;

	@Autowired
	private ProductConfigRepository productConfigRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BedspacePartitionMasterRepository bedspacePartitionRepository;
	
	@Autowired
	private BedspaceCatRepository bedspaceCatRepository;
	
	@Autowired
	private BedspaceBathroomTypeRepository bedspaceBathroomTypeRepository;
	
	@Autowired
	private BedspaceRepository bedspaceRepository;
	



	@Override
	public Integer getSubscriberIdfromAuth(Authentication auth) throws Exception {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) auth.getPrincipal();
		Optional<User> userOp = userRepository.findByEmail(userPrincipal.getUsername());

		if (userOp.isPresent()) {
			User user = userOp.get();
			if (user.getRoles() != null) {
				for (Role role : user.getRoles()) {
					if (RoleEnum.ROLE_MGT_ADMIN.getName().equals(role.getName().toString())) {
						if (user.getSubscriber() != null) {
							Integer subscriberId = user.getSubscriber().getSubscriberId();
							logger.info("getSubscriberIdfromAuth subscriber->= {}", user.getSubscriber());

							boolean exists = subscriberRepository.existsBySubscriberIdAndActive(subscriberId, 1);
							if (!exists) {
								logger.info("Subscription suspended for subscriberId ->:{} ", subscriberId);
								throw new BusinessException("Subscription suspended", null);
							}

							return subscriberId;
						}
					}else if (RoleEnum.ROLE_BS_MGT_ADMIN.getName().equals(role.getName().toString())) {
						if (user.getSubscriber() != null) {
							Integer subscriberId = user.getSubscriber().getSubscriberId();
							logger.info("getSubscriberIdfromAuth subscriber->= {}", user.getSubscriber());

							boolean exists = subscriberRepository.existsBySubscriberIdAndActive(subscriberId, 1);
							if (!exists) {
								logger.info("Subscription suspended for subscriberId ->:{} ", subscriberId);
								throw new BusinessException("Subscription suspended", null);
							}

							return subscriberId;
						}
					}
				}
			}
		}
		return 0;
	}

	@Override
	public Map<String, Object> createSubscriber(SubscriberRequest request,Integer...defaultRoleId) throws Exception {

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
		
		Integer roleId=2; //by default ROLE_MGT_ADMIN
		if(defaultRoleId!=null && defaultRoleId.length!=0) {
			roleId=defaultRoleId[0]; // whatever roleId we are passing
		}
		Role defaultRole = roleRepository.findById(roleId)
				.orElseThrow(() -> new RuntimeException("Role not found for ID "));
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
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;
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
		try {
			if (request.getCompanyTradeLicenseCopy() != null) {
				if (!ObjectUtils.isEmpty(request.getCompanyTradeLicenseCopy())) {
					String contentType = CommonUtil.validateAttachment(request.getCompanyTradeLicenseCopy());
					String fileExt = contentType.substring(contentType.indexOf("/") + 1);
					s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.TRADE_LIC_PIC, contentType, fileExt,
							Base64.getEncoder().encodeToString(request.getCompanyTradeLicenseCopy()), null);
					s3UploadObjectDtoList.add(s3BuildingLogoDto);

					S3UploadDto s3UploadDto = new S3UploadDto();
					s3UploadDto.setSubscriberId(request.getSubscriberId());
					s3UploadDto.setObjectType(S3UploadObjTypeEnum.COMPANYTRADELICENSE.toString());
					s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
					List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
					for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
						if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
							if (s3UploadObjectDto.getObjectName().equals(CommonConstants.TRADE_LIC_PIC)) {
								subscriber.setCompanyTradeLicenseCopyFilename(s3UploadObjectDto.getS3FileName());
							}

						}
					}

				}
			}
			if (request.getCompanyLogo() != null) {
				if (!ObjectUtils.isEmpty(request.getCompanyLogo())) {
					String contentType = CommonUtil.validateAttachment(request.getCompanyLogo());
					String fileExt = contentType.substring(contentType.indexOf("/") + 1);
					s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.COMPANY_LOGO_PIC, contentType, fileExt,
							Base64.getEncoder().encodeToString(request.getCompanyLogo()), null);
					s3UploadObjectDtoList.add(s3BuildingLogoDto);

					S3UploadDto s3UploadDto = new S3UploadDto();
					s3UploadDto.setSubscriberId(request.getSubscriberId());
					s3UploadDto.setObjectType(S3UploadObjTypeEnum.COMPANYLOGO.toString());
					s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
					List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
					for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
						if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
							if (s3UploadObjectDto.getObjectName().equals(CommonConstants.COMPANY_LOGO_PIC)) {
								subscriber.setCompanyLogoFilename(s3UploadObjectDto.getS3FileName());
							}

						}
					}

				}
			}
		} catch (Exception e) {
			logger.error("image upload failed:"+e.getMessage());
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

	public ApiResponse<List<SubscriptionPlans>> getAllSubscriptionPlans(String planCategory) {
		List<SubscriptionPlanMaster> plansList = subscriptionPlanRepository.findAll();
		List<SubscriptionPlans> result = plansList.stream().filter(e -> e.getActive() == 1 && e.getPlanCategory().equals(planCategory))
				.map(e -> new SubscriptionPlans(e.getPlanId(), e.getPlanName(), e.getPriceMonth(), e.getPriceYear(),
						e.getCurrency(), e.getDurationInDays(), e.getTrialDays(), e.getDescription(), e.getFeatures(),
						e.getMetadata(),e.getPlanCategory()))
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

	public ApiResponse<Object> addArea(Integer subscriberId, AreaRequest request) {
		Optional<Subscriber> subscriberOpt = subscriberRepository.findById(subscriberId);

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

	public ApiResponse<Object> addCommunity(Integer subscriberId, CommunityRequest request) {

		Area location = areaRepoCustom.findByAreaIdAndSubscriberId(request.getLocationId(), subscriberId);
		if (ObjectUtils.isEmpty(location)) {
			throw new BusinessException("Area not found with id: " + request.getLocationId(), null);
		}

		Optional<Subscriber> subscriberOptional = subscriberRepository.findById(subscriberId);

		Subscriber subscriber = subscriberOptional.get();

		Community community = new Community();
		community.setCommunityName(request.getCommunityName());
		// community.setLocation(location);
		community.setSubscriber(subscriber);

		communityRepository.save(community);

		CommunityResponse commuResp = new CommunityResponse();
		BeanUtils.copyProperties(community, commuResp);
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, commuResp, null, subscriberId);
	}

	public ApiResponse<Object> addBuilding(BuildingRequest request) {

		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;

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

		if (!validSubscription(request)) {
			throw new BusinessException("Can not create build", null);
		}

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

			// building.setBuildingLogo(request.getBuildingLogo());

			if (!ObjectUtils.isEmpty(request.getBuildingLogo())) {
				String contentType = CommonUtil.validateAttachment(request.getBuildingLogo());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.BUILD_MAIN_PIC, contentType, fileExt,
						Base64.getEncoder().encodeToString(request.getBuildingLogo()), null);
				s3UploadObjectDtoList.add(s3BuildingLogoDto);
			}

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

			S3UploadDto s3UploadDto = new S3UploadDto();
			s3UploadDto.setSubscriberId(request.getSubscriberId());
			s3UploadDto.setBuildingId(building.getBuildingId());
			s3UploadDto.setObjectType(S3UploadObjTypeEnum.BUILDING.toString());
			s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
			List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
			for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
				if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
					if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BUILD_MAIN_PIC)) {
						building.setBuildingLogoFileName(s3UploadObjectDto.getS3FileName());
					}

				}
			}

			buildingResp = new BuildingResponse();
			BeanUtils.copyProperties(building, buildingResp);
		} catch (Exception e) {
			throw new BusinessException("addBuilding failed "+e.getMessage(), e);
		}

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, buildingResp, null, request.getSubscriberId());
	}

	private boolean validSubscription(BuildingRequest request) {
		boolean validSubscrption = true;
		Integer subscriberId = request.getSubscriberId();
		Long totalBuildings = getSubscriberTotalActiveBuilding(subscriberId);
		Long totalUnits = getSubscriberTotalActiveUnits(subscriberId);
		// Integer totalAdminUsers=getSubscriberTotalActiveAdminUsers(subscriberId);
		Long totalTenants = getSubscriberTotalActiveTenants(subscriberId);

		Subscriptions subscription = this.getActiveSubscriptionDetails(subscriberId);
		if (subscription != null) {
			Optional<SubscriptionPlanMaster> planMasterop = planMasterRepository
					.findById(subscription.getPlan().getPlanId());
			if (planMasterop.isPresent()) {
				SubscriptionPlanMaster planMaster = planMasterop.get();
				Map<String, Object> planMetaData = planMaster.getMetadata();

				Long maxNoBuilding = Long.valueOf((String) planMetaData.get("maxNoBuilding"));
				Long maxNoUnits = Long.valueOf((String) planMetaData.get("maxNoUnits"));
				Long maxNoTenantUser = Long.valueOf((String) planMetaData.get("maxNoTenantUser"));
				if (totalBuildings > maxNoBuilding) {
					throw new BusinessException(
							"No of building exceeded for subscriberId: " + subscriberId + " Choose other plan", null);
				}
				if (totalUnits > maxNoUnits) {
					throw new BusinessException(
							"No of units exceeded for subscriberId: " + subscriberId + " Choose other plan", null);
				}
				if (totalTenants > maxNoTenantUser) {
					throw new BusinessException(
							"No of tenant user exceeded for subscriberId: " + subscriberId + " Choose other plan",
							null);
				}
			}
		}

		return validSubscrption;
	}

	private Long getSubscriberTotalActiveTenants(Integer subscriberId) {

		return subscriberRepository.countActiveTenantUnitsBySubscriberId(subscriberId);
	}

	private Long getSubscriberTotalActiveBuilding(Integer subscriberId) {
		return subscriberRepository.countActiveBuildingsBySubscriberId(subscriberId);
	}

	private Long getSubscriberTotalActiveUnits(Integer subscriberId) {
		return subscriberRepository.countActiveUnitsBySubscriberId(subscriberId);
	}

	public ApiResponse<Object> addUnit(UnitRequest request) throws Exception {
		S3UploadObjectDto s3MainPicDto = null;
		S3UploadObjectDto s3Pic2Dto = null;
		S3UploadObjectDto s3Pic3Dto = null;
		S3UploadObjectDto s3Pic4Dto = null;
		S3UploadObjectDto s3Pic5Dto = null;
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
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

		Building building = buildingRepository.findByBuildingIdAndSubscriberId(request.getBuildingId(),request.getSubscriberId());
		if (building==null) {
			throw new BusinessException("Building not found with id: " + request.getBuildingId(), null);
		}
		//Building building = buildingOptional.get();

		Optional<UnitStatus> unitStatusTypeOp = unitStatusRepository.findById(1);

		if (!unitStatusTypeOp.isPresent()) {
			throw new BusinessException("UnitStatus not found", null);
		}

//		Optional<Floor> floorOptional = floorRepository.findById(request.getFloorId());
//		if (!floorOptional.isPresent()) {
//			throw new BusinessException("Floor not found with id: " + request.getFloorId(), null);
//		}
		
		Subscriber subscriber = subscriberRepository.findById(request.getSubscriberId()).orElse(null);

		Optional<FloorMaster> floorOptional = floorMasterRepository.findByFloorName(request.getFloorName());
		if (!floorOptional.isPresent()) {
			throw new BusinessException("FloorName not found: " + request.getFloorName(), null);
		}
		FloorMaster floor = floorOptional.get();

		validateNoOfFloorAndUnitsConfig(building);

		if (!ObjectUtils.isEmpty(request.getUnitMainPic1())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitMainPic1());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3MainPicDto = new S3UploadObjectDto(CommonConstants.UNIT_MAIN_PIC, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitMainPic1()), null);
			s3UploadObjectDtoList.add(s3MainPicDto);
		}
		if (!ObjectUtils.isEmpty(request.getUnitPic2())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitPic2());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3Pic2Dto = new S3UploadObjectDto(CommonConstants.UNIT_PIC2, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitPic2()), null);
			s3UploadObjectDtoList.add(s3Pic2Dto);
		}
		if (!ObjectUtils.isEmpty(request.getUnitPic3())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitPic3());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3Pic3Dto = new S3UploadObjectDto(CommonConstants.UNIT_PIC3, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitPic3()), null);
			s3UploadObjectDtoList.add(s3Pic3Dto);
		}
		if (!ObjectUtils.isEmpty(request.getUnitPic4())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitPic4());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3Pic4Dto = new S3UploadObjectDto(CommonConstants.UNIT_PIC4, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitPic4()), null);
			s3UploadObjectDtoList.add(s3Pic4Dto);
		}
		if (!ObjectUtils.isEmpty(request.getUnitPic5())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitPic5());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3Pic5Dto = new S3UploadObjectDto(CommonConstants.UNIT_PIC5, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitPic5()), null);
			s3UploadObjectDtoList.add(s3Pic5Dto);
		}

		Unit unit = new Unit();
		unit.setBuilding(building);
		// unit.setFloor(floor);
		unit.setFloor(floor);
		unit.setUnitName(request.getUnitName());
		unit.setUnitType(unitType);
		unit.setUnitSubType(unitSubTypeOptional.get());
		unit.setSize(request.getSize());
		unit.setHasBalcony(request.getHasBalcony());
		unit.setUnitStatus(unitStatusTypeOp.get());

		unit.setRentMonth(request.getRentMonth());
		unit.setRentYear(request.getRentYear());
		unit.setEbConnNo(request.getEbConnNo());
		unit.setWaterConnNo(request.getWaterConnNo());
		unit.setSecurityDeposit(request.getSecurityDeposit());
		unit.setSubscriber(subscriber);
		unitRepository.save(unit);

		S3UploadDto s3UploadDto = new S3UploadDto();
		s3UploadDto.setSubscriberId(request.getSubscriberId());
		s3UploadDto.setBuildingId(request.getBuildingId());
		s3UploadDto.setUnitId(unit.getUnitId());
		s3UploadDto.setObjectType(S3UploadObjTypeEnum.UNIT.toString());
		s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);

		List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
		for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
			if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_MAIN_PIC)) {
					unit.setUnitMainPic1Name(s3UploadObjectDto.getS3FileName());
				}
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_PIC2)) {
					unit.setUnitPic2Name(s3UploadObjectDto.getS3FileName());
				}
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_PIC3)) {
					unit.setUnitPic3Name(s3UploadObjectDto.getS3FileName());
				}
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_PIC4)) {
					unit.setUnitPic4Name(s3UploadObjectDto.getS3FileName());
				}
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_PIC5)) {
					unit.setUnitPic5Name(s3UploadObjectDto.getS3FileName());
				}
			}
		}

		UnitResponse unitResp = new UnitResponse();
		BeanUtils.copyProperties(unit, unitResp);
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, unitResp, null, null);
	}
	
	
	public ApiResponse<Object> addUnit(BSUnitRequest request) throws Exception {
		S3UploadObjectDto s3MainPicDto = null;
		S3UploadObjectDto s3Pic2Dto = null;
		S3UploadObjectDto s3Pic3Dto = null;
		S3UploadObjectDto s3Pic4Dto = null;
		S3UploadObjectDto s3Pic5Dto = null;
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
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

		Building building = buildingRepository.findByBuildingIdAndSubscriberId(request.getBuildingId(),request.getSubscriberId());
		if (building==null) {
			throw new BusinessException("Building not found with id: " + request.getBuildingId(), null);
		}
		//Building building = buildingOptional.get();

		Optional<UnitStatus> unitStatusTypeOp = unitStatusRepository.findById(1);

		if (!unitStatusTypeOp.isPresent()) {
			throw new BusinessException("UnitStatus not found", null);
		}

//		Optional<Floor> floorOptional = floorRepository.findById(request.getFloorId());
//		if (!floorOptional.isPresent()) {
//			throw new BusinessException("Floor not found with id: " + request.getFloorId(), null);
//		}
		
		Subscriber subscriber = subscriberRepository.findById(request.getSubscriberId()).orElse(null);

		Optional<FloorMaster> floorOptional = floorMasterRepository.findByFloorName(request.getFloorName());
		if (!floorOptional.isPresent()) {
			throw new BusinessException("FloorName not found: " + request.getFloorName(), null);
		}
		FloorMaster floor = floorOptional.get();

		validateNoOfFloorAndUnitsConfig(building);

		if (!ObjectUtils.isEmpty(request.getUnitMainPic1())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitMainPic1());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3MainPicDto = new S3UploadObjectDto(CommonConstants.UNIT_MAIN_PIC, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitMainPic1()), null);
			s3UploadObjectDtoList.add(s3MainPicDto);
		}
		if (!ObjectUtils.isEmpty(request.getUnitPic2())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitPic2());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3Pic2Dto = new S3UploadObjectDto(CommonConstants.UNIT_PIC2, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitPic2()), null);
			s3UploadObjectDtoList.add(s3Pic2Dto);
		}
		if (!ObjectUtils.isEmpty(request.getUnitPic3())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitPic3());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3Pic3Dto = new S3UploadObjectDto(CommonConstants.UNIT_PIC3, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitPic3()), null);
			s3UploadObjectDtoList.add(s3Pic3Dto);
		}
		if (!ObjectUtils.isEmpty(request.getUnitPic4())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitPic4());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3Pic4Dto = new S3UploadObjectDto(CommonConstants.UNIT_PIC4, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitPic4()), null);
			s3UploadObjectDtoList.add(s3Pic4Dto);
		}
		if (!ObjectUtils.isEmpty(request.getUnitPic5())) {
			String contentType = CommonUtil.validateAttachment(request.getUnitPic5());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3Pic5Dto = new S3UploadObjectDto(CommonConstants.UNIT_PIC5, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getUnitPic5()), null);
			s3UploadObjectDtoList.add(s3Pic5Dto);
		}

		Unit unit = new Unit();
		unit.setBuilding(building);
		// unit.setFloor(floor);
		unit.setFloor(floor);
		unit.setUnitName(request.getUnitName());
		unit.setUnitType(unitType);
		unit.setUnitSubType(unitSubTypeOptional.get());
		unit.setSize(request.getSize());
		unit.setHasBalcony(request.getHasBalcony());
		unit.setUnitStatus(unitStatusTypeOp.get());

		//unit.setRentMonth(request.getRentMonth());
		//unit.setRentYear(request.getRentYear());
		unit.setEbConnNo(request.getEbConnNo());
		unit.setWaterConnNo(request.getWaterConnNo());
		//unit.setSecurityDeposit(request.getSecurityDeposit());
		unit.setSubscriber(subscriber);
		unitRepository.save(unit);

		S3UploadDto s3UploadDto = new S3UploadDto();
		s3UploadDto.setSubscriberId(request.getSubscriberId());
		s3UploadDto.setBuildingId(request.getBuildingId());
		s3UploadDto.setUnitId(unit.getUnitId());
		s3UploadDto.setObjectType(S3UploadObjTypeEnum.UNIT.toString());
		s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);

		List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
		for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
			if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_MAIN_PIC)) {
					unit.setUnitMainPic1Name(s3UploadObjectDto.getS3FileName());
				}
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_PIC2)) {
					unit.setUnitPic2Name(s3UploadObjectDto.getS3FileName());
				}
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_PIC3)) {
					unit.setUnitPic3Name(s3UploadObjectDto.getS3FileName());
				}
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_PIC4)) {
					unit.setUnitPic4Name(s3UploadObjectDto.getS3FileName());
				}
				if (s3UploadObjectDto.getObjectName().equals(CommonConstants.UNIT_PIC5)) {
					unit.setUnitPic5Name(s3UploadObjectDto.getS3FileName());
				}
			}
		}

		UnitResponse unitResp = new UnitResponse();
		BeanUtils.copyProperties(unit, unitResp);
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, unitResp, null, null);
	}


	private void validateNoOfFloorAndUnitsConfig(Building building) {
		// Long
		// currentFloorCount=subscriberRepository.countDistinctFloorsByBuildingId(building.getBuildingId());
		Long currentUnitsCount = subscriberRepository.countUnitsByBuildingId(building.getBuildingId());
		// Integer noOfFloorsBuildingConfig=building.getNoOfFloors();
		Integer noOfUnitsBuildingConfig = building.getNoOfUnits();
//		if(currentFloorCount>noOfFloorsBuildingConfig) {
//			throw new BusinessException("No of floor exceeded for buildingId: " + building.getBuildingId(), null);
//		}

		if (noOfUnitsBuildingConfig !=null && currentUnitsCount!=0 && currentUnitsCount > noOfUnitsBuildingConfig) {
			throw new BusinessException("No of units exceeded for buildingId: " + building.getBuildingId(), null);
		}

	}

	@SuppressWarnings("unchecked")
	public ApiResponse<Object> createTenant(CreateUserRequest request) throws Exception {
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;
		User existingUser = null;
		User user = null;
		boolean existingTenant = false;
		@SuppressWarnings("unused")
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
		tenant.setPassportNo(request.getPassportNo());
		tenant.setPassportExpiryDate(CommonUtil.getDatefromString(request.getPassportExpiryDate(), DATE_ddMMyyyy));
		tenant.setNationality(nationality);
		tenant.setSubscriber(subscriber);

		if (!ObjectUtils.isEmpty(request.getEidaCopy())) {
			String contentType = CommonUtil.validateAttachment(request.getEidaCopy());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.EID_PIC, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getEidaCopy()), null);
			s3UploadObjectDtoList.add(s3BuildingLogoDto);

			S3UploadDto s3UploadDto = new S3UploadDto();
			s3UploadDto.setSubscriberId(request.getSubscriberId());
			s3UploadDto.setObjectType(S3UploadObjTypeEnum.EID.toString());
			s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
			List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
			s3UploadObjectDtoList.clear();
			for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
				if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
					if (s3UploadObjectDto.getObjectName().equals(CommonConstants.EID_PIC)) {
						tenant.setEidaCopyFilename(s3UploadObjectDto.getS3FileName());
					}

				}
			}
		}
		if (!ObjectUtils.isEmpty(request.getPassportCopy())) {
			String contentType = CommonUtil.validateAttachment(request.getPassportCopy());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.PASSPORT_PIC, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getPassportCopy()), null);
			s3UploadObjectDtoList.add(s3BuildingLogoDto);

			S3UploadDto s3UploadDto = new S3UploadDto();
			s3UploadDto.setSubscriberId(request.getSubscriberId());
			s3UploadDto.setObjectType(S3UploadObjTypeEnum.PASSPORT.toString());
			s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
			List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
			s3UploadObjectDtoList.clear();
			for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
				if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
					if (s3UploadObjectDto.getObjectName().equals(CommonConstants.PASSPORT_PIC)) {
						tenant.setPassportCopyFilename(s3UploadObjectDto.getS3FileName());
					}

				}
			}
		}

		if (!ObjectUtils.isEmpty(request.getPhoto())) {
			String contentType = CommonUtil.validateAttachment(request.getPhoto());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.PHOTO_PIC, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getPhoto()), null);
			s3UploadObjectDtoList.add(s3BuildingLogoDto);

			S3UploadDto s3UploadDto = new S3UploadDto();
			s3UploadDto.setSubscriberId(request.getSubscriberId());
			s3UploadDto.setObjectType(S3UploadObjTypeEnum.PHOTO.toString());
			s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
			List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
			s3UploadObjectDtoList.clear();
			for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
				if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
					if (s3UploadObjectDto.getObjectName().equals(CommonConstants.PHOTO_PIC)) {
						tenant.setPhotoFilename(s3UploadObjectDto.getS3FileName());
					}

				}
			}
		}

		tenant.setNationality(nationality);
		tenant.setTenantStatus(CommonConstants.TENANT_NEW);
		tenant = tenantRepository.save(tenant);
		String pwd = CommonUtil.generateRandomPwd();
		pwd = "123456";// remove this
		
		if (existingUser != null) {
			

			existingUser.setMobileNo(Long.valueOf(request.getMobileNo()));
			existingUser.setActive(true);
			existingUser.setEmiratesId(CommonUtil.getLongValofObject(request.getEmiratesId()));
			existingUser.setDob(CommonUtil.getDatefromString(request.getDob(), DATE_ddMMyyyy));
			existingUser.setGender(request.getGender());
			existingUser.setAddress(request.getAddress());
			if (request.getEidaCopy() != null && request.getEidaCopy().length > 1
					&& StringUtils.isNoneBlank(tenant.getEidaCopyFilename())) {
				// existingUser.setEidaCopy(request.getEidaCopy());
				existingUser.setEidaCopyFilename(tenant.getEidaCopyFilename());
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
			// user.setEidaCopy(request.getEidaCopy());
			if (StringUtils.isNoneBlank(tenant.getEidaCopyFilename())) {
				user.setEidaCopyFilename(tenant.getEidaCopyFilename());
			}
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
			@SuppressWarnings("rawtypes")
			GenericResponse gr = new GenericResponse();
			gr.setId(tenant.getTenantId());
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, gr, null, null);
		} else {
			throw new BusinessException("Tenant Not created", null);
		}

	}
	
	@Override
	public ApiResponse<Object> bsUpdateTenant(UpdateUserRequest request) throws Exception {
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;
		User existingUser = null;
		NotificationEmailResponseDTO resp = null;
		boolean existingTenant = false;
		Tenant existingTenantDb=null;
		User user = null;
		Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
		if (userOptional.isPresent()) {
			Countries nationality = countriesRepository.findById(request.getNationalityId()).orElse(null);
			Subscriber subscriber = subscriberRepository.findById(request.getSubscriberId()).orElse(null);

			existingUser = userOptional.get();
			Set<Role> exisingRoles = existingUser.getRoles();
			for (Role r : exisingRoles) {
				if (r.getRoleId() == RoleEnum.ROLE_TENANT.getValue()) {
					existingTenant = true;
				} 
			}

			if (!existingTenant) {
				throw new BusinessException("No Tenant found", null);
			}
			
			existingTenantDb=tenantRepository.findByTenantEmailAndSubscriberId(request.getEmail(),request.getSubscriberId());
			existingTenantDb.setFirstName(request.getFirstName());
			existingTenantDb.setLastName(request.getLastName());
		//	existingTenantDb.setEmail(request.getEmail());
			//existingTenantDb.setPhoneNumber(request.getPhoneNumber());
			existingTenantDb.setDateOfBirth(CommonUtil.getDatefromString(request.getDob(), DATE_ddMMyyyy));
			existingTenantDb.setEmiratesId(CommonUtil.getLongValofObject(request.getEmiratesId()));
			existingTenantDb.setEidaExpiryDate(CommonUtil.getDatefromString(request.getEidaExpiryDate(), DATE_ddMMyyyy));
			existingTenantDb.setPassportNo(request.getPassportNo());
			existingTenantDb.setPassportExpiryDate(CommonUtil.getDatefromString(request.getPassportExpiryDate(), DATE_ddMMyyyy));
			existingTenantDb.setNationality(nationality);
			existingTenantDb.setSubscriber(subscriber);
			
			if (!ObjectUtils.isEmpty(request.getEidaCopy())) {
				String contentType = CommonUtil.validateAttachment(request.getEidaCopy());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.EID_PIC, contentType, fileExt,
						Base64.getEncoder().encodeToString(request.getEidaCopy()), null);
				s3UploadObjectDtoList.add(s3BuildingLogoDto);

				S3UploadDto s3UploadDto = new S3UploadDto();
				s3UploadDto.setSubscriberId(request.getSubscriberId());
				s3UploadDto.setObjectType(S3UploadObjTypeEnum.EID.toString());
				s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
				List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
				s3UploadObjectDtoList.clear();
				for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
					if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.EID_PIC)) {
							existingTenantDb.setEidaCopyFilename(s3UploadObjectDto.getS3FileName());
						}

					}
				}
			}
			if (!ObjectUtils.isEmpty(request.getPassportCopy())) {
				String contentType = CommonUtil.validateAttachment(request.getPassportCopy());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.PASSPORT_PIC, contentType, fileExt,
						Base64.getEncoder().encodeToString(request.getPassportCopy()), null);
				s3UploadObjectDtoList.add(s3BuildingLogoDto);

				S3UploadDto s3UploadDto = new S3UploadDto();
				s3UploadDto.setSubscriberId(request.getSubscriberId());
				s3UploadDto.setObjectType(S3UploadObjTypeEnum.PASSPORT.toString());
				s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
				List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
				s3UploadObjectDtoList.clear();
				for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
					if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.PASSPORT_PIC)) {
							existingTenantDb.setPassportCopyFilename(s3UploadObjectDto.getS3FileName());
						}

					}
				}
			}

			if (!ObjectUtils.isEmpty(request.getPhoto())) {
				String contentType = CommonUtil.validateAttachment(request.getPhoto());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.PHOTO_PIC, contentType, fileExt,
						Base64.getEncoder().encodeToString(request.getPhoto()), null);
				s3UploadObjectDtoList.add(s3BuildingLogoDto);

				S3UploadDto s3UploadDto = new S3UploadDto();
				s3UploadDto.setSubscriberId(request.getSubscriberId());
				s3UploadDto.setObjectType(S3UploadObjTypeEnum.PHOTO.toString());
				s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
				List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
				s3UploadObjectDtoList.clear();
				for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
					if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.PHOTO_PIC)) {
							existingTenantDb.setPhotoFilename(s3UploadObjectDto.getS3FileName());
						}

					}
				}
			}
			existingTenantDb = tenantRepository.save(existingTenantDb);
			
			System.out.println("existingTenantDb="+existingTenantDb);
			
			existingUser.setMobileNo(Long.valueOf(request.getMobileNo()));
			existingUser.setActive(true);
			existingUser.setEmiratesId(CommonUtil.getLongValofObject(request.getEmiratesId()));
			existingUser.setDob(CommonUtil.getDatefromString(request.getDob(), DATE_ddMMyyyy));
			existingUser.setGender(request.getGender());
			existingUser.setAddress(request.getAddress());
			if (request.getEidaCopy() != null && request.getEidaCopy().length > 1
					&& StringUtils.isNoneBlank(existingTenantDb.getEidaCopyFilename())) {
				// existingUser.setEidaCopy(request.getEidaCopy());
				existingUser.setEidaCopyFilename(existingTenantDb.getEidaCopyFilename());
			}
			existingUser.setNationality(nationality);

			// user.setUserType(userType);
			existingUser.setSubscriber(subscriber);
			existingUser.setUpdatedDate(new Date());
			
			//Set<Role> exisingRoles = existingUser.getRoles();
			//exisingRoles.add(role);
			//existingUser.setTenantId(tenant.getTenantId());
			user = userRepository.save(existingUser);
			
			NotifEmailDTO dto = new NotifEmailDTO();
			dto.setEmailTo(request.getEmail());
			dto.setCustomerName(request.getFirstName());
		//	dto.setPwd(pwd);
			resp = notificationService.sendTenentDetailsUpdate(dto);

		}
		if (resp != null && resp.isEmailSent()) {
			@SuppressWarnings("rawtypes")
			GenericResponse gr = new GenericResponse();
			gr.setId(existingTenantDb.getTenantId());
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, gr, null, null);
		} else {
			throw new BusinessException("Tenant Not updated", null);
		}
		
	}
	
	public ApiResponse<Object> reserveUnit(Integer subscriberId, ReserveUnitRequest request)throws Exception {
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;
		User user = null;
		
		Role role = roleRepository.findById(RoleEnum.ROLE_TENANT.getValue()).orElse(null);
		if (role == null) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, RoleEnum.ROLE_TENANT.getName() + " Role not found",
					null, null);
		}
		
		
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
		Double unitReserveAmount = (Double) unitReserve.get("unitReserveAmount");

		Integer unitReserveDays = (unitReserve.get("unitReserveDays") != null)
				? (Integer) unitReserve.get("unitReserveDays")
				: null;

		if (unitReserveDays == null) {
			throw new BusinessException("unitReserveDays not found in product config", null);
		}

		Countries nationality = countriesRepository.findById(request.getNationalityId()).orElseThrow(() -> new BusinessException("Nationality not found with ID: " + request.getNationalityId(),null));
		Subscriber subscriber = subscriberRepository.findById(subscriberId).orElseThrow(() -> new BusinessException("Subscriber not found with ID: " ,null));
		//findReservationsForDate
		
		List <UnitReserveDetails>  activeReserveList = unitReserveDetailsRepository.findReservationsForDate(request.getUnitId(), new Date());
		if(activeReserveList==null || activeReserveList.size()!=0) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,"Currently this unit is Reserved status",null, null);
		}
		
		Tenant tenant = new Tenant();
		tenant.setFirstName(request.getFirstName());
		//tenant.setLastName(request.getLastName());
		tenant.setEmail(request.getEmail());
		tenant.setPhoneNumber(request.getMobileNo());
		tenant.setDateOfBirth(CommonUtil.getDatefromString(request.getDob(), DATE_ddMMyyyy));
		tenant.setEmiratesId(CommonUtil.getLongValofObject(request.getEmiratesId()));
		//tenant.setEidaExpiryDate(CommonUtil.getDatefromString(request.getEidaExpiryDate(), DATE_ddMMyyyy));
		//tenant.setPassportNo(request.getPassportNo());
		//tenant.setPassportExpiryDate(CommonUtil.getDatefromString(request.getPassportExpiryDate(), DATE_ddMMyyyy));
		tenant.setNationality(nationality);
		tenant.setSubscriber(subscriber);
		tenant.setTenantStatus(CommonConstants.TENANT_ACTIVE);		
		if (!ObjectUtils.isEmpty(request.getEidaCopy())) {
			String contentType = CommonUtil.validateAttachment(request.getEidaCopy());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.EID_PIC, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getEidaCopy()), null);
			s3UploadObjectDtoList.add(s3BuildingLogoDto);

			S3UploadDto s3UploadDto = new S3UploadDto();
			s3UploadDto.setSubscriberId(request.getSubscriberId());
			s3UploadDto.setObjectType(S3UploadObjTypeEnum.EID.toString());
			s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
			List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
			s3UploadObjectDtoList.clear();
			for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
				if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
					if (s3UploadObjectDto.getObjectName().equals(CommonConstants.EID_PIC)) {
						tenant.setEidaCopyFilename(s3UploadObjectDto.getS3FileName());
					}

				}
			}
		}
		
		String pwd = CommonUtil.generateRandomPwd();
		pwd = "123456";// remove this
		
		
		Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
		if (existingUser.isPresent()) {
			logger.info("reserveUnit existing user=" + request.getEmail());
			user = existingUser.get();
			if (StringUtils.isNoneBlank(tenant.getEidaCopyFilename())) {
				user.setEidaCopyFilename(tenant.getEidaCopyFilename());
			}
		} else {
			logger.info("reserveUnit new user=" + request.getEmail());
			user = new User();
			user.setEmail(request.getEmail());
			user.setMobileNo(Long.valueOf(request.getMobileNo()));
			user.setPassword(encoder.encode(pwd));
			user.setActive(false);  // Active only if he become real tenant
			user.setEmiratesId(CommonUtil.getLongValofObject(request.getEmiratesId()));
			user.setDob(CommonUtil.getDatefromString(request.getDob(), DATE_ddMMyyyy));
			user.setGender(request.getGender());
			user.setAddress(request.getAddress());
			// user.setEidaCopy(request.getEidaCopy());
			if (StringUtils.isNoneBlank(tenant.getEidaCopyFilename())) {
				user.setEidaCopyFilename(tenant.getEidaCopyFilename());
			}
			user.setNationality(nationality);
			// user.setUserType(userType);
			user.setSubscriber(subscriber);
			user.setCreatedDate(new Date());
			
			Set<Role> roles = new HashSet<>();
			roles.add(role);
			user.setRoles(roles);

			user.setTenantId(tenant.getTenantId());
			user = userRepository.save(user);
			//userRepository.save(newUser);
		}
		

//		NotifEmailDTO dto = new NotifEmailDTO();
//		dto.setEmailTo(request.getEmail());
//		dto.setCustomerName(request.getFirstName());
//		dto.setPwd(pwd);
//		resp = notificationService.sendTenentAccountCreationEmail(dto);
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, unitReserveDays);
		Date reserveEndDate = calendar.getTime();
		
		if (unitReservePaymentOption) {
			// create Order todo
			UnitReserveDetails reserveDetails = new UnitReserveDetails();
			reserveDetails.setUnit(unit);
			reserveDetails.setUser(user);
			reserveDetails.setReserveStartDate(new Date());
			reserveDetails.setReserveEndDate(reserveEndDate);
			reserveDetails.setPaymentRequired((unitReservePaymentOption) ? 1 : 0);
			reserveDetails.setUnitReserveStatus(CommonConstants.UNIT_RESERVE_PAYMNET_PENDING);
			reserveDetails.setUnitReserveAmount(unitReserveAmount);
			UnitReserveDetails reserveDet = unitReserveDetailsRepository.save(reserveDetails);
			
			if (reserveDet != null && reserveDet.getUnitReserveId() != null) {
				unit.setUnitStatus(unitStatusRepository.findByUnitStatusName(UnitStatusEnum.RESERVED.toString()));
				unitRepository.save(unit);
				UnitReserveResponse gr = new UnitReserveResponse();
				gr.setUnitReserveId(reserveDetails.getUnitReserveId());
				gr.setMsg("Please proceed to payment");
				return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, gr, null, null);
			}
			

		} else {
			unit.setUnitStatus(unitStatusRepository.findByUnitStatusName(UnitStatusEnum.RESERVED.toString()));
			unitRepository.save(unit);

			UnitReserveDetails reserveDetails = new UnitReserveDetails();
			reserveDetails.setUnit(unit);
			reserveDetails.setUser(user);
			reserveDetails.setReserveStartDate(new Date());
			reserveDetails.setReserveEndDate(reserveEndDate);
			reserveDetails.setPaymentRequired((unitReservePaymentOption) ? 1 : 0);
			reserveDetails.setUnitReserveStatus(CommonConstants.UNIT_RESERVE_RESERVED);
			UnitReserveDetails reserveDet = unitReserveDetailsRepository.save(reserveDetails);
			if (reserveDet != null && reserveDet.getUnitReserveId() != null) {
				NotifEmailDTO dto = new NotifEmailDTO();
				dto.setEmailTo(request.getEmail());
				dto.setCustomerName(request.getEmail());
				dto.setBuildingId(CommonUtil.getStringValofObject(unit.getBuilding().getBuildingId()));
				dto.setBuildingName(unit.getBuilding().getBuildingName());
				dto.setUnitId(CommonUtil.getStringValofObject(unit.getUnitId()));
				dto.setUnitName(unit.getUnitName());
				dto.setReserveFromDate(CommonUtil.getStringDatefromDate(new Date()));
				dto.setReserveToDate(CommonUtil.getStringDatefromDate(reserveEndDate));
				;

				NotificationEmailResponseDTO notifResp = notificationService.sendUnitReservationEmail(dto);
				if (notifResp != null && notifResp.isEmailSent()) {
					logger.info("reserveUnit email successfully sent to user{}, reserveDet={}" + user.getEmail(),
							reserveDet);
					UnitReserveResponse gr = new UnitReserveResponse();
					gr.setUnitReserveId(reserveDetails.getUnitReserveId());
					gr.setMsg("Unit successfully reserved");
					return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, gr, null, null);
				}
			}
		}
		
		
		return null;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ApiResponse<Object> createParkingZone(ParkingZoneRequest request) {

		Optional<Building> buildingOptional = buildingRepository.findById(request.getBuildingId());
		if (!buildingOptional.isPresent()) {
			throw new BusinessException("Building not found with id: " + request.getBuildingId(), null);
		}
		Building building = buildingRepository.findByBuildingIdAndSubscriberId(request.getBuildingId(),
				request.getSubscriberId());
		if (ObjectUtils.isEmpty(building)) {
			throw new BusinessException("Building not found with id: " + request.getBuildingId(), null);
		}

		ParkingZone parkingZone = new ParkingZone();
		parkingZone.setParkZoneName(request.getParkZoneName());
		parkingZone.setSubscriber(building.getSubscriber());
		parkingZone.setBuilding(building);

		parkingZoneRepository.save(parkingZone);

		GenericResponse gr = new GenericResponse();
		gr.setId(parkingZone.getParkZoneId());
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, gr, null, null);
	}

	public ApiResponse<Object> createParking(ParkingRequest request) {

		Building building = buildingRepository.findByBuildingIdAndSubscriberId(request.getBuildingId(),
				request.getSubscriberId());
		if (ObjectUtils.isEmpty(building)) {
			throw new BusinessException("BuildingId not found with this subscriber", null);
		}

		ParkingZone parkZone = null;
		if (request.getParkZoneId() != null) {
			parkZone = parkingZoneRepository.findByParkZoneIdAndSubscriberId(request.getParkZoneId(),
					request.getSubscriberId());
			if (ObjectUtils.isEmpty(parkZone)) {
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "Parking Zone not found with this subscriber",
						null, null);
			}
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
		parking.setIsAvailable(true); // by default it is available
		parking.setBuilding(building);
		parking.setCreatedTime(new Date());

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
		Optional <Subscriber> subscriber= subscriberRepository.findById(request.getSubscriberId());
		logger.info("Starting process to add tenant unit for tenantId: {}, unitId: {}", request.getTenantId(),
				request.getUnitId());

		TenantUnit tenantUnit = new TenantUnit();
		Tenant tenant = tenantRepository.findByTenantIdAndSubscriberId(request.getTenantId(),
				request.getSubscriberId());

		if (ObjectUtils.isEmpty(tenant)) {
			logger.error("Tenant not found with ID: {}", request.getTenantId());
			throw new BusinessException("Tenant not found with ID: " + request.getTenantId(), null);
		}

		Unit unit = unitRepository.findByUnitIdAndSubscriberId(request.getUnitId(), request.getSubscriberId());
		if (unit == null) {
			logger.error("Unit not found with ID: {}", request.getUnitId());
			throw new BusinessException("Unit not found with ID: " + request.getUnitId(), null);
		}

		UnitStatus unitStatus = unit.getUnitStatus();
		if (unitStatus != null && (unitStatus.getUnitStatusId() == UnitStatusEnum.OCCUPIED.getValue()
				|| unitStatus.getUnitStatusId() == UnitStatusEnum.RESERVED.getValue())) {
			logger.warn("Unit already registered or reserved, unitId: {}", unit.getUnitId());
			throw new BusinessException("Already this Unit registered / reserved for another tenant", null);
		}

		if (request.getParkingId() != null) {
			logger.info("Checking parking availability for parkingId: {}", request.getParkingId());
			Parking parking = parkingRepository.findByParkingIdAndSubscriberId(request.getParkingId(),
					request.getSubscriberId());

			if (parking == null) {
				logger.error("Parking not found with ID: {}", request.getParkingId());
				throw new BusinessException("Parking not found with ID: " + request.getParkingId(), null);
			}

			Parking alreadyAssignedParking = parkingRepository.findParkingWithTenantUnit(request.getParkingId());
			if (alreadyAssignedParking != null) {
				logger.warn("Parking already assigned, parkingId: {}", request.getParkingId());
				throw new BusinessException("This Parking already allotted to someone: " + request.getParkingId(),
						null);
			}

			tenantUnit.setParking(parking);
			parking.setIsAvailable(false);
		}

		Optional<RentCycle> rentCycleOp = rentCycleRepository.findById(request.getRentCycleId());
		if (!rentCycleOp.isPresent()) {
			logger.error("RentCycle not found with id: {}", request.getRentCycleId());
			throw new BusinessException("rentCycle not found with id: " + request.getRentCycleId(), null);
		}
		RentCycle rentCycle = rentCycleOp.get();

		Optional<UnitStatus> unitStatusOp = unitStatusRepository.findById(UnitStatusEnum.RESERVED.getValue());
		if (!unitStatusOp.isPresent()) {
			logger.error("Reserved UnitStatus not found in repository");
			throw new BusinessException("Invalid UnitStatus", null);
		}
		unit.setUnitStatus(unitStatusOp.get());

		tenantUnit.setTenant(tenant);
		tenantUnit.setUnit(unit);
		tenantUnit.setTenurePeriodMonth(request.getTenurePeriodMonth());
		tenantUnit.setRentCycle(rentCycle);
		tenantUnit.setExpired(false);
		tenantUnit.setActive(false);
		tenantUnit.setCreatedTime(new Date());
		tenantUnit.setCreatedBy(request.getSubscriberId());
		tenantUnit.setSubscriber(subscriber.get());

		tenantUnitRepository.save(tenantUnit);
		logger.info("TenantUnit saved successfully with ID: {}", tenantUnit.getTenantUnitId());

		if (tenantUnit != null && tenantUnit.getTenantUnitId() != null) {
			TenureDetails tenureDetails = new TenureDetails();
			tenureDetails
					.setTenancyStartDate(CommonUtil.getDatefromString(request.getTenancyStartDate(), DATE_ddMMyyyy));
			tenureDetails.setTenancyEndDate(calculateTenancyEndDate(request));
			tenureDetails.setTenantUnit(tenantUnit);
			//tenureDetails.setTe
			tenureDetails.setTotalRentPerYear(unit.getRentYear());
			tenureDetails.setDiscount(request.getDiscount());
			tenureDetails.setTotalRentAfDiscount((unit.getRentYear()-(unit.getRentYear()*(request.getDiscount()/100))));
			tenureDetails.setSecurityDeposit(unit.getSecurityDeposit());
			tenureDetails.setCreatedBy(request.getSubscriberId());
			tenureDetails.setSubscriber(subscriber.get());
			tenureDetailsRepository.save(tenureDetails);
			//tenantUnit.setTenureDetails(tenureDetails);
			logger.info("TenureDetails saved successfully for tenantUnitId: {}", tenantUnit.getTenantUnitId());

			if (tenureDetails.getTenantTenureId() != null) {
				TenantUnitResponse tenantUnitResp = new TenantUnitResponse();
				BeanUtils.copyProperties(tenantUnit, tenantUnitResp);
				logger.info("TenantUnit added successfully, returning response");
				return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, tenantUnitResp, null, null);
			}
		}

		logger.warn("Failed to create TenantUnit or TenureDetails properly");
		return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
	}

	private Date calculateTenancyEndDate(TenantUnitRequest request) {
		LocalDate tenStartDate = CommonUtil.getLocalDatefromString(request.getTenancyStartDate(),
				CommonConstants.DATE_ddMMyyyy);
		LocalDate tenEndDate = tenStartDate.plusMonths(request.getTenurePeriodMonth());
		return CommonUtil.getDatefromLocalDate(tenEndDate);

	}

	@SuppressWarnings("unused")
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
/*
	public ApiResponse<Object> reserveUnit(Integer subscriberId, ReserveUnitRequest request)throws Exception {
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;
		//try {

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
			// newUser.setEidaCopy(request.getEidaCopy());

			if (!ObjectUtils.isEmpty(request.getEidaCopy())) {
				String contentType = CommonUtil.validateAttachment(request.getEidaCopy());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.EID_PIC, contentType, fileExt,
						Base64.getEncoder().encodeToString(request.getEidaCopy()), null);
				s3UploadObjectDtoList.add(s3BuildingLogoDto);

				S3UploadDto s3UploadDto = new S3UploadDto();
				s3UploadDto.setSubscriberId(subscriberId);
				s3UploadDto.setObjectType(S3UploadObjTypeEnum.EID.toString());
				s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
				List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
				for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
					if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.EID_PIC)) {
							newUser.setEidaCopyFilename(s3UploadObjectDto.getS3FileName());
						}

					}
				}
			}

			Countries nationality = countriesRepository.findById(request.getNationalityId()).orElse(null);

			newUser.setNationality(nationality);
			Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
			if (existingUser.isPresent()) {
				logger.info("reserveUnit existing user=" + newUser.getEmail());
				newUser = existingUser.get();
			} else {
				logger.info("reserveUnit new user=" + newUser.getEmail());
				userRepository.save(newUser);
			}

			if (unitReservePaymentOption) {
				// create Order todo
				UnitReserveDetails reserveDetails = new UnitReserveDetails();
				reserveDetails.setUnit(unit);
				reserveDetails.setUser(newUser);
				reserveDetails.setReserveStartDate(new Date());
				reserveDetails.setReserveEndDate(reserveEndDate);
				reserveDetails.setPaymentRequired((unitReservePaymentOption) ? 1 : 0);
				UnitReserveDetails reserveDet = unitReserveDetailsRepository.save(reserveDetails);
				if (reserveDet != null && reserveDet.getUnitReserveId() != null) {
					unit.setUnitStatus(unitStatusRepository.findByUnitStatusName(UnitStatusEnum.RESERVED.toString()));
					unitRepository.save(unit);
					GenericResponse gr = new GenericResponse();
					gr.setId(unit.getUnitId());
					return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, gr, null, null);
					//return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Please proceed the payment", null, null);
				}
				

			} else {
				unit.setUnitStatus(unitStatusRepository.findByUnitStatusName(UnitStatusEnum.RESERVED.toString()));
				unitRepository.save(unit);

				UnitReserveDetails reserveDetails = new UnitReserveDetails();
				reserveDetails.setUnit(unit);
				reserveDetails.setUser(newUser);
				reserveDetails.setReserveStartDate(new Date());
				reserveDetails.setReserveEndDate(reserveEndDate);
				reserveDetails.setPaymentRequired((unitReservePaymentOption) ? 1 : 0);
				UnitReserveDetails reserveDet = unitReserveDetailsRepository.save(reserveDetails);
				if (reserveDet != null && reserveDet.getUnitReserveId() != null) {
					NotifEmailDTO dto = new NotifEmailDTO();
					dto.setEmailTo(request.getEmail());
					dto.setCustomerName(request.getEmail());
					dto.setBuildingId(CommonUtil.getStringValofObject(unit.getBuilding().getBuildingId()));
					dto.setBuildingName(unit.getBuilding().getBuildingName());
					dto.setUnitId(CommonUtil.getStringValofObject(unit.getUnitId()));
					dto.setUnitName(unit.getUnitName());
					dto.setReserveFromDate(CommonUtil.getStringDatefromDate(new Date()));
					dto.setReserveToDate(CommonUtil.getStringDatefromDate(reserveEndDate));
					;

					NotificationEmailResponseDTO notifResp = notificationService.sendUnitReservationEmail(dto);
					if (notifResp != null && notifResp.isEmailSent()) {
						logger.info("reserveUnit email successfully sent to user{}, reserveDet={}" + newUser.getEmail(),
								reserveDet);
						
						return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Unit successfully reserved", null, null);
					}
				}
			}

//		} catch (Exception e) {
//			throw new BusinessException("Failed to reserve unit", e);
//			//throw e;
//		}
		return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "Failed to reserve unit: ", null, null);
	}
*/
	@SuppressWarnings("rawtypes")
	public ApiResponse<Object> getAllBuildings(Integer subscriberId, BuildingSearchRequest request) {
		PaginationRequest paginationRequest = request.getPaginationRequest();
		Sort sort = paginationRequest.getSortDirection().equalsIgnoreCase("desc")
				? Sort.by(paginationRequest.getSortBy()).descending()
				: Sort.by(paginationRequest.getSortBy()).ascending();
		PageRequest pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), sort);

		List<Building> listOfBuildings = buildingRepository.findAllBySubscriberIdAndIsDeletedFalse(subscriberId);

		if (StringUtils.isNotBlank(request.getSearch())) {
			String searchTerm = request.getSearch().toLowerCase();
			listOfBuildings = listOfBuildings.stream().filter(b -> {
				try {
					if (b.getBuildingId().toString().contains(searchTerm)) {
						return true;
					}
				} catch (Exception e) {
				}

				if (StringUtils.isNotBlank(b.getBuildingName())
						&& b.getBuildingName().toLowerCase().contains(searchTerm)) {
					return true;
				}

				if (b.getCommunity() != null && b.getCommunity().getArea() != null
						&& b.getCommunity().getArea().getCity() != null
						&& StringUtils.isNotBlank(b.getCommunity().getArea().getCity().getName())
						&& b.getCommunity().getArea().getCity().getName().toLowerCase().contains(searchTerm)) {
					return true;
				} else if (b.getArea() != null && b.getArea().getCity() != null
						&& StringUtils.isNotBlank(b.getArea().getCity().getName())
						&& b.getArea().getCity().getName().toLowerCase().contains(searchTerm)) {
					return true;
				}

				if (b.getCommunity() != null && b.getCommunity().getArea() != null
						&& StringUtils.isNotBlank(b.getCommunity().getArea().getAreaName())
						&& b.getCommunity().getArea().getAreaName().toLowerCase().contains(searchTerm)) {
					return true;
				} else if (b.getArea() != null && StringUtils.isNotBlank(b.getArea().getAreaName())
						&& b.getArea().getAreaName().toLowerCase().contains(searchTerm)) {
					return true;
				}

				return false;
			}).collect(Collectors.toList());
		}

		List<BuildingDetailDTO> listBD = new ArrayList<>();

		for (Building b : listOfBuildings) {
			BuildingDetailDTO bt = new BuildingDetailDTO();
			BeanUtils.copyProperties(b, bt);

			if (StringUtils.isNotBlank(b.getBuildingLogoFileName())) {
				bt.setBuildingLogoLink(s3Service.generatePresignedUrl(subscriberId, b.getBuildingId(), null,
						b.getBuildingLogoFileName()));
			}

			if (b.getCommunity() != null) {
				Community ct = b.getCommunity();
				bt.setCommunityId(ct.getCommunityId());
				bt.setCommunityName(ct.getCommunityName());
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

//			List<Floor> floors = floorRepository.findByBuilding(b);
//			bt.setFloors(floors.stream().map(f -> {
//				FloorDTO dto = new FloorDTO();
//				dto.setFloorId(f.getFloorId());
//				dto.setFloorName(f.getFloorName());
//				return dto;
//			}).collect(Collectors.toList()));

			List<Parking> parkings = parkingRepository.findByBuilding(b);
			bt.setParkings(parkings.stream().map(p -> {
				ParkingDTO dto = new ParkingDTO();
				dto.setParkingId(p.getParkingId());
				dto.setParkingName(p.getParkingName());
				dto.setParkingType(p.getParkingType());
				dto.setIsAvailable(p.getIsAvailable());
				if (p.getParkZone() != null) {
					ParkingZoneSimpleDTO zoneDto = new ParkingZoneSimpleDTO();
					zoneDto.setParkZoneId(p.getParkZone().getParkZoneId());
					zoneDto.setParkZoneName(p.getParkZone().getParkZoneName());
					dto.setParkZone(zoneDto);
				}
				return dto;
			}).collect(Collectors.toList()));

			List<ParkingZone> parkingZones = parkingZoneRepository.findByBuilding(b);
			bt.setParkingZones(parkingZones.stream().map(pz -> {
				ParkingZoneDTO dto = new ParkingZoneDTO();
				dto.setParkZoneId(pz.getParkZoneId());
				dto.setParkZoneName(pz.getParkZoneName());
				return dto;
			}).collect(Collectors.toList()));

			List<Unit> units = unitRepository.findByBuildingAndIsDeletedFalse(b);
			bt.setUnits(units.stream().map(u -> {
				UnitDTO dto = new UnitDTO();
				dto.setUnitId(u.getUnitId());
				dto.setUnitName(u.getUnitName());
				if (u.getFloor() != null) {
					dto.setFloorName(u.getFloor().getFloorName());
				}
				if (u.getUnitType() != null) {
					dto.setUnitType(u.getUnitType().getUnitTypeName());
				}
				if (u.getUnitSubType() != null) {
					dto.setUnitSubType(u.getUnitSubType().getUnitSubtypeName());
				}
				dto.setSize(u.getSize());
				dto.setHasBalcony(u.getHasBalcony());
				if (u.getUnitStatus() != null) {
					dto.setUnitStatus(u.getUnitStatus().getUnitStatusName());
				}
				dto.setRentMonth(u.getRentMonth());
				dto.setRentYear(u.getRentYear());
				dto.setSecurityDeposit(u.getSecurityDeposit());
				dto.setWaterConnNo(u.getWaterConnNo());
				dto.setEbConnNo(u.getEbConnNo());

				List<String> unitImages = new ArrayList<>();
				if (StringUtils.isNotBlank(u.getUnitMainPic1Name())) {
					unitImages.add(s3Service.generatePresignedUrl(subscriberId, b.getBuildingId(), u.getUnitId(),
							u.getUnitMainPic1Name()));
				}
				dto.setUnitImages(unitImages);

				Optional<TenantUnit> tenantUnit = tenantUnitRepository.findByUnitAndActiveTrue(u);
				if (tenantUnit.isPresent() && tenantUnit.get().getTenant() != null
						&& !tenantUnit.get().getTenant().getIsDeleted()) {
					Tenant tenant = tenantUnit.get().getTenant();
					TenantSimpleDTO tenantDto = new TenantSimpleDTO();
					tenantDto.setTenantId(tenant.getTenantId());
					tenantDto.setFirstName(tenant.getFirstName());
					tenantDto.setLastName(tenant.getLastName());
					tenantDto.setEmail(tenant.getEmail());
					tenantDto.setPhoneNumber(tenant.getPhoneNumber());
					tenantDto.setEidaExpiryDate(tenant.getEidaExpiryDate());
					tenantDto.setPassportNo(tenant.getPassportNo());
					tenantDto.setPassportExpiryDate(tenant.getPassportExpiryDate());
					if (tenant.getNationality() != null) {
						tenantDto.setNationality(tenant.getNationality().getName());
					}

//					List<TenureDetails> tenureDetails = tenureDetailsRepository.findByTenantUnit(tenantUnit.get());
//					if (!tenureDetails.isEmpty()) {
//						tenantDto.setTenureDetails(tenureDetails.stream().map(td -> {
//							TenureDetailsDTO tenureDto = new TenureDetailsDTO();
//							tenureDto.setTenantTenureId(td.getTenantTenureId());
//							tenureDto.setTenancyStartDate(td.getTenancyStartDate());
//							tenureDto.setTenancyEndDate(td.getTenancyEndDate());
//							return tenureDto;
//						}).collect(Collectors.toList()));
//					}

					dto.setTenant(tenantDto);
				}

				UnitReserveDetails reservation = unitReserveDetailsRepository.findByUnit(u);
				if (reservation != null) {
					UnitReserveDetailsDTO reserveDto = new UnitReserveDetailsDTO();
					reserveDto.setUnitReserveId(reservation.getUnitReserveId());
					reserveDto.setReserveStartDate(reservation.getReserveStartDate());
					reserveDto.setReserveEndDate(reservation.getReserveEndDate());
					reserveDto.setPaymentRequired(reservation.getPaymentRequired());
					if (reservation.getUser() != null) {
						UserSimpleDTO userDto = new UserSimpleDTO();
						userDto.setUserId(reservation.getUser().getUserId().intValue());
						userDto.setUserName(reservation.getUser().getEmail());
						userDto.setEmail(reservation.getUser().getEmail());
						reserveDto.setReservedBy(userDto);
					}
					dto.setReservation(reserveDto);
				}

				List<UnitKeys> unitKeys = unitKeysRepository.findByUnit(u);
				if (!unitKeys.isEmpty()) {
					dto.setKeys(unitKeys.stream().map(uk -> {
						UnitKeyDTO keyDto = new UnitKeyDTO();
						keyDto.setUnitKeysId(uk.getUnitKeysId());
						if (uk.getKeyMaster() != null) {
							keyDto.setKeyName(uk.getKeyMaster().getKeyName());
						}
						return keyDto;
					}).collect(Collectors.toList()));
				}
				
				
				List <Bedspace> bedspaceList=bedspaceRepository.findByUnit_UnitId(u.getUnitId());
				if (bedspaceList!=null && !bedspaceList.isEmpty()) {
					
					
					dto.setBedspaces(bedspaceList.stream().map(bs-> {
						BedspaceDTO bsDto = new BedspaceDTO();
						bsDto.setBedspaceId(bs.getBedspaceId());
						bsDto.setBedspaceName(bs.getBedspaceName());
						

						if(bs.getPartition()!=null) {
							bsDto.setPartitionId(bs.getPartition().getBedspacePartitionId());
							bsDto.setPartitionTypeName(bs.getPartition().getBedspacePartitionName());							
						}
						if(bs.getBedspaceCategory()!=null) {
							bsDto.setBedspaceCategoryId(bs.getBedspaceCategory().getBedspaceCatId());
							bsDto.setBedspaceCategoryName(bs.getBedspaceCategory().getBedspaceCatName());
						}
						
						if(bs.getBathroomType()!=null) {
							bsDto.setBedspaceBathroomTypeId(bs.getBathroomType().getBedspaceBathroomTypeId());
							bsDto.setBedspaceBathroomTypeName(bs.getBathroomType().getBedspaceBathroomTypeName());
						}
						bsDto.setFeatures(bs.getFeatures());
						bsDto.setSecurityDeposit(bs.getSecurityDeposit());
						bsDto.setRentDay(bs.getRentDay());
						bsDto.setRentMonth(bs.getRentMonth());
						bsDto.setStatus(bs.getStatus());
						bsDto.setUnitId(u.getUnitId());
						
						List<String> bsImages = new ArrayList<>();
						if (StringUtils.isNotBlank(bs.getBsMainPic1Name())) {
							bsImages.add(s3Service.generatePresignedUrl(subscriberId, b.getBuildingId(), u.getUnitId(),
									bs.getBsMainPic1Name()));
						}
						bsDto.setBsImages(bsImages);

						return bsDto;
					}).collect(Collectors.toList()));
					
					
					
				}

				return dto;
			}).collect(Collectors.toList()));
			listBD.add(bt);
		}

		Page<BuildingDetailDTO> pageBD = PaginationUtils.convertListToPage(listBD, pageable);

		PaginationResponse pgResp = new PaginationResponse<>(pageBD.getContent(), pageBD.getNumber(),
				pageBD.getTotalPages(), pageBD.getTotalElements(), pageBD.isFirst(), pageBD.isLast());
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, pgResp, null, null);
	}
	
	
	@SuppressWarnings("rawtypes")
	public ApiResponse<Object> getBuildingById(Integer subscriberId, Integer buildingId) {
		
		Building b = buildingRepository.findByBuildingIdAndSubscriberId(buildingId,subscriberId);

		List<BuildingDetailDTO> listBD = new ArrayList<>();

	
			BuildingDetailDTO bt = new BuildingDetailDTO();
			BeanUtils.copyProperties(b, bt);

			if (StringUtils.isNotBlank(b.getBuildingLogoFileName())) {
				bt.setBuildingLogoLink(s3Service.generatePresignedUrl(subscriberId, b.getBuildingId(), null,
						b.getBuildingLogoFileName()));
			}

			if (b.getCommunity() != null) {
				Community ct = b.getCommunity();
				bt.setCommunityId(ct.getCommunityId());
				bt.setCommunityName(ct.getCommunityName());
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


		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, bt, null, null);
	}

	public ApiResponse<Object> searchBuildings(Integer subscriberId, BuildingSearchRequest request) {

		PaginationRequest paginationRequest = request.getPaginationRequest();
		@SuppressWarnings("unused")
		Sort sort = paginationRequest.getSortDirection().equalsIgnoreCase("desc")
				? Sort.by(paginationRequest.getSortBy()).descending()
				: Sort.by(paginationRequest.getSortBy()).ascending();

		PageRequest pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(),
				Sort.by("buildingName").ascending());
		Specification<Building> spec = BuildingSpecification.searchByKeyword(request.getSearch(), subscriberId);

		Page<Building> pageResult = buildingRepository.findAll(spec, pageable);

		List<BuildingSearchDto> dtos = pageResult.getContent().stream().map(building -> {
			String areaName = building.getArea() != null ? building.getArea().getAreaName() : null;
			String cityName = (building.getArea() != null && building.getArea().getCity() != null)
					? building.getArea().getCity().getName()
					: null;

			return new BuildingSearchDto(building.getBuildingId(), building.getBuildingName(), areaName, cityName,
					building.getAddress(), building.getHasGym(), building.getHasSwimpool(),
					building.getHasKidsPlayground(), building.getHasPlaycourt(), building.getNoOfFloors(),
					building.getNoOfUnits(), building.getLatitude(), building.getLongitude(),
					(building.getCommunity() != null) ? building.getCommunity().getCommunityId() : 0,
					(building.getCommunity() != null) ? building.getCommunity().getCommunityName() : "",
					(building.getArea() != null) ? building.getArea().getAreaId() : 0,
					(building.getArea() != null)
							? (building.getArea().getCountry() != null) ? building.getArea().getCountry().getCountryId()
									: 0
							: 0,
					(building.getArea() != null)
							? (building.getArea().getCountry() != null) ? building.getArea().getCountry().getName() : ""
							: "",
					(building.getArea() != null)
							? (building.getArea().getState() != null) ? building.getArea().getState().getStateId() : 0
							: 0,
					(building.getArea() != null)
							? (building.getArea().getState() != null) ? building.getArea().getState().getName() : ""
							: "",
					(building.getArea() != null)
							? (building.getArea().getCity() != null) ? building.getArea().getCity().getCityId() : 0
							: 0,
					getBuildlingLogoLink(building), // logolink,
					getBuildlingFloors(building), // floor
					getBuildlingParking(building), // parking,
					getBuildlingParkingZone(building), // parkingzone,
					getBuildlingUnits(building)// units

			);
		}).collect(Collectors.toList());

		@SuppressWarnings("rawtypes")
		PaginationResponse pgResp = new PaginationResponse<>(dtos, pageResult.getNumber(), pageResult.getTotalPages(),
				pageResult.getTotalElements(), pageResult.isFirst(), pageResult.isLast());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, pgResp, null, null);
	}
	
	


	private List<FloorDTO> getBuildlingFloors(Building b) {

//		List<Floor> floorList = floorRepository.findByBuilding(b);
//
//		DtoConverter<Floor, FloorDTO> floorToDto = floor -> {
//			FloorDTO dto = new FloorDTO();
//			dto.setFloorId(floor.getFloorId());
//			dto.setFloorName(floor.getFloorName());
//			return dto;
//		};
//		List<FloorDTO> dtos = DtoMapperUtil.mapList(floorList, floorToDto);
//		return dtos;
		return null;
	}

	private List<UnitDTO> getBuildlingUnits(Building b) {
		List<Unit> units = unitRepository.findByBuildingAndIsDeletedFalse(b);

		List<UnitDTO> unitDtos = units.stream().map(u -> {
			UnitDTO dto = new UnitDTO();
			dto.setUnitId(u.getUnitId());
			dto.setUnitName(u.getUnitName());
			if (u.getFloor() != null) {
				dto.setFloorName(u.getFloor().getFloorName());
			}
			if (u.getUnitType() != null) {
				dto.setUnitType(u.getUnitType().getUnitTypeName());
			}
			if (u.getUnitSubType() != null) {
				dto.setUnitSubType(u.getUnitSubType().getUnitSubtypeName());
			}
			dto.setSize(u.getSize());
			dto.setHasBalcony(u.getHasBalcony());
			if (u.getUnitStatus() != null) {
				dto.setUnitStatus(u.getUnitStatus().getUnitStatusName());
			}
			dto.setRentMonth(u.getRentMonth());
			dto.setRentYear(u.getRentYear());
			dto.setSecurityDeposit(u.getSecurityDeposit());
			dto.setWaterConnNo(u.getWaterConnNo());
			dto.setEbConnNo(u.getEbConnNo());

			List<String> unitImages = new ArrayList<>();
			if (StringUtils.isNotBlank(u.getUnitMainPic1Name())) {
				unitImages.add(s3Service.generatePresignedUrl(b.getSubscriber().getSubscriberId(), b.getBuildingId(),
						u.getUnitId(), u.getUnitMainPic1Name()));
			}
			dto.setUnitImages(unitImages);

			Optional<TenantUnit> tenantUnit = tenantUnitRepository.findByUnitAndActiveTrue(u);
			if (tenantUnit.isPresent() && tenantUnit != null && tenantUnit.get().getTenant() != null
					&& !tenantUnit.get().getTenant().getIsDeleted()) {
				Tenant tenant = tenantUnit.get().getTenant();
				TenantSimpleDTO tenantDto = new TenantSimpleDTO();
				tenantDto.setTenantId(tenant.getTenantId());
				tenantDto.setFirstName(tenant.getFirstName());
				tenantDto.setLastName(tenant.getLastName());
				tenantDto.setEmail(tenant.getEmail());
				tenantDto.setPhoneNumber(tenant.getPhoneNumber());
				tenantDto.setEidaExpiryDate(tenant.getEidaExpiryDate());
				tenantDto.setPassportNo(tenant.getPassportNo());
				tenantDto.setPassportExpiryDate(tenant.getPassportExpiryDate());
				if (tenant.getNationality() != null) {
					tenantDto.setNationality(tenant.getNationality().getName());
				}

//				List<TenureDetails> tenureDetails = tenureDetailsRepository.findByTenantUnit(tenantUnit.get());
//				if (!tenureDetails.isEmpty()) {
//					tenantDto.setTenureDetails(tenureDetails.stream().map(td -> {
//						TenureDetailsDTO tenureDto = new TenureDetailsDTO();
//						tenureDto.setTenantTenureId(td.getTenantTenureId());
//						tenureDto.setTenancyStartDate(td.getTenancyStartDate());
//						tenureDto.setTenancyEndDate(td.getTenancyEndDate());
//						return tenureDto;
//					}).collect(Collectors.toList()));
//				}

				dto.setTenant(tenantDto);
			}

			UnitReserveDetails reservation = unitReserveDetailsRepository.findByUnit(u);
			if (reservation != null) {
				UnitReserveDetailsDTO reserveDto = new UnitReserveDetailsDTO();
				reserveDto.setUnitReserveId(reservation.getUnitReserveId());
				reserveDto.setReserveStartDate(reservation.getReserveStartDate());
				reserveDto.setReserveEndDate(reservation.getReserveEndDate());
				reserveDto.setPaymentRequired(reservation.getPaymentRequired());
				if (reservation.getUser() != null) {
					UserSimpleDTO userDto = new UserSimpleDTO();
					userDto.setUserId(reservation.getUser().getUserId().intValue());
					userDto.setUserName(reservation.getUser().getEmail());
					userDto.setEmail(reservation.getUser().getEmail());
					reserveDto.setReservedBy(userDto);
				}
				dto.setReservation(reserveDto);
			}

			List<UnitKeys> unitKeys = unitKeysRepository.findByUnit(u);
			if (!unitKeys.isEmpty()) {
				dto.setKeys(unitKeys.stream().map(uk -> {
					UnitKeyDTO keyDto = new UnitKeyDTO();
					keyDto.setUnitKeysId(uk.getUnitKeysId());
					if (uk.getKeyMaster() != null) {
						keyDto.setKeyName(uk.getKeyMaster().getKeyName());
					}
					return keyDto;
				}).collect(Collectors.toList()));
			}

			return dto;
		}).collect(Collectors.toList());
		return unitDtos;
	}

	private List<ParkingZoneDTO> getBuildlingParkingZone(Building b) {
		List<ParkingZone> parkingZonesList = parkingZoneRepository.findByBuilding(b);

		DtoConverter<ParkingZone, ParkingZoneDTO> parkToDto = park -> {
			ParkingZoneDTO dto = new ParkingZoneDTO();
			dto.setParkZoneId(park.getParkZoneId());
			dto.setParkZoneName(park.getParkZoneName());
			return dto;
		};
		List<ParkingZoneDTO> dtos = DtoMapperUtil.mapList(parkingZonesList, parkToDto);
		return dtos;

	}

	private List<ParkingDTO> getBuildlingParking(Building b) {
		List<Parking> parkings = parkingRepository.findByBuilding(b);

		List<ParkingDTO> parkingsDtos = parkings.stream().map(p -> {
			ParkingDTO dto = new ParkingDTO();
			dto.setParkingId(p.getParkingId());
			dto.setParkingName(p.getParkingName());
			dto.setParkingType(p.getParkingType());
			dto.setIsAvailable(p.getIsAvailable());
			if (p.getParkZone() != null) {
				ParkingZoneSimpleDTO zoneDto = new ParkingZoneSimpleDTO();
				zoneDto.setParkZoneId(p.getParkZone().getParkZoneId());
				zoneDto.setParkZoneName(p.getParkZone().getParkZoneName());
				dto.setParkZone(zoneDto);
			}
			return dto;
		}).collect(Collectors.toList());
		return parkingsDtos;
	}

	private String getBuildlingLogoLink(Building b) {
		if (StringUtils.isNotBlank(b.getBuildingLogoFileName())) {
			return s3Service.generatePresignedUrl(b.getSubscriber().getSubscriberId(), b.getBuildingId(), null,
					b.getBuildingLogoFileName());
		}
		return "";
	}

	@SuppressWarnings("rawtypes")
	public ApiResponse<Object> getAllUnitsByBuildingId(Integer subscriberId, Integer buildingId,
			PaginationRequest paginationRequest) {
		try {
			Sort sort = paginationRequest.getSortDirection().equalsIgnoreCase("desc")
					? Sort.by(paginationRequest.getSortBy()).descending()
					: Sort.by(paginationRequest.getSortBy()).ascending();
			PageRequest pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), sort);

			Page<Unit> unitPage = unitRepository.findUnitsByBuildingIdWithPagination(buildingId, subscriberId,pageable);

			List<UnitDetailResponse> unitDTOs = unitPage.getContent().stream().map(unit -> {
				UnitDetailResponse dto = new UnitDetailResponse();
				BeanUtils.copyProperties(unit, dto);

				if (unit.getBuilding() != null) {
					dto.setBuildingId(unit.getBuilding().getBuildingId());
				}

				if (unit.getFloor() != null) {
					dto.setFloor(unit.getFloor().getFloorName());
				}

				if (unit.getUnitType() != null) {
					dto.setUnitTypeId(unit.getUnitType().getUnitTypeId());
					dto.setUnitTypeName(unit.getUnitType().getUnitTypeName());
				}

				if (unit.getUnitSubType() != null) {
					dto.setUnitSubTypeId(unit.getUnitSubType().getUnitSubtypeId());
					dto.setUnitSubTypeName(unit.getUnitSubType().getUnitSubtypeName());
				}

				if (unit.getUnitStatus() != null) {
					dto.setUnitStatusId(unit.getUnitStatus().getUnitStatusId());
					dto.setUnitStatusName(unit.getUnitStatus().getUnitStatusName());
				}

				if (unit.getUnitStatus() != null && unit.getUnitStatus().getUnitStatusId() == 2) {
					tenantUnitRepository.findByUnitAndActiveTrue(unit).ifPresent(tenantUnit -> {
						TenantDetailResponse tenantDetail = new TenantDetailResponse();
						BeanUtils.copyProperties(tenantUnit.getTenant(), tenantDetail);
						dto.setTenantDetails(tenantDetail);
					});//
				}

				if (StringUtils.isNotBlank(unit.getUnitMainPic1Name())) {
					S3DownloadDto s3DownloadDto = new S3DownloadDto(subscriberId, buildingId, unit.getUnitId(),
							unit.getUnitMainPic1Name());
					dto.setUnitMainPic1Link(s3Service.generatePresignedUrl(s3DownloadDto));
				}
				if (StringUtils.isNotBlank(unit.getUnitPic2Name())) {
					S3DownloadDto s3DownloadDto = new S3DownloadDto(subscriberId, buildingId, unit.getUnitId(),
							unit.getUnitPic2Name());
					dto.setUnitPic2Link(s3Service.generatePresignedUrl(s3DownloadDto));
				}
				if (StringUtils.isNotBlank(unit.getUnitPic3Name())) {
					S3DownloadDto s3DownloadDto = new S3DownloadDto(subscriberId, buildingId, unit.getUnitId(),
							unit.getUnitPic3Name());
					dto.setUnitPic3Link(s3Service.generatePresignedUrl(s3DownloadDto));
				}
				if (StringUtils.isNotBlank(unit.getUnitPic4Name())) {
					S3DownloadDto s3DownloadDto = new S3DownloadDto(subscriberId, buildingId, unit.getUnitId(),
							unit.getUnitPic4Name());
					dto.setUnitPic4Link(s3Service.generatePresignedUrl(s3DownloadDto));
				}
				if (StringUtils.isNotBlank(unit.getUnitPic5Name())) {
					S3DownloadDto s3DownloadDto = new S3DownloadDto(subscriberId, buildingId, unit.getUnitId(),
							unit.getUnitPic5Name());
					dto.setUnitPic5Link(s3Service.generatePresignedUrl(s3DownloadDto));
				}
				
				
				List <Bedspace> bedspaceList=bedspaceRepository.findByUnit_UnitId(unit.getUnitId());
				if (bedspaceList!=null && !bedspaceList.isEmpty()) {
					bedspaceList.stream().map(bs -> {
						return dto;
						
					}).collect(Collectors.toList());
				}
				
				 List<BedspaceDTO> bedspaceDtos = bedspaceList.stream().map(bedspace -> {
					 
					 //bedspace.
						return new BedspaceDTO(
								bedspace.getBedspaceId(), 
								bedspace.getBedspaceName(),
								bedspace.getFeatures(),
								bedspace.getSecurityDeposit(),
								bedspace.getRentMonth(),
								bedspace.getRentDay(),
								bedspace.getUnit().getUnitId(),
								bedspace.getPartition().getBedspacePartitionId(),
								bedspace.getPartition().getBedspacePartitionName(),
								bedspace.getBedspaceCategory().getBedspaceCatId(),
								bedspace.getBedspaceCategory().getBedspaceCatName(),
								bedspace.getBathroomType().getBedspaceBathroomTypeId(),
								bedspace.getBathroomType().getBedspaceBathroomTypeName(),
								bedspace.getStatus(), null
						);
					}).collect(Collectors.toList());
				
				 dto.setBedspaces(bedspaceDtos);
				return dto;
			}).collect(Collectors.toList());
			

			PaginationResponse response = new PaginationResponse<>(unitDTOs, unitPage.getNumber(),
					unitPage.getTotalPages(), unitPage.getTotalElements(), unitPage.isFirst(), unitPage.isLast());

			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, response, null, null);
		} catch (Exception e) {
			throw new BusinessException("Error fetching units"+e.getMessage(), null);
		}
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
				dto.setUnitName(tenantUnit.getUnit().getUnitName());
				if (tenantUnit.getUnit().getBuilding() != null) {
					dto.setBuildingId(tenantUnit.getUnit().getBuilding().getBuildingId());
					dto.setBuildingName(tenantUnit.getUnit().getBuilding().getBuildingName());
				}
			}
			if (tenantUnit.getParking() != null) {
				dto.setParkingId(tenantUnit.getParking().getParkingId());
				dto.setParkingName(tenantUnit.getParking().getParkingName());
				dto.setParkingType(tenantUnit.getParking().getParkingType());
				if (tenantUnit.getParking().getParkZone() != null) {
					dto.setParkingZoneId(tenantUnit.getParking().getParkZone().getParkZoneId());
					dto.setParkingZoneName(tenantUnit.getParking().getParkZone().getParkZoneName());
				}

			}
			return dto;
		}).collect(Collectors.toList());

		PaginationResponse response = new PaginationResponse<>(tenantDTOs, tenantUnitPage.getNumber(),
				tenantUnitPage.getTotalPages(), tenantUnitPage.getTotalElements(), tenantUnitPage.isFirst(),
				tenantUnitPage.isLast());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, response, null, null);
	}

	public ApiResponse<?> deleteBuilding(DeleteBuildingRequest request) {
		try {
			Building building = buildingRepository.findByBuildingIdAndSubscriberId(request.getBuildingId(),request.getSubscriberId());
			if (building==null) {
				throw new BusinessException("Building not found with id: " + request.getBuildingId(), null);
			}

			
			
			if (building.getIsDeleted()) {
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "Building is already marked as deleted.", null,
						null);
			}

//			List<Floor> floors = floorRepository.findByBuildingBuildingId(request.getBuildingId());
//			if (!floors.isEmpty()) {
//				List<Integer> floorIds = floors.stream().map(Floor::getFloorId).collect(Collectors.toList());
//				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
//						"Cannot delete building because it has associated floors. Floor ids=" + floorIds, null, null);
//			}

			List<Parking> parkingList = parkingRepository.findByBuildingBuildingId(request.getBuildingId());
			if (!parkingList.isEmpty()) {
				List<Integer> parkingIds = parkingList.stream().map(Parking::getParkingId).collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete building because it has associated parking. Parking ids=" + parkingIds, null,
						null);
			}

			List<Unit> units = unitRepository.findByBuildingBuildingId(request.getBuildingId());
			if (!units.isEmpty()) {
				List<Integer> unitIds = units.stream().map(Unit::getUnitId).collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete building because it has associated units. Unit ids=" + unitIds, null, null);
			}

			building.setIsDeleted(true);
			buildingRepository.save(building);

			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Building successfully marked as deleted", null, null);
		} catch (Exception e) {
			throw new BusinessException("Failed to delete building:"+e.getMessage(), e);
		}
	}

	public ApiResponse<?> deleteUnit(DeleteUnitRequest request) {
		try {
			Unit unit = unitRepository.findByUnitIdAndSubscriberId(request.getUnitId(),request.getSubscriberId());
			if (unit==null) {
				throw new BusinessException("Unit not found with id: " + request.getUnitId(), null);
			}

			if (unit.getIsDeleted()) {
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "Unit is already marked as deleted.", null, null);
			}

			List<TenantUnit> tenantUnits = tenantUnitRepository.findByUnitUnitId(request.getUnitId());
			if (!tenantUnits.isEmpty()) {
				List<Integer> tenantUnitIds = tenantUnits.stream().map(TenantUnit::getTenantUnitId)
						.collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete unit because it has associated tenant units. Tenant Unit ids=" + tenantUnitIds,
						null, null);
			}

			List<UnitKeys> unitKeys = unitKeysRepository.findByUnitUnitId(request.getUnitId());
			if (!unitKeys.isEmpty()) {
				List<Integer> unitKeysIds = unitKeys.stream().map(UnitKeys::getUnitKeysId).collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete unit because it has associated unit keys. Unit Keys ids=" + unitKeysIds, null,
						null);
			}

			List<UnitReserveDetails> unitReserveDetails = unitReserveDetailsRepository
					.findByUnitUnitId(request.getUnitId());
			if (!unitReserveDetails.isEmpty()) {
				List<Integer> unitReserveIds = unitReserveDetails.stream().map(UnitReserveDetails::getUnitReserveId)
						.collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete unit because it has associated unit reserve details. Unit Reserve ids="
								+ unitReserveIds,
						null, null);
			}

			unit.setIsDeleted(true);
			unitRepository.save(unit);

			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Unit successfully marked as deleted", null, null);
		} catch (Exception e) {

			throw new BusinessException("Failed to delete unit:"+e.getMessage(), e);
		}
	}
	
	public ApiResponse<?> deleteBedspace(DeleteBedspaceRequest request) {
		try {
			Optional<Bedspace> bedspaceOp = bedspaceRepository.findByIdAndSubscriberId(request.getBedspaceId(),request.getSubscriberId());
			if (!bedspaceOp.isPresent()) {
				throw new BusinessException("Bedspace not found with id: " + request.getBedspaceId(), null);
			}
			
			Bedspace bedspace=bedspaceOp.get();

			if (bedspace.getIsDeleted()) {
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "Bedspace is already marked as deleted.", null, null);
			}
/*
			List<TenantUnit> tenantUnits = tenantUnitRepository.findByUnitUnitId(request.getUnitId());
			if (!tenantUnits.isEmpty()) {
				List<Integer> tenantUnitIds = tenantUnits.stream().map(TenantUnit::getTenantUnitId)
						.collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete unit because it has associated tenant units. Tenant Unit ids=" + tenantUnitIds,
						null, null);
			}

			List<UnitKeys> unitKeys = unitKeysRepository.findByUnitUnitId(request.getUnitId());
			if (!unitKeys.isEmpty()) {
				List<Integer> unitKeysIds = unitKeys.stream().map(UnitKeys::getUnitKeysId).collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete unit because it has associated unit keys. Unit Keys ids=" + unitKeysIds, null,
						null);
			}

			List<UnitReserveDetails> unitReserveDetails = unitReserveDetailsRepository
					.findByUnitUnitId(request.getUnitId());
			if (!unitReserveDetails.isEmpty()) {
				List<Integer> unitReserveIds = unitReserveDetails.stream().map(UnitReserveDetails::getUnitReserveId)
						.collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete unit because it has associated unit reserve details. Unit Reserve ids="
								+ unitReserveIds,
						null, null);
			}

			unit.setIsDeleted(true);
			unitRepository.save(unit);
*/
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Bedspace successfully marked as deleted", null, null);
		} catch (Exception e) {

			throw new BusinessException("Failed to delete bedspace:"+e.getMessage(), e);
		}
		
	}

	public ApiResponse<?> deleteTenant(TenantIdRequest request) {
		try {
			Tenant tenant = tenantRepository.findById(request.getTenantId()).orElseThrow(
					() -> new ResourceNotFoundException("Tenant not found with id: " + request.getTenantId()));

			if (tenant.getIsDeleted()) {
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, "Tenant is already marked as deleted.", null,
						null);
			}

			List<TenantUnit> tenantUnits = tenantUnitRepository.findByTenantTenantId(request.getTenantId());
			if (!tenantUnits.isEmpty()) {
				List<Integer> tenantUnitIds = tenantUnits.stream().map(TenantUnit::getTenantUnitId)
						.collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete tenant because it has associated tenant units. Tenant Unit ids=" + tenantUnitIds,
						null, null);
			}

//			List<Order> orders = orderRepository.findByTenantTenantId(request.getTenantId());
//			if (!orders.isEmpty()) {
//				List<Integer> orderIds = orders.stream().map(Order::getOrderId).collect(Collectors.toList());
//				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
//						"Cannot delete tenant because it has associated orders. Order ids=" + orderIds, null, null);
//			}

			List<TenureDetails> tenureDetails = null;//.findByTenantUnitTenantTenantId(request.getTenantId());
			if (!tenureDetails.isEmpty()) {
				List<Integer> tenureDetailIds = tenureDetails.stream().map(TenureDetails::getTenantTenureId)
						.collect(Collectors.toList());
				return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC,
						"Cannot delete tenant because it has associated tenure details. Tenure Detail ids="
								+ tenureDetailIds,
						null, null);
			}

			tenant.setIsDeleted(true);
			tenantRepository.save(tenant);

			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Tenant successfully marked as deleted", null, null);
		} catch (Exception e) {
			throw new BusinessException("Failed to delete tenant:"+e.getMessage(), e);
		}
	}

	public ApiResponse<?> updateBuilding(BuildingRequest request) throws Exception {
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;

		Building existingBuilding = buildingRepository.findByBuildingIdAndSubscriberId(request.getBuildingId(),
				request.getSubscriberId());
		if (existingBuilding == null) {
			throw new BusinessException("Building not found with id: " + request.getBuildingId(), null);
		}

		Countries country = null;
		State state = null;

		if (request.getCountryId() != null || request.getStateId() != null) {
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
		}

		City city = null;
		if (existingBuilding.getArea() != null) {
			city = existingBuilding.getArea().getCity();
			if (city != null && StringUtils.isNotBlank(request.getCityName())
					&& !request.getCityName().equals(city.getName())) {
				city.setName(request.getCityName());
				if (country != null)
					city.setCountry(country);
				if (state != null)
					city.setState(state);
				cityRepository.save(city);
			}
		}

		Area area = existingBuilding.getArea();
		if (area != null) {
			if (StringUtils.isNotBlank(request.getAreaName())) {
				area.setAreaName(request.getAreaName());
				if (country != null)
					area.setCountry(country);
				if (state != null)
					area.setState(state);
				area.setCity(city);
				areaRepository.save(area);
			}
		}
		Community community = existingBuilding.getCommunity();
		if (StringUtils.isNotBlank(request.getCommunityName())) {
			if (community == null) {
				community = new Community();
				community.setArea(area);
				community.setSubscriber(existingBuilding.getSubscriber());
			}
			community.setCommunityName(request.getCommunityName());
			communityRepository.save(community);
		}

		existingBuilding.setBuildingName(request.getBuildingName());
		existingBuilding.setAddress(request.getAddress());

		if (!ObjectUtils.isEmpty(request.getBuildingLogo())) {
			String contentType = CommonUtil.validateAttachment(request.getBuildingLogo());
			String fileExt = contentType.substring(contentType.indexOf("/") + 1);
			s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.BUILD_MAIN_PIC, contentType, fileExt,
					Base64.getEncoder().encodeToString(request.getBuildingLogo()), null);
			s3UploadObjectDtoList.add(s3BuildingLogoDto);
		}

		existingBuilding.setHasGym(request.getHasGym());
		existingBuilding.setHasSwimpool(request.getHasSwimpool());
		existingBuilding.setHasKidsPlayground(request.getHasKidsPlayground());
		existingBuilding.setHasPlaycourt(request.getHasPlaycourt());
		existingBuilding.setNoOfFloors(request.getNoOfFloors());
		existingBuilding.setNoOfUnits(request.getNoOfunits());
		existingBuilding.setArea(area);
		existingBuilding.setCommunity(community);
		existingBuilding.setLatitude(request.getLatitude());
		existingBuilding.setLongitude(request.getLongitude());

		buildingRepository.save(existingBuilding);

		if (!s3UploadObjectDtoList.isEmpty()) {
			S3UploadDto s3UploadDto = new S3UploadDto();
			s3UploadDto.setSubscriberId(request.getSubscriberId());
			s3UploadDto.setBuildingId(existingBuilding.getBuildingId());
			s3UploadDto.setObjectType(S3UploadObjTypeEnum.BUILDING.toString());
			s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);

			List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
			for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
				if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
					if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BUILD_MAIN_PIC)) {
						existingBuilding.setBuildingLogoFileName(s3UploadObjectDto.getS3FileName());
					}
				}
			}
			buildingRepository.save(existingBuilding);
		}

		BuildingResponse buildingResp = new BuildingResponse();
		BeanUtils.copyProperties(existingBuilding, buildingResp);

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, buildingResp, null, request.getSubscriberId());
	}

	public ApiResponse<?> updateUnit(Integer subscriberId, UnitUpdateRequest request) {
		try {
			Unit existingUnit = unitRepository.findById(request.getUnitId())
					.orElseThrow(() -> new Exception("Unit not found"));

			existingUnit.setUnitName(request.getUnitName());
			existingUnit.setUnitType(request.getUnitType());
			existingUnit.setUnitSubType(request.getUnitSubType());
			existingUnit.setSize(request.getSize());
			existingUnit.setHasBalcony(request.getHasBalcony());
			existingUnit.setRentMonth(request.getRentMonth());
			existingUnit.setRentYear(request.getRentYear());
			existingUnit.setSecurityDeposit(request.getSecurityDeposit());
			existingUnit.setWaterConnNo(request.getWaterConnNo());
			existingUnit.setEbConnNo(request.getEbConnNo());

			unitRepository.save(existingUnit);
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Unit successfully updated", null, null);
		} catch (Exception e) {
			throw new BusinessException("Failed to update unit:", e);
		}
	}

	public ApiResponse<?> updateTenant(Integer subscriberId, TenantUpdateRequest request) {
		try {
			Tenant existingTenant = tenantRepository.findById(request.getTenantId())
					.orElseThrow(() -> new Exception("Tenant not found"));
			

		Countries country=	countriesRepository.findById(request.getNationalityId())
				.orElseThrow(() -> new BusinessException("Invalid Nationality ID",null));

			existingTenant.setFirstName(request.getFirstName());
			existingTenant.setLastName(request.getLastName());
			//existingTenant.setEmail(request.getEmail());
			existingTenant.setPhoneNumber(request.getPhoneNumber());
			existingTenant.setDateOfBirth(request.getDateOfBirth());
			existingTenant.setEmiratesId(request.getEmiratesId());
			existingTenant.setEidaExpiryDate(request.getEidaExpiryDate());
			existingTenant.setPassportNo(request.getPassportNo());
			existingTenant.setPassportExpiryDate(request.getPassportExpiryDate());
			existingTenant.setNationality(country);

			tenantRepository.save(existingTenant);
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Tenant successfully updated", null, null);
		} catch (Exception e) {
			throw new BusinessException("FFailed to update tenant:"+e.getMessage(), e);
		}
	}

	@Override
	public ApiResponse<?> getAllFloors() {
		List<FloorMaster> floors = floorMasterRepository.findAll();
		if (floors.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, floors, null, null);

	}

	@Override
	public ApiResponse<Object> getAllParkingZoneByBuilding(ParkingZoneRequest request) {
		Building existingBuilding = buildingRepository.findByBuildingIdAndSubscriberId(request.getBuildingId(),
				request.getSubscriberId());
		if (existingBuilding == null) {
			throw new BusinessException("Building not found", null);
		}
		List<ParkingZone> listParkingZone = parkingZoneRepository.findAllParkZoneByBuildingId(request.getBuildingId(),
				request.getSubscriberId());

		if (listParkingZone.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		List<ParkingZoneResponse> pzDTOs = listParkingZone.stream().map(pz -> {
			ParkingZoneResponse dto = new ParkingZoneResponse();
			BeanUtils.copyProperties(pz, dto);

			if (pz.getBuilding() != null) {
				dto.setBuildingId(pz.getBuilding().getBuildingId());
				dto.setBuildingName(pz.getBuilding().getBuildingName());
			}

			return dto;
		}).collect(Collectors.toList());
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, pzDTOs, null, null);
	}

	@Override
	public ApiResponse<Object> getAllParkingByBuilding(ParkingRequest request) {
		ParkingZone pz = parkingZoneRepository.findByParkZoneIdAndBuildingIdSubscriberId(request.getParkZoneId(),
				request.getBuildingId(), request.getSubscriberId());
		if (pz == null) {
			throw new BusinessException("ParkingZone or Building or subscriber not associated", null);
		}
		List<Parking> listParking = parkingRepository.findAllParkingByParkZoneIdAndBuildingId(request.getParkZoneId(),
				request.getBuildingId());

		if (listParking.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		List<ParkingDetailResponse> pzDTOs = listParking.stream().map(pk -> {
			ParkingDetailResponse dto = new ParkingDetailResponse();
			BeanUtils.copyProperties(pk, dto);

			if (pk.getBuilding() != null) {
				dto.setBuildingId(pk.getBuilding().getBuildingId());
				dto.setBuildingName(pk.getBuilding().getBuildingName());
			}
			if (pk.getParkZone() != null) {
				dto.setParkZoneId(pk.getParkZone().getParkZoneId());
				dto.setParkZoneName(pk.getParkZone().getParkZoneName());
			}

			return dto;
		}).collect(Collectors.toList());
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, pzDTOs, null, null);
	}

	@Override
	public Subscriptions getActiveSubscriptionDetails(Integer subscriberId) {
		Subscriptions existingSubscription = subscriptionRepository
				.findTopBySubscriber_SubscriberIdOrderBySubscriptionIdDesc(subscriberId);
		if (existingSubscription != null) {
			if (CommonUtil.getCurrentLocalDate().isAfter(existingSubscription.getStartDate().minusDays(1))
					&& CommonUtil.getCurrentLocalDate().isBefore(existingSubscription.getEndDate())) {

				return existingSubscription;
			}
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public ApiResponse<Object> searchUnits(Integer subscriberId, UnitPaginationRequest request) {
		PaginationRequest paginationRequest = request.getPaginationRequest();
		PageRequest pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(),
				Sort.by(paginationRequest.getSortDirection().equalsIgnoreCase("desc") ? Sort.Direction.DESC
						: Sort.Direction.ASC, paginationRequest.getSortBy()));

		Specification<Unit> spec = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.equal(root.get("isDeleted"), false));

			if (subscriberId != null) {
				predicates.add(cb.equal(root.get("subscriber").get("subscriberId"), subscriberId));
			}

			String keyword = request.getSearch();

			if ("vacant".equalsIgnoreCase(keyword)) {
				predicates.add(cb.equal(root.get("unitStatus").get("unitStatusName"), "Vacant"));
			} else if ("occupied".equalsIgnoreCase(keyword)) {
				Subquery<Long> subQuery = query.subquery(Long.class);
				Root<TenantUnit> tenantUnit = subQuery.from(TenantUnit.class);
				subQuery.select(tenantUnit.get("unit").get("unitId")).where(cb.equal(tenantUnit.get("active"), true));
				predicates.add(root.get("unitId").in(subQuery));
			} else if (keyword != null && !keyword.isEmpty()) {
				Predicate byType = cb.like(cb.lower(root.get("unitType").get("unitTypeName")),
						"%" + keyword.toLowerCase() + "%");
				Predicate bySubType = cb.like(cb.lower(root.get("unitSubType").get("unitSubtypeName")),
						"%" + keyword.toLowerCase() + "%");
				predicates.add(cb.or(byType, bySubType));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		Page<Unit> pageResult = unitRepository.findAll(spec, pageable);

		List<UnitAllDTO> dtos = pageResult.getContent().stream().map(unit -> {
			UnitAllDTO dto = new UnitAllDTO();
			dto.setUnitId(unit.getUnitId());
			dto.setUnitName(unit.getUnitName());
			dto.setFloorName(unit.getFloor() != null ? unit.getFloor().getFloorName() : null);
			dto.setUnitTypeName(unit.getUnitType() != null ? unit.getUnitType().getUnitTypeName() : null);
			dto.setUnitSubtypeName(unit.getUnitSubType() != null ? unit.getUnitSubType().getUnitSubtypeName() : null);
			dto.setSize(unit.getSize());
			dto.setHasBalcony(unit.getHasBalcony());
			dto.setUnitStatusName(unit.getUnitStatus() != null ? unit.getUnitStatus().getUnitStatusName() : null);
			dto.setRentMonth(unit.getRentMonth());
			dto.setRentYear(unit.getRentYear());
			dto.setSecurityDeposit(unit.getSecurityDeposit());

			if (unit.getBuilding() != null) {
				dto.setBuildingName(unit.getBuilding().getBuildingName());
				dto.setAddress(unit.getBuilding().getAddress());

				if (unit.getBuilding().getCommunity() != null) {
					dto.setCommunityName(unit.getBuilding().getCommunity().getCommunityName());

					if (unit.getBuilding().getCommunity().getArea() != null) {
						dto.setAreaName(unit.getBuilding().getCommunity().getArea().getAreaName());

						if (unit.getBuilding().getCommunity().getArea().getCity() != null) {
							dto.setCityName(unit.getBuilding().getCommunity().getArea().getCity().getName());
						}

						if (unit.getBuilding().getCommunity().getArea().getState() != null) {
							dto.setStateName(unit.getBuilding().getCommunity().getArea().getState().getName());
						}

						if (unit.getBuilding().getCommunity().getArea().getCountry() != null) {
							dto.setCountryName(unit.getBuilding().getCommunity().getArea().getCountry().getName());
						}
					}
				}
			}

			return dto;
		}).collect(Collectors.toList());

		@SuppressWarnings("rawtypes")
		PaginationResponse pgResp = new PaginationResponse<>(dtos, pageResult.getNumber(), pageResult.getTotalPages(),
				pageResult.getTotalElements(), pageResult.isFirst(), pageResult.isLast());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, pgResp, null, null);
	}

	public ApiResponse<Object> getAllTenants(Integer subscriberId, TenantSearchRequest request) {
		PaginationRequest paginationRequest = request.getPaginationRequest();
		PageRequest pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(),
				Sort.by(paginationRequest.getSortDirection().equalsIgnoreCase("desc") ? Sort.Direction.DESC
						: Sort.Direction.ASC, paginationRequest.getSortBy()));
		String searchTerm = request.getSearch();

		Specification<Tenant> spec =(root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Ensure we fetch distinct tenants to avoid duplicates
            query.distinct(true);

            // Join tenantUnits and unit to check for occupancy
            Join<Tenant, TenantUnit> tenantUnitJoin = root.join("tenantUnits", JoinType.LEFT);
            Join<TenantUnit, Unit> unitJoin = tenantUnitJoin.join("unit", JoinType.LEFT);

            // Required filters
            predicates.add(cb.equal(root.get("isDeleted"), false));
            predicates.add(cb.equal(root.get("subscriber").get("subscriberId"), subscriberId));
           // predicates.add(cb.equal(tenantUnitJoin.get("active"), true));
            //predicates.add(cb.equal(tenantUnitJoin.get("expired"), false));

            if (searchTerm != null && !searchTerm.isBlank()) {
                String likeSearch = "%" + searchTerm.toLowerCase() + "%";
                Predicate orPredicate = cb.or(
                    cb.like(cb.lower(root.get("firstName")), likeSearch),
                    cb.like(cb.lower(root.get("lastName")), likeSearch),
                    cb.like(cb.lower(root.get("email")), likeSearch),
                    cb.like(cb.lower(root.get("phoneNumber")), likeSearch),
                    // Assuming emiratesId is stored as string or you want to allow numeric match
                    cb.like(cb.function("str", String.class, root.get("emiratesId")), likeSearch)

                );
                predicates.add(orPredicate);
            }
            
            
         // Status filter logic
        /*	if (request.getStatus() != null) {
        		String status = request.getStatus();
        		Date now = new Date();

        		switch (status) {
        			case CommonConstants.ACTIVE:
        				predicates.add(cb.isTrue(tenantUnitJoin.get("active")));
        				predicates.add(cb.or(
        					cb.isFalse(tenantUnitJoin.get("expired")),
        					cb.isNull(tenantUnitJoin.get("expired"))
        				));
        				break;

        			case CommonConstants.FUTURE:
        				predicates.add(cb.greaterThan(
        					tenantUnitJoin.get("tenureDetails").get("startDate"), now));
        				break;

        			case CommonConstants.EX_TENANT:
        				predicates.add(cb.isTrue(tenantUnitJoin.get("expired")));
        				break;
        		}
        	}*/
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    

		Page<Tenant> pageResult = tenantRepository.findAll(spec, pageable);

		List<TenantDTO> dtos = pageResult.getContent().stream().map(tenant -> {
			TenantDTO dto = new TenantDTO();
			dto.setTenantId(tenant.getTenantId());
			dto.setFirstName(tenant.getFirstName());
			dto.setLastName(tenant.getLastName());
			dto.setEmail(tenant.getEmail());
			dto.setPhoneNumber(tenant.getPhoneNumber());
			dto.setDateOfBirth(tenant.getDateOfBirth());
			dto.setEmiratesId(tenant.getEmiratesId());
			dto.setEidaExpiryDate(tenant.getEidaExpiryDate());
			//dto.setEidaCopyFilename(tenant.getEidaCopyFilename());
			dto.setPassportNo(tenant.getPassportNo());
			dto.setPassportExpiryDate(tenant.getPassportExpiryDate());
			//dto.setPassportCopyFilename(tenant.getPassportCopyFilename());
			//dto.setPhotoFilename(tenant.getPhotoFilename());
			dto.setNationalityId(tenant.getNationality().getCountryId());
			dto.setNationality(tenant.getNationality().getName());
			if (tenant.getNationality() != null) {
				dto.setNationality(tenant.getNationality().getName());
			}
			dto.setCreatedTime(tenant.getCreatedTime());
			dto.setUpdatedTime(tenant.getUpdatedTime());
			dto.setCreatedBy(tenant.getCreatedBy());
			dto.setUpdatedBy(tenant.getUpdatedBy());
			
			if (StringUtils.isNotBlank(tenant.getEidaCopyFilename())) {
				dto.setEidaCopyFileLink(s3Service.generatePresignedUrl(subscriberId, null, null,
						tenant.getEidaCopyFilename()));
			}
			if (StringUtils.isNotBlank(tenant.getPassportCopyFilename())) {
				dto.setPassportCopyFileLink(s3Service.generatePresignedUrl(subscriberId, null, null,
						tenant.getPassportCopyFilename()));
			}
			if (StringUtils.isNotBlank(tenant.getPhotoFilename())) {
				dto.setPhotoFileLink(s3Service.generatePresignedUrl(subscriberId, null, null,
						tenant.getPhotoFilename()));
			}
			
			dto.setTenantStatus(tenant.getTenantStatus());
			if (StringUtils.isBlank(tenant.getTenantStatus())) {
				dto.setTenantStatus(CommonConstants.TENANT_IN_ACTIVE);
			}
			
			if(tenant.getTenantUnits()!=null && tenant.getTenantUnits().size()>1 ) {
				
				List<TenantUnitDTO> tenantUnitsDtolist=new ArrayList<>();
				List <TenantUnit> tenantUnitList=tenant.getTenantUnits();
				for(TenantUnit tu:tenantUnitList) {
					TenantUnitDTO tuDto=new TenantUnitDTO();
					if(tu!=null) {
						tuDto.setTenantUnitId(tu.getTenantUnitId());
						tuDto.setUnitId(tu.getUnit().getUnitId());
						tuDto.setTenurePeriodMonth(tu.getTenurePeriodMonth());
						if(tu.getTenureDetails()!=null) {
							tuDto.setTenancyStartDate(CommonUtil.getStringDatefromDate(tu.getTenureDetails().getTenancyStartDate()));	
							tuDto.setTenancyEndDate(CommonUtil.getStringDatefromDate(tu.getTenureDetails().getTenancyEndDate()));
							
						}
						tuDto.setActive(tu.getActive());
						tuDto.setExpired(tu.getExpired());
						
						
						
					}
					
					tenantUnitsDtolist.add(tuDto);
				}
				dto.setTenantUnits(tenantUnitsDtolist);
				
			}
			dto.setNationality(tenant.getNationality().getName());
			//dto.setNationalityId(tenant.ge)
			
			return dto;
		}).collect(Collectors.toList());

		@SuppressWarnings("rawtypes")
		PaginationResponse pgResp = new PaginationResponse<>(dtos, pageResult.getNumber(), pageResult.getTotalPages(),
				pageResult.getTotalElements(), pageResult.isFirst(), pageResult.isLast());

		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, pgResp, null, null);
	}
	
	
	
	
	@Override
    public ApiResponse<Object> getAllBedspaceCategories() {
		
		List<BedspaceCatMaster> bedspaceMas = bedspaceCatRepository.findAll();
		if (bedspaceMas.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, bedspaceMas, null, null);
     
    }

    @Override
    public ApiResponse<Object> getAllBedspaceBathroomTypes() {
        
        List<BedspaceBathroomTypeMaster> bathTypeMas = bedspaceBathroomTypeRepository.findAll();
		if (bathTypeMas.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, bathTypeMas, null, null);
    }

    @Override
    public ApiResponse<Object>getAllBedspacePartitions() {
    
    
      List<BedspacePartitionMaster> partitionMas = bedspacePartitionRepository.findAll();
		if (partitionMas.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, partitionMas, null, null);
        
    }

	@Override
	public ApiResponse<Object> createBedspace(Integer subscriberId, BedspaceRequest dto)throws Exception {
		BedspaceResponse bedspaceResp ;
		S3UploadObjectDto s3MainPicDto = null;
		S3UploadObjectDto s3Pic2Dto = null;
		S3UploadObjectDto s3Pic3Dto = null;
		S3UploadObjectDto s3Pic4Dto = null;
		S3UploadObjectDto s3Pic5Dto = null;
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
			
			Bedspace bedspace = new Bedspace();
		    bedspace.setBedspaceName(dto.getBedspaceName());
		    bedspace.setSecurityDeposit(dto.getSecurityDeposit());
		    bedspace.setRentMonth(dto.getRentMonth());
		    bedspace.setRentDay(dto.getRentDay());
		   // bedspace.setHasWardrobe(dto.getHasWardrobe());
		   // bedspace.setHasKitchen(dto.getHasKitchen());
		    bedspace.setFeatures(dto.getFeatures());
		    	
		    // fetch and set references
		    
		    Unit unit = unitRepository.findByUnitIdAndSubscriberId(dto.getUnitId(), subscriberId);  
			if (unit==null) {
				throw new BusinessException("Unit not found with id: " + dto.getUnitId(), null);
			}
			
			bedspace.setSubscriber(unit.getSubscriber());	
			bedspace.setUnit(unit);
		    bedspace.setPartition(bedspacePartitionRepository.findById(dto.getPartitionId()).orElseThrow(() -> new BusinessException("Bedspace Partition not found", null)));
		    bedspace.setBedspaceCategory(bedspaceCatRepository.findById(dto.getBedspaceCatId()).orElseThrow(() -> new BusinessException("Bedsapace category not found", null)));
		    bedspace.setBathroomType(bedspaceBathroomTypeRepository.findById(dto.getBedspaceBathroomTypeId()).orElseThrow(() -> new BusinessException("Bedsapace bathroom not found", null)));
		    
		    
		    if (!ObjectUtils.isEmpty(dto.getBsMainPic1())) {
				String contentType = CommonUtil.validateAttachment(dto.getBsMainPic1());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3MainPicDto = new S3UploadObjectDto(CommonConstants.BS_MAIN_PIC, contentType, fileExt,
						Base64.getEncoder().encodeToString(dto.getBsMainPic1()), null);
				s3UploadObjectDtoList.add(s3MainPicDto);
			}
			if (!ObjectUtils.isEmpty(dto.getBsPic2())) {
				String contentType = CommonUtil.validateAttachment(dto.getBsPic2());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3Pic2Dto = new S3UploadObjectDto(CommonConstants.BS_PIC2, contentType, fileExt,
						Base64.getEncoder().encodeToString(dto.getBsPic2()), null);
				s3UploadObjectDtoList.add(s3Pic2Dto);
			}
			if (!ObjectUtils.isEmpty(dto.getBsPic3())) {
				String contentType = CommonUtil.validateAttachment(dto.getBsPic3());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3Pic3Dto = new S3UploadObjectDto(CommonConstants.BS_PIC3, contentType, fileExt,
						Base64.getEncoder().encodeToString(dto.getBsPic3()), null);
				s3UploadObjectDtoList.add(s3Pic3Dto);
			}
			if (!ObjectUtils.isEmpty(dto.getBsPic4())) {
				String contentType = CommonUtil.validateAttachment(dto.getBsPic4());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3Pic4Dto = new S3UploadObjectDto(CommonConstants.BS_PIC4, contentType, fileExt,
						Base64.getEncoder().encodeToString(dto.getBsPic4()), null);
				s3UploadObjectDtoList.add(s3Pic4Dto);
			}
			if (!ObjectUtils.isEmpty(dto.getBsPic5())) {
				String contentType = CommonUtil.validateAttachment(dto.getBsPic5());
				String fileExt = contentType.substring(contentType.indexOf("/") + 1);
				s3Pic5Dto = new S3UploadObjectDto(CommonConstants.BS_PIC5, contentType, fileExt,
						Base64.getEncoder().encodeToString(dto.getBsPic5()), null);
				s3UploadObjectDtoList.add(s3Pic5Dto);
			}
		    
		    try {
		    	bedspaceRepository.save(bedspace);
		    	
		    	S3UploadDto s3UploadDto = new S3UploadDto();
				s3UploadDto.setSubscriberId(subscriberId);
				s3UploadDto.setBsId(bedspace.getBedspaceId());
				s3UploadDto.setBuildingId(unit.getBuilding().getBuildingId());
				s3UploadDto.setUnitId(unit.getUnitId());
				s3UploadDto.setObjectType(S3UploadObjTypeEnum.BS.toString());
				s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);

				List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
				for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
					if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_MAIN_PIC)) {
							bedspace.setBsMainPic1Name(s3UploadObjectDto.getS3FileName());
						}
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_PIC2)) {
							bedspace.setBsPic2Name(s3UploadObjectDto.getS3FileName());
						}
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_PIC3)) {
							bedspace.setBsPic3Name(s3UploadObjectDto.getS3FileName());
						}
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_PIC4)) {
							bedspace.setBsPic4Name(s3UploadObjectDto.getS3FileName());
						}
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_PIC5)) {
							bedspace.setBsPic5Name(s3UploadObjectDto.getS3FileName());
						}
					}
				}

		    
		    	bedspaceResp = new BedspaceResponse();
		    	BeanUtils.copyProperties(bedspace, bedspaceResp);

		} catch (Exception e) {
			throw new BusinessException("addBedspace failed :"+e.getMessage(), e);
		}

	return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, bedspaceResp, null, dto.getSubscriberId());
		
	}

	@Override
	public ApiResponse<?> updateBedspace(Integer subscriberId, BedspaceRequest request) {
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;
		S3UploadObjectDto s3MainPicDto = null;
		S3UploadObjectDto s3Pic2Dto = null;
		S3UploadObjectDto s3Pic3Dto = null;
		S3UploadObjectDto s3Pic4Dto = null;
		S3UploadObjectDto s3Pic5Dto = null;
		try {
			Bedspace existingBs = bedspaceRepository.findByIdAndSubscriberId(request.getBedspaceId(),subscriberId)
					.orElseThrow(() -> new BusinessException("Bedspace not found",null));

			existingBs.setBedspaceName(request.getBedspaceName());
			existingBs.setSecurityDeposit(request.getSecurityDeposit());
			existingBs.setRentMonth(request.getRentMonth());
			existingBs.setRentDay(request.getRentDay());
			//existingBs.setHasWardrobe(request.getHasWardrobe());
			//existingBs.setHasKitchen(request.getHasKitchen());
			existingBs.setFeatures(request.getFeatures());
			
			Unit unit = unitRepository.findByUnitIdAndSubscriberId(request.getUnitId(), subscriberId);  
			if (unit==null) {
				throw new BusinessException("Unit not found with id: " + request.getUnitId(), null);
			}
				
			existingBs.setSubscriber(unit.getSubscriber());	
			existingBs.setUnit(unit);
			existingBs.setPartition(bedspacePartitionRepository.findById(request.getPartitionId()).orElseThrow(() -> new BusinessException("Bedspace Partition not found", null)));
			existingBs.setBedspaceCategory(bedspaceCatRepository.findById(request.getBedspaceCatId()).orElseThrow(() -> new BusinessException("Bedsapace category not found", null)));
			existingBs.setBathroomType(bedspaceBathroomTypeRepository.findById(request.getBedspaceBathroomTypeId()).orElseThrow(() -> new BusinessException("Bedsapace bathroom not found", null)));
			
			 if (!ObjectUtils.isEmpty(request.getBsMainPic1())) {
					String contentType = CommonUtil.validateAttachment(request.getBsMainPic1());
					String fileExt = contentType.substring(contentType.indexOf("/") + 1);
					s3MainPicDto = new S3UploadObjectDto(CommonConstants.BS_MAIN_PIC, contentType, fileExt,
							Base64.getEncoder().encodeToString(request.getBsMainPic1()), null);
					s3UploadObjectDtoList.add(s3MainPicDto);
				}
				if (!ObjectUtils.isEmpty(request.getBsPic2())) {
					String contentType = CommonUtil.validateAttachment(request.getBsPic2());
					String fileExt = contentType.substring(contentType.indexOf("/") + 1);
					s3Pic2Dto = new S3UploadObjectDto(CommonConstants.BS_PIC2, contentType, fileExt,
							Base64.getEncoder().encodeToString(request.getBsPic2()), null);
					s3UploadObjectDtoList.add(s3Pic2Dto);
				}
				if (!ObjectUtils.isEmpty(request.getBsPic3())) {
					String contentType = CommonUtil.validateAttachment(request.getBsPic3());
					String fileExt = contentType.substring(contentType.indexOf("/") + 1);
					s3Pic3Dto = new S3UploadObjectDto(CommonConstants.BS_PIC3, contentType, fileExt,
							Base64.getEncoder().encodeToString(request.getBsPic3()), null);
					s3UploadObjectDtoList.add(s3Pic3Dto);
				}
				if (!ObjectUtils.isEmpty(request.getBsPic4())) {
					String contentType = CommonUtil.validateAttachment(request.getBsPic4());
					String fileExt = contentType.substring(contentType.indexOf("/") + 1);
					s3Pic4Dto = new S3UploadObjectDto(CommonConstants.BS_PIC4, contentType, fileExt,
							Base64.getEncoder().encodeToString(request.getBsPic4()), null);
					s3UploadObjectDtoList.add(s3Pic4Dto);
				}
				if (!ObjectUtils.isEmpty(request.getBsPic5())) {
					String contentType = CommonUtil.validateAttachment(request.getBsPic5());
					String fileExt = contentType.substring(contentType.indexOf("/") + 1);
					s3Pic5Dto = new S3UploadObjectDto(CommonConstants.BS_PIC5, contentType, fileExt,
							Base64.getEncoder().encodeToString(request.getBsPic5()), null);
					s3UploadObjectDtoList.add(s3Pic5Dto);
				}
			
			bedspaceRepository.save(existingBs);
			if (!s3UploadObjectDtoList.isEmpty()) {
				S3UploadDto s3UploadDto = new S3UploadDto();
				s3UploadDto.setSubscriberId(request.getSubscriberId());
				s3UploadDto.setBuildingId(existingBs.getUnit().getBuilding().getBuildingId());
				s3UploadDto.setUnitId(existingBs.getUnit().getUnitId());
				s3UploadDto.setBsId(existingBs.getBedspaceId());
				s3UploadDto.setObjectType(S3UploadObjTypeEnum.BS.toString());
				s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);

				List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
				for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
					if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_MAIN_PIC)) {
							existingBs.setBsMainPic1Name(s3UploadObjectDto.getS3FileName());;
						}
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_PIC2)) {
							existingBs.setBsPic2Name(s3UploadObjectDto.getS3FileName());
						}
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_PIC3)) {
							existingBs.setBsPic3Name(s3UploadObjectDto.getS3FileName());
						}
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_PIC4)) {
							existingBs.setBsPic4Name(s3UploadObjectDto.getS3FileName());
						}
						if (s3UploadObjectDto.getObjectName().equals(CommonConstants.BS_PIC5)) {
							existingBs.setBsPic5Name(s3UploadObjectDto.getS3FileName());
						}
					}
				}
				bedspaceRepository.save(existingBs);
			}

			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Bedspace successfully updated", null, null);
		} catch (Exception e) {
			throw new BusinessException("Failed to update unit:"+e.getMessage(), e);
		}
	}
	
	 @Override
	 @Transactional(readOnly = true)
	 public ApiResponse<Object> searchBedspaces(BedspaceSearchCriteria criteria) {
		 //Page<BedspaceDTO>
		 PaginationRequest paginationRequest = criteria.getPaginationRequest();
		 paginationRequest.setSortBy("bedspaceId");
			Sort sort = paginationRequest.getSortDirection().equalsIgnoreCase("desc")
					? Sort.by(paginationRequest.getSortBy()).descending()
					: Sort.by(paginationRequest.getSortBy()).ascending();
			PageRequest pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), sort);
			
		
		 
		 Page<Bedspace> pageResult =   bedspaceRepository.findAll(BedspaceSpecifications.matchesCriteria(criteria),pageable);
	                
		 
		 List<BedspaceDTO> dtos = pageResult.getContent().stream().map(bedspace -> {
			 
				return new BedspaceDTO(
						bedspace.getBedspaceId(), 
						bedspace.getBedspaceName(),
						bedspace.getFeatures(),
						bedspace.getSecurityDeposit(),
						bedspace.getRentMonth(),
						bedspace.getRentDay(),
						//bedspace.getHasWardrobe(),
						//bedspace.getHasKitchen(),
						bedspace.getUnit().getUnitId(),
						bedspace.getPartition().getBedspacePartitionId(),
						bedspace.getPartition().getBedspacePartitionName(),
						bedspace.getBedspaceCategory().getBedspaceCatId(),
						bedspace.getBedspaceCategory().getBedspaceCatName(),
						bedspace.getBathroomType().getBedspaceBathroomTypeId(),
						bedspace.getBathroomType().getBedspaceBathroomTypeName(),
						bedspace.getStatus(), null
				);
			}).collect(Collectors.toList());

			@SuppressWarnings("rawtypes")
			PaginationResponse pgResp = new PaginationResponse<>(dtos, pageResult.getNumber(), pageResult.getTotalPages(),
					pageResult.getTotalElements(), pageResult.isFirst(), pageResult.isLast());

			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, pgResp, null, null);
	 }

	

	
	
}