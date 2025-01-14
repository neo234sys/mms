package com.sbmtech.mms.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Subscriptions;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscriptions, Integer> {

	Subscriptions findTopBySubscriber_SubscriberIdAndStatusOrderByStartDateDesc(Integer subscriberId, String status);

	List<Subscriptions> findByStatusInAndEndDateBefore(List<String> statuses, LocalDateTime endDate);

}
