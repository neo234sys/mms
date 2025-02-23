package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.ParkingZone;

@Repository
public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Integer> {
	
	@Query(value="SELECT B from ParkingZone B where B.parkZoneId=?1 and B.subscriber.subscriberId =?2")
	public ParkingZone findByParkZoneIdAndSubscriberId(Integer parkZoneId,Integer subscriberId);

}
