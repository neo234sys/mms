package com.sbmtech.mms.service.impl;

import static com.sbmtech.mms.constant.CommonConstants.DATE_ddMMyyyy;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_CODE;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_DESC;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_CODE;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_DESC;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.constant.CommonConstants;
import com.sbmtech.mms.dto.ChequeDetailsDTO;
import com.sbmtech.mms.dto.RentDue;
import com.sbmtech.mms.dto.S3UploadDto;
import com.sbmtech.mms.dto.S3UploadObjectDto;
import com.sbmtech.mms.exception.BusinessException;
import com.sbmtech.mms.models.ParkingZone;
import com.sbmtech.mms.models.PaymentPurpose;
import com.sbmtech.mms.models.RentCycle;
import com.sbmtech.mms.models.S3UploadObjTypeEnum;
import com.sbmtech.mms.models.Subscriber;
import com.sbmtech.mms.models.Tenant;
import com.sbmtech.mms.models.TenantCCDetails;
import com.sbmtech.mms.models.TenantChequeDetails;
import com.sbmtech.mms.models.TenantUnit;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.models.UnitStatus;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.PaymentScheduleRequest;
import com.sbmtech.mms.payload.request.SavePaymentDetailsRequest;
import com.sbmtech.mms.repository.PaymentPurposeRepository;
import com.sbmtech.mms.repository.RentCycleRepository;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.TenantCCDetailsRepository;
import com.sbmtech.mms.repository.TenantChequeDetailsRepository;
import com.sbmtech.mms.repository.TenantUnitRepository;
import com.sbmtech.mms.repository.UnitRepository;
import com.sbmtech.mms.repository.UnitStatusRepository;
import com.sbmtech.mms.service.PaymentService;
import com.sbmtech.mms.service.S3Service;
import com.sbmtech.mms.util.CommonUtil;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentPurposeRepository paymentPurposeRepository;
	
	@Autowired
	private SubscriberRepository subscriberRepository;

	@Autowired
	private TenantCCDetailsRepository tenantCCDetailsRepository;

	@Autowired
	private TenantChequeDetailsRepository tenantChequeDetailsRepository;

	@Autowired
	private TenantUnitRepository tenantUnitRepository;

	@Autowired
	private UnitStatusRepository unitStatusRepository;

	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	private RentCycleRepository rentCycleRepository;
	
	@Autowired
	private S3Service s3Service;
	
	
	@Override
	public ApiResponse<List<PaymentPurpose>> getAllPaymentPurposes() throws Exception {
		List<PaymentPurpose> result = paymentPurposeRepository.findAll();
		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, result, null, null);

	}

	@Override
	public ApiResponse<Object> savePaymentDetails(SavePaymentDetailsRequest request)throws Exception {
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;
		logger.info("Received request to save payment details for tenantId: {}, tenantUnitId: {}, paymentModeId: {}",
				request.getTenantId(), request.getTenantUnitId(), request.getPaymentModeId());
		Integer  validTenantUnitId= tenantUnitRepository.isValidTenantUnitIdWithActiveSubscriber(request.getTenantUnitId(),request.getSubscriberId());
		if (validTenantUnitId==null || (validTenantUnitId==0)) {
			logger.error("Tenant unit not found for tenantUnitId: {}", request.getTenantUnitId());
			throw new BusinessException("Tenant unit not found", null);
		}
		Optional<TenantUnit> tenantUnitOpt = tenantUnitRepository.findById(request.getTenantUnitId());
		//
		if (tenantUnitOpt.isEmpty()) {
			logger.error("Tenant unit not found for tenantUnitId: {}", request.getTenantUnitId());
			throw new BusinessException("Tenant unit not found", null);
		}

		TenantUnit tenantUnit = tenantUnitOpt.get();

		Tenant tenant = tenantUnit.getTenant();
		if (tenant == null || Boolean.TRUE.equals(tenant.getIsDeleted())) {
			logger.error("Tenant is either deleted or not found for tenantUnitId: {}", request.getTenantUnitId());
			throw new BusinessException("Tenant is either deleted or does not exist", null);
		}

		Unit unit = tenantUnit.getUnit();
		if (unit == null) {
			logger.error("Unit not found for tenantUnitId: {}", request.getTenantUnitId());
			throw new BusinessException("Unit not found", null);
		}

		Integer mode = request.getPaymentModeId();

		try {
			if (mode == 1) { // Credit Card
				if (request.getCcDetails() == null) {
					logger.error("Credit card details are missing for tenantId: {}", request.getTenantId());
					throw new BusinessException("Credit card details are missing", null);
				}

				TenantCCDetails cc = new TenantCCDetails();
				cc.setTenantId(request.getTenantId());
				cc.setTenantUnitId(request.getTenantUnitId());
				cc.setCcName(request.getCcDetails().getCcName());
				cc.setCcNo(request.getCcDetails().getCcNo());
				cc.setCcCvcNo(request.getCcDetails().getCcCvcNo());
				cc.setCcExpiry(request.getCcDetails().getCcExpiry());

				tenantCCDetailsRepository.save(cc);
				logger.info("Credit card details saved successfully for tenantId: {}", request.getTenantId());

			} else if (mode == 4) { // Cheque
				if (request.getChequeDetails() == null || request.getChequeDetails().isEmpty()) {
					logger.error("Cheque details are missing for tenantId: {}", request.getTenantId());
					throw new BusinessException("Cheque details are missing", null);
				}

				for (ChequeDetailsDTO detail : request.getChequeDetails()) {
					
					
					TenantChequeDetails cheque = new TenantChequeDetails();
					cheque.setTenantId(request.getTenantId());
					cheque.setTenantUnitId(request.getTenantUnitId());
					cheque.setChequeNo(detail.getChequeNo());
					cheque.setChequeBank(detail.getChequeBank());
					cheque.setChequeAmount(detail.getChequeAmount());
					
					cheque.setChequeDate(CommonUtil.getLocalDatefromString(detail.getChequeDate(), DATE_ddMMyyyy));
					cheque.setPaymentPurposeId(detail.getPaymentPurposeId());
					
					if (!ObjectUtils.isEmpty(detail.getChequePic())) {
						String contentType = CommonUtil.validateAttachment(detail.getChequePic());
						String fileExt = contentType.substring(contentType.indexOf("/") + 1);
						s3BuildingLogoDto = new S3UploadObjectDto(CommonConstants.CHEQUE_PIC, contentType, fileExt,
								Base64.getEncoder().encodeToString(detail.getChequePic()), null);
						s3UploadObjectDtoList.add(s3BuildingLogoDto);

						S3UploadDto s3UploadDto = new S3UploadDto();
						s3UploadDto.setSubscriberId(request.getSubscriberId());
						s3UploadDto.setObjectType(S3UploadObjTypeEnum.CHEQUE.toString());
						s3UploadDto.setBuildingId(unit.getBuilding().getBuildingId());
						s3UploadDto.setUnitId(unit.getUnitId());
						s3UploadDto.setS3UploadObjectDtoList(s3UploadObjectDtoList);
						List<S3UploadObjectDto> s3UploadObjectDtoListRet = s3Service.upload(s3UploadDto);
						for (S3UploadObjectDto s3UploadObjectDto : s3UploadObjectDtoListRet) {
							if (s3UploadObjectDto != null && StringUtils.isNotBlank(s3UploadObjectDto.getS3FileName())) {
								if (s3UploadObjectDto.getObjectName().equals(CommonConstants.CHEQUE_PIC)) {
									
									cheque.setChequePicName(s3UploadObjectDto.getS3FileName());
								}

							}
						}

					}

					tenantChequeDetailsRepository.save(cheque);
					logger.info("Cheque details saved for chequeNo: {} and tenantId: {}", detail.getChequeNo(),
							request.getTenantId());
				}

			} else {
				logger.error("Invalid payment mode or missing details for tenantId: {}", request.getTenantId());
				throw new BusinessException("Invalid payment mode or missing details", null);
			}

			UnitStatus updatedStatus = unitStatusRepository.findById(2)
					.orElseThrow(() -> new BusinessException("Unit status ID 2 not found", null));
			unit.setUnitStatus(updatedStatus);
			unitRepository.save(unit);
			logger.info("Unit status updated to 2 for unitId: {}", unit.getUnitId());

			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Payment details saved successfully", null, null);

		} catch (Exception e) {
			logger.error("Exception while saving payment details: {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public ApiResponse<Object> calculatePaymentSchedule(@Valid PaymentScheduleRequest request) {

		
		TenantUnit tu = tenantUnitRepository.findByTenantUnitIdIdAndSubscriberId(request.getTenantUnitId(),
				 request.getSubscriberId());
		if (tu == null) {
			throw new BusinessException("TenantUnit or subscriber not associated", null);
		}
		  List<RentDue> dues = this.calculateDueDates(CommonUtil.getLocalDatefromDate(tu.getTenureDetails().get(0).getTenancyStartDate()),
				tu.getTenurePeriodMonth(),tu.getRentCycle().getRentCycleId(),tu.getTenureDetails().get(0).getTotalRentPerYear());
		//return null;
		  return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, dues, null, null);
	}
	
	public List<RentDue> calculateDueDates(LocalDate startDate, int tenureMonths, int rentCycleId,double totalRentPerYear) {
		Optional <RentCycle> rentCycleOp=rentCycleRepository.findById(rentCycleId);
		  Period interval =Period.ofMonths(0);
		if(rentCycleOp.isPresent()) {
	        RentCycle rentCycle = rentCycleOp.get();
	        System.out.println(rentCycle.getRentCycleName().toLowerCase());
	        if(rentCycle.getRentCycleName().toLowerCase().contains("year")) {
	        	interval=Period.ofMonths(12);
	        }else if(rentCycle.getRentCycleName().toLowerCase().contains("half")) {
	        	interval= Period.ofMonths(6);
	        }else if(rentCycle.getRentCycleName().toLowerCase().contains("quar")) {
	        	interval= Period.ofMonths(3);
	        }else if(rentCycle.getRentCycleName().toLowerCase().contains("month")) {
	        	interval=Period.ofMonths(1);
	        }
		}
        
        long totalCycles = tenureMonths / interval.toTotalMonths();
        double rentPerCycle = totalRentPerYear * ((double) interval.toTotalMonths() / 12.0);

        //List<LocalDate> dueDates = new ArrayList<>();
        List<RentDue> rentDues = new ArrayList<>();
        for (int i = 0; i < totalCycles; i++) {
           // dueDates.add(startDate.plus(interval.multipliedBy(i)));
        	 LocalDate dueDate = startDate.plus(interval.multipliedBy(i));
             rentDues.add(new RentDue(dueDate, rentPerCycle));
        }

      System.out.println("dueDates="+ rentDues);
        
        return rentDues;
    }

}
