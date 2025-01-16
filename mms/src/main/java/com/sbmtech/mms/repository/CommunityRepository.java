package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmtech.mms.models.Community;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

}