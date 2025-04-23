package com.sbmtech.mms.service.impl;

import static com.sbmtech.mms.constant.CommonConstants.FAILURE_CODE;
import static com.sbmtech.mms.constant.CommonConstants.FAILURE_DESC;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_CODE;
import static com.sbmtech.mms.constant.CommonConstants.SUCCESS_DESC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.dto.ChequeDetailsDTO;
import com.sbmtech.mms.exception.BusinessException;
import com.sbmtech.mms.models.PaymentPurpose;
import com.sbmtech.mms.models.Tenant;
import com.sbmtech.mms.models.TenantCCDetails;
import com.sbmtech.mms.models.TenantChequeDetails;
import com.sbmtech.mms.models.TenantUnit;
import com.sbmtech.mms.models.Unit;
import com.sbmtech.mms.models.UnitStatus;
import com.sbmtech.mms.payload.request.ApiResponse;
import com.sbmtech.mms.payload.request.SavePaymentDetailsRequest;
import com.sbmtech.mms.repository.PaymentPurposeRepository;
import com.sbmtech.mms.repository.TenantCCDetailsRepository;
import com.sbmtech.mms.repository.TenantChequeDetailsRepository;
import com.sbmtech.mms.repository.TenantUnitRepository;
import com.sbmtech.mms.repository.UnitRepository;
import com.sbmtech.mms.repository.UnitStatusRepository;
import com.sbmtech.mms.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentPurposeRepository paymentPurposeRepository;

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

	@Override
	public ApiResponse<List<PaymentPurpose>> getAllPaymentPurposes() throws Exception {
		List<PaymentPurpose> result = paymentPurposeRepository.findAll();
		if (result.isEmpty()) {
			return new ApiResponse<>(FAILURE_CODE, FAILURE_DESC, null, null, null);
		}
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_DESC, result, null, null);

	}

	@Override
	public ApiResponse<Object> savePaymentDetails(SavePaymentDetailsRequest request) {
		logger.info("Received request to save payment details for tenantId: {}, tenantUnitId: {}, paymentModeId: {}",
				request.getTenantId(), request.getTenantUnitId(), request.getPaymentModeId());

		Optional<TenantUnit> tenantUnitOpt = tenantUnitRepository.findById(request.getTenantUnitId());
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

			} else if (mode == 2) { // Cheque
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
					cheque.setChequePic(detail.getChequePic());
					cheque.setChequeDate(
							LocalDate.parse(detail.getChequeDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
					cheque.setPaymentPurposeId(detail.getPaymentPurposeId());

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

}
