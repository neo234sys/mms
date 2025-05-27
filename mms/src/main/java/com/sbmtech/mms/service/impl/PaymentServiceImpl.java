package com.sbmtech.mms.service.impl;

import static com.sbmtech.mms.constant.CommonConstants.DATE_ddMMyyyy;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_CODE;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_DESC;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_CODE;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_DESC;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmtech.mms.constant.CommonConstants;
import com.sbmtech.mms.dto.ChequeDetailsDTO;
import com.sbmtech.mms.dto.RentDue;
import com.sbmtech.mms.dto.RentDueEntryDTO;
import com.sbmtech.mms.dto.S3UploadDto;
import com.sbmtech.mms.dto.S3UploadObjectDto;
import com.sbmtech.mms.exception.BusinessException;
import com.sbmtech.mms.models.OrderStatusEnum;
import com.sbmtech.mms.models.PaymentMode;
import com.sbmtech.mms.models.PaymentModeEnum;
import com.sbmtech.mms.models.PaymentOrderEntity;
import com.sbmtech.mms.models.PaymentPurpose;
import com.sbmtech.mms.models.PaymentPurposeEnum;
import com.sbmtech.mms.models.RentCycle;
import com.sbmtech.mms.models.RentDueEntity;
import com.sbmtech.mms.models.S3UploadObjTypeEnum;
import com.sbmtech.mms.models.Tenant;
import com.sbmtech.mms.models.TenantCCDetails;
import com.sbmtech.mms.models.TenantChequeDetails;
import com.sbmtech.mms.models.TenantUnit;
import com.sbmtech.mms.models.TenureDetails;
import com.sbmtech.mms.models.TransactionEntity;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.models.UnitReserveDetails;
import com.sbmtech.mms.models.UnitStatus;
import com.sbmtech.mms.models.UnitStatusEnum;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.OrderRentRequest;
import com.sbmtech.mms.payload.request.OrderReservationRequest;
import com.sbmtech.mms.payload.request.PaymentScheduleRequest;
import com.sbmtech.mms.payload.request.RentDuePaymentModeRequest;
import com.sbmtech.mms.payload.request.SavePaymentDetailsRequest;
import com.sbmtech.mms.payload.request.TransactionRequest;
import com.sbmtech.mms.payload.response.PaymentOrderResponse;
import com.sbmtech.mms.payload.response.TenantUnitResponse;
import com.sbmtech.mms.payload.response.TransactionResponse;
import com.sbmtech.mms.repository.PaymentModeRepository;
import com.sbmtech.mms.repository.PaymentOrderRepository;
import com.sbmtech.mms.repository.PaymentPurposeRepository;
import com.sbmtech.mms.repository.RentCycleRepository;
import com.sbmtech.mms.repository.RentDueRepository;
import com.sbmtech.mms.repository.SubscriberRepository;
import com.sbmtech.mms.repository.TenantCCDetailsRepository;
import com.sbmtech.mms.repository.TenantChequeDetailsRepository;
import com.sbmtech.mms.repository.TenantUnitRepository;
import com.sbmtech.mms.repository.TenureDetailsRepository;
import com.sbmtech.mms.repository.TransactionRepository;
import com.sbmtech.mms.repository.UnitRepository;
import com.sbmtech.mms.repository.UnitReserveDetailsRepository;
import com.sbmtech.mms.repository.UnitStatusRepository;
import com.sbmtech.mms.service.PaymentOrderService;
import com.sbmtech.mms.service.PaymentService;
import com.sbmtech.mms.service.S3Service;
import com.sbmtech.mms.util.CommonUtil;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);

	
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	
	@Autowired
	private PaymentOrderService paymentOrderService;

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
	private UnitReserveDetailsRepository unitReserveDetailsRepository;
	
	@Autowired
	private S3Service s3Service;
	
	
	@Autowired
	private TenureDetailsRepository tenureDetailsRepository;

	@Autowired
	private RentDueRepository rentDueRepository;
	
	@Autowired
	PaymentPurposeRepository paymentPurposeRepository;
	
	@Autowired
	private PaymentModeRepository paymentModeRepository;
	
	@Autowired
	PaymentOrderRepository paymentOrderRepository;
	
	@Autowired
    private TransactionRepository transactionRepository;
	
	
	
	
	
	
	@Override
	public ApiResponse<List<PaymentPurpose>> getAllPaymentPurposes() throws Exception {
		List<PaymentPurpose> result = paymentPurposeRepository.findAll();
		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, result, null, null);

	}



	@Override
	public ApiResponse<Object> createPaymentSchedule(@Valid PaymentScheduleRequest request) {

		
		TenantUnit tu = tenantUnitRepository.findByTenantUnitIdIdAndSubscriberId(request.getTenantUnitId(),
				 request.getSubscriberId());
		if (tu == null) {
			throw new BusinessException("TenantUnit or subscriber not associated", null);
		}
		  List<RentDue> dues = this.calculateDueDates(tu.getTenureDetails(),
				tu.getTenurePeriodMonth(),tu.getRentCycle().getRentCycleId(),tu.getTenureDetails().getTotalRentPerYear());
		  
		  List <RentDueEntity> list= this.saveRentDues(tu.getTenureDetails().getTenantTenureId(),dues);
		  dues = list.stream()
		           .map(entity -> {
		               RentDue due = new RentDue();
		               due.setRentDueId(entity.getRentDueId());
		               due.setTenureId(entity.getTenure().getTenantTenureId());
		               due.setAmount(entity.getAmount());
		               due.setDueDate(entity.getDueDate());
		               due.setPaymentPurposeId(entity.getPaymentPurpose().getPurposeId());
		               due.setPaymentPurposeName(entity.getPaymentPurpose().getPurposeName());
		               
		               
		               return due;
		           })
		           .collect(Collectors.toList());
		  return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, dues, null, null);
	}
	
	public List<RentDue> calculateDueDates(TenureDetails tenureDetails, int tenureMonths, int rentCycleId,double totalRentPerYear) {
		List<PaymentPurpose> paymentPurpose = paymentPurposeRepository.findAll();
		LocalDate startDate=CommonUtil.getLocalDatefromDate(tenureDetails.getTenancyStartDate());
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
        rentDues.add(new RentDue(startDate, tenureDetails.getSecurityDeposit(),PaymentPurposeEnum.SECURITY_DEPOSIT.getValue(),PaymentPurposeEnum.SECURITY_DEPOSIT.getName()));
        for (int i = 0; i < totalCycles; i++) {
           // dueDates.add(startDate.plus(interval.multipliedBy(i)));
        	 LocalDate dueDate = startDate.plus(interval.multipliedBy(i));
             rentDues.add(new RentDue(dueDate, rentPerCycle,PaymentPurposeEnum.RENT.getValue(),PaymentPurposeEnum.RENT.getName()));
        }
      //  rentDues.add(new RentDue(startDate, tenureDetails.getSecurityDeposit(),PaymentPurposeEnum.SECURITY_DEPOSIT.getValue(),PaymentPurposeEnum.SECURITY_DEPOSIT.getName()));

      System.out.println("dueDates="+ rentDues);
        
        return rentDues;
    }

	public List <RentDueEntity> saveRentDues(int tenureId, List<RentDue> rentDues) {
	    TenureDetails tenure = tenureDetailsRepository.findById(tenureId)
	            .orElseThrow(() -> new EntityNotFoundException("Tenure not found"));

	    List<RentDueEntity> existingDues = rentDueRepository.findByTenureIdAndSubscriberIdAndActive(
	            tenureId, tenure.getSubscriber().getSubscriberId(), CommonConstants.INT_ONE
	    );
	    existingDues.forEach(due -> due.setActive(CommonConstants.INT_ZERO));
	    rentDueRepository.saveAll(existingDues); // Persist soft-deleted records
	    
	    List<RentDueEntity> entities = rentDues.stream().map(due -> {
	        RentDueEntity entity = new RentDueEntity();
	        entity.setDueDate(due.getDueDate());
	        entity.setAmount(due.getAmount());
	        entity.setTenure(tenure);
	        entity.setActive(CommonConstants.INT_ONE);
	        entity.setPaymentStatus(CommonConstants.PENDING_DESC);

	        PaymentPurpose purpose = new PaymentPurpose();
	        purpose.setPurposeId(due.getPaymentPurposeId());
	        purpose.setPurposeName(due.getPaymentPurposeName());
	        entity.setPaymentPurpose(purpose);
	        
	        entity.setSubscriber(tenure.getSubscriber());
	        

	        return entity;
	    }).collect(Collectors.toList());

	    return rentDueRepository.saveAll(entities);
	}

	
	

	@Override
	public ApiResponse<Object> getPaymentSchedule(@Valid PaymentScheduleRequest request) {
		TenantUnit tu = tenantUnitRepository.findByTenantUnitIdIdAndSubscriberId(request.getTenantUnitId(),
				 request.getSubscriberId());
		if (tu == null) {
			throw new BusinessException("TenantUnit or subscriber not associated", null);
		}
		List <RentDueEntity> list=rentDueRepository.getActiveRentDues(tu.getTenureDetails().getTenantTenureId(), request.getSubscriberId());
		 List<RentDue> dues = list.stream()
		           .map(entity -> {
		               RentDue due = new RentDue();
		               due.setRentDueId(entity.getRentDueId());
		               due.setTenureId(entity.getTenure().getTenantTenureId());
		               due.setAmount(entity.getAmount());
		               due.setDueDate(entity.getDueDate());
		               due.setPaymentPurposeId(entity.getPaymentPurpose().getPurposeId());
		               due.setPaymentModeId((entity.getPaymentMode()!=null)?entity.getPaymentMode().getPaymentModeId():0);
		               due.setPaymentModeName((entity.getPaymentMode()!=null)?entity.getPaymentMode().getPaymentModeName():"");
		               due.setPaymentPurposeName(entity.getPaymentPurpose().getPurposeName());
		               return due;
		           })
		           .collect(Collectors.toList());
		  return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, dues, null, null);
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
	public ApiResponse<Object> updatePaymentModeDetails(@Valid RentDuePaymentModeRequest request)throws Exception {
		List<S3UploadObjectDto> s3UploadObjectDtoList = new ArrayList<>();
		S3UploadObjectDto s3BuildingLogoDto = null;
		List<RentDueEntity> rentDueEntList = rentDueRepository.findByTenureIdAndSubscriberIdAndActive(request.getTenureId(),
				 request.getSubscriberId(),CommonConstants.INT_ONE);
		if (rentDueEntList == null || rentDueEntList.size()==0) {
			throw new BusinessException("RentDue or subscriber not associated", null);
		}
		Integer tenantUnitId=rentDueEntList.get(0).getTenure().getTenantUnit().getTenantUnitId();
		Tenant tenant = rentDueEntList.get(0).getTenure().getTenantUnit().getTenant();
		
		if (tenant == null || Boolean.TRUE.equals(tenant.getIsDeleted())) {
			logger.error("Tenant is either deleted or not found for tenantUnitId: {}", tenantUnitId);
			throw new BusinessException("Tenant is either deleted or does not exist", null);
		}

		Unit unit = rentDueEntList.get(0).getTenure().getTenantUnit().getUnit();
		if (unit == null) {
			logger.error("Unit not found for tenantUnitId: {}", tenantUnitId);
			throw new BusinessException("Unit not found", null);
		}
		
		

		List <RentDueEntryDTO> rentDueList =request.getRentDues();
		
		for(RentDueEntryDTO rentDue:rentDueList) {
			Integer mode = rentDue.getPaymentModeId();
			RentDueEntity rentDueEnt=rentDueRepository.findById(rentDue.getRentDueId())
					.orElseThrow(() -> new BusinessException("RentDue with ID " + rentDue.getRentDueId() + " not found", null));
			
			PaymentMode paymode=paymentModeRepository.findById(rentDue.getPaymentModeId())
					.orElseThrow(() -> new BusinessException("PaymentMode with ID " + rentDue.getPaymentModeId() + " not found", null));
			try {
				if (mode == 1) { // Credit Card
					if (rentDue.getCcDetails() == null) {
						logger.error("Credit card details are missing for tenantId: {}", tenant.getTenantId());
						throw new BusinessException("Credit card details are missing", null);
					}

					TenantCCDetails cc = new TenantCCDetails();
					cc.setTenantId(tenant.getTenantId());
					cc.setTenantUnitId(tenantUnitId);
					cc.setCcName(rentDue.getCcDetails().getCcName());
					cc.setCcNo(rentDue.getCcDetails().getCcNo());
					cc.setCcCvcNo(rentDue.getCcDetails().getCcCvcNo());
					cc.setCcExpiry(rentDue.getCcDetails().getCcExpiry());
					cc.setRentDue(rentDueEnt);

					tenantCCDetailsRepository.save(cc);
					
					rentDueEnt.setCcDetails(cc); 
					rentDueEnt.setPaymentMode(paymode);
					rentDueRepository.save(rentDueEnt);
					logger.info("Credit card details saved successfully for tenantId: {}", tenant.getTenantId());

				} else if (mode == 4) { // Cheque
					
					if (rentDue.getChequeDetails() == null ) {
						logger.error("Cheque details are missing for tenantId: {}", tenant.getTenantId());
						throw new BusinessException("Cheque details are missing", null);
					}

					//for (ChequeDetailsDTO detail : rentDue.getChequeDetails()) {
					ChequeDetailsDTO detail=rentDue.getChequeDetails();
						
						TenantChequeDetails cheque = new TenantChequeDetails();
						cheque.setTenantId(tenant.getTenantId());
						cheque.setTenantUnitId(tenantUnitId);
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
						cheque.setRentDue(rentDueEnt);
						tenantChequeDetailsRepository.save(cheque);

						rentDueEnt.setChequeDetails(cheque);
						rentDueEnt.setPaymentMode(paymode);
						rentDueRepository.save(rentDueEnt);
						logger.info("Cheque details saved for chequeNo: {} and tenantId: {}", detail.getChequeNo(),
								tenant.getTenantId());
					//}
					
				}else if (mode == 2) { //Bank Transfer
					
				}else if (mode == 3) { //Cash
					
				} else {
					logger.error("Invalid payment mode or missing details for tenantId: {}", tenant.getTenantId());
					throw new BusinessException("Invalid payment mode or missing details", null);
				}

//				UnitStatus updatedStatus = unitStatusRepository.findById(2)
//						.orElseThrow(() -> new BusinessException("Unit status ID 2 not found", null));
//				unit.setUnitStatus(updatedStatus);
//				unitRepository.save(unit);
//				logger.info("Unit status updated to 2 for unitId: {}", unit.getUnitId());

				

			} catch (Exception e) {
				logger.error("Exception while saving payment details: {}", e.getMessage(), e);
				throw e;
			}
		}
		
		
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, "Payment details saved successfully", null, null);
	}
	
	
	@Override
	public ApiResponse<Object> createRentOrder(@Valid OrderRentRequest request) {
		
	//PaymentPurpose paymentPurpose = paymentPurposeRepository.findById(request.getPurposeId())
		//            .orElseThrow(() -> new BusinessException("PaymentPurposeId not found",null));
		 
		TenantUnit tu = tenantUnitRepository.findByTenantUnitIdIdAndSubscriberId(request.getTenantUnitId(),
				 request.getSubscriberId());
		PaymentOrderEntity order=new PaymentOrderEntity();
		//if(request.getPurposeId()!=null && request.getPurposeId()==PaymentPurposeEnum.SECURITY_DEPOSIT.getValue() ||
			//	 request.getPurposeId()==PaymentPurposeEnum.RENT.getValue() ) { // for Rent & securitydeposit
			if (tu == null) {
				throw new BusinessException("TenantUnit or subscriber not associated", null);
			}
			List <RentDueEntity> dueslist=rentDueRepository.findRentDuesByIds(request.getRentDueIds());
			if(dueslist==null || dueslist.size()==0) {
				throw new BusinessException("No such rent due ids / Create Payment Schedule", null);
			}
			boolean hasMultiplePaymentModes = rentDueRepository.countDistinctPaymentModes(request.getRentDueIds()) > 1;
			
			if(hasMultiplePaymentModes) {
				throw new BusinessException("Can not create order for different payment mode", null);
			}
			
			boolean hasDifferentPaymentPurpose = rentDueRepository.countDistinctPaymentPurpose(request.getRentDueIds()) > 1;
			
			if(hasDifferentPaymentPurpose) {
				throw new BusinessException("Can not create order for different payment purpose", null);
			}
			
			
			
			if(dueslist.get(0).getPaymentMode()==null) {
				throw new BusinessException("Payment mode is not set for this rent due id" + dueslist  , null);
			}
			
			PaymentMode paymode=paymentModeRepository.findById(dueslist.get(0).getPaymentMode().getPaymentModeId()).get();
			
			if(dueslist.get(0).getPaymentPurpose()==null) {
				throw new BusinessException("Payment Purpose is not set for this rent due id" + dueslist  , null);
			}
			PaymentPurpose paymentPurpose=paymentPurposeRepository.findById(dueslist.get(0).getPaymentPurpose().getPurposeId()).get();
	
			List <RentDueEntity> listRentDues=rentDueRepository.findByRentDueIdsAndTenureId(request.getRentDueIds(),tu.getTenureDetails().getTenantTenureId());
			if(listRentDues==null || listRentDues.isEmpty() ) {
				throw new BusinessException("No Rent due for this tenant / create rent due to make order", null);
			}
			order.setOrderDate(CommonUtil.getCurrentLocalDateTime());
			order.setPaymentMode(paymode);
			order.setPaymentPurpose(paymentPurpose);
			order.setPaymentStatus(OrderStatusEnum.PENDING.toString());
			order.setSubscriber(tu.getSubscriber());
			
			for (RentDueEntity rd : listRentDues) {
		            rd.setOrder(order);
		    }

		
		
		
		 PaymentOrderEntity orderEnt=	paymentOrderService.createOrder(order);
		
		if (orderEnt.getOrderId() != null) {
			PaymentOrderResponse orderResp = new PaymentOrderResponse();
			BeanUtils.copyProperties(orderEnt, orderResp);
			logger.info("Payment Order saved successfully, returning response orderId=>"+orderEnt.getOrderId());
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, orderResp, null, null);
		}
		
		logger.info("Failed to create Payment Order properly"+request.getRentDueIds()+",TenantUnitId="+request.getTenantUnitId());
		return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
	
	}
	
	@Override
	public ApiResponse<Object> createReservationOrder(@Valid OrderReservationRequest request) {
		if(request.getPurposeId()!=null && request.getPurposeId()==PaymentPurposeEnum.RESERVATION.getValue()) { //Reservation
		
		
			PaymentMode paymentMode = paymentModeRepository.findById(request.getPaymentModeId())
		            .orElseThrow(() -> new BusinessException("PaymentModeId not found",null));
			
			PaymentPurpose paymentPurpose = paymentPurposeRepository.findById(request.getPurposeId())
					            .orElseThrow(() -> new BusinessException("PaymentPurposeId not found",null));
			
			UnitReserveDetails unitReserveDetEnt=unitReserveDetailsRepository.findById(request.getUnitReserveId())
	        .orElseThrow(() -> new BusinessException("Unit ReservidID not found",null));
			
			
			if(request.getPaymentModeId()!=PaymentModeEnum.CREDIT_CARD.getValue() && request.getPaymentModeId()!=PaymentModeEnum.CASH.getValue()) {
				throw new BusinessException("Only cash or Cheque accepted",null);
			}
			
			
			
			PaymentOrderEntity order=new PaymentOrderEntity();
			order.setOrderDate(CommonUtil.getCurrentLocalDateTime());
			order.setPaymentMode(paymentMode);
			order.setPaymentPurpose(paymentPurpose);
			order.setPaymentStatus(OrderStatusEnum.PENDING.toString());
			order.setSubscriber(unitReserveDetEnt.getUnit().getSubscriber());
			PaymentOrderEntity orderEnt=	paymentOrderService.createOrder(order);
			if (orderEnt.getOrderId() != null) {
				unitReserveDetEnt.setOrder(order);
				unitReserveDetEnt.setUnitReserveStatus(CommonConstants.UNIT_RESERVE_PAYMNET_PENDING);
				PaymentOrderResponse orderResp = new PaymentOrderResponse();
				BeanUtils.copyProperties(orderEnt, orderResp);
				logger.info("Payment Order saved successfully, returning response orderId=>"+orderEnt.getOrderId());
				return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, orderResp, null, null);
			}
		
		 
		
		}
		return null;
	}



	@Override
	public ApiResponse<Object> createTransaction(@Valid TransactionRequest request) {
		
		PaymentOrderEntity paymentOrderEnt=paymentOrderRepository.findById(request.getOrderId())
				.orElseThrow(() -> new BusinessException("Order with ID " + request.getOrderId() + " not found", null));
		List<RentDueEntity> rentDuesList=rentDueRepository.findByOrderId(request.getOrderId());
		UnitReserveDetails unitReserveDetails=unitReserveDetailsRepository.findByOrderId(request.getOrderId());
		TransactionEntity transaction = new TransactionEntity();
		if(rentDuesList!=null && !rentDuesList.isEmpty()) {
			Double totalOrderAmount=rentDueRepository.getTotalAmountByOrderIdFromRentDue(request.getOrderId());
			System.out.println("totalOrderAmount="+totalOrderAmount);
			
			
	        transaction.setAmount(totalOrderAmount);
	        transaction.setPaymentMode(paymentOrderEnt.getPaymentMode());
	        transaction.setTransactionDate(LocalDateTime.now());
	        transaction.setOrder(paymentOrderEnt);
	        transaction.setStatus(OrderStatusEnum.PROCESSED.toString());
	
	        transactionRepository.save(transaction);
	        
	        paymentOrderEnt.setPaymentStatus(OrderStatusEnum.PROCESSED.toString());
        
      
      
        	TenureDetails tenureDetails=rentDuesList.get(0).getTenure();
            TenantUnit tenantUnit=tenureDetails.getTenantUnit();
            Unit unit=tenantUnit.getUnit();
            paymentOrderEnt.setPaymentStatus(OrderStatusEnum.PROCESSED.toString());
            for (RentDueEntity rd : rentDuesList) {
            	rd.setPaymentStatus(OrderStatusEnum.PROCESSED.toString());
            }	
            unit.setUnitStatus(unitStatusRepository.findByUnitStatusName(UnitStatusEnum.OCCUPIED.toString()));
            tenantUnit.setActive(true);
            tenantUnit.getTenant().setTenantStatus(CommonConstants.TENANT_ACTIVE);
        }
      
        if(unitReserveDetails!=null) {

	        transaction.setAmount(unitReserveDetails.getUnitReserveAmount());
	        transaction.setPaymentMode(paymentOrderEnt.getPaymentMode());
	        transaction.setTransactionDate(LocalDateTime.now());
	        transaction.setOrder(paymentOrderEnt);
	        transaction.setStatus(OrderStatusEnum.PROCESSED.toString());
	
	        transactionRepository.save(transaction);
	        
	        paymentOrderEnt.setPaymentStatus(OrderStatusEnum.PROCESSED.toString());
        	unitReserveDetails.setUnitReserveStatus(CommonConstants.UNIT_RESERVE_PAYMENTDONE);
        	unitReserveDetails.getUnit().setUnitStatus(unitStatusRepository.findByUnitStatusName(UnitStatusEnum.RESERVED.toString()));
        }
        
      
        if (transaction.getTransactionId() != null) {
        	TransactionResponse transResp = new TransactionResponse();
			BeanUtils.copyProperties(transaction, transResp);
			logger.info("Transaction saved successfully, returning response orderID"+request.getOrderId()+" ,TransactionID="+transaction.getTransactionId());
			return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, transResp, null, null);
		}
        logger.info("Failed to create Transaction properly orderID="+request.getOrderId());
		return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
	}





}
