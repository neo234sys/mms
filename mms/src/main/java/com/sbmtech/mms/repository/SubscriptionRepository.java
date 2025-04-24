package com.sbmtech.mms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Subscriptions;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscriptions, Integer> {

	Subscriptions findTopBySubscriber_SubscriberIdAndStatusOrderByStartDateDesc(Integer subscriberId, String status);

	Subscriptions findTopBySubscriber_SubscriberIdOrderByStartDateDesc(Integer subscriberId);

	Subscriptions findTopBySubscriber_SubscriberIdOrderBySubscriptionIdDesc(Integer subscriberId);

	List<Subscriptions> findByStatusInAndEndDateBefore(List<String> statuses, LocalDate endDate);

}
