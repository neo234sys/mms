package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

	Otp findByReferenceIdAndOtpCode(Integer referenceId, Long otpCode);

}