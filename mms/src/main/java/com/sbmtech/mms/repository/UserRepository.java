package com.sbmtech.mms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByMobileNo(String mobileNo);
	
	Optional<User> findByEmail(String email);

	boolean existsByMobileNo(Long mobileNo);

	boolean existsByEmail(String email);

	boolean existsByEmiratesId(Long emiratesId);

}