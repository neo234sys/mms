package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

	@Query(value="SELECT P from Parking P join Building B ON P.building.buildingId=B.buildingId "
			+ " where P.parkingId=?1 and  B.subscriber.subscriberId =?2")
	public Parking findByParkingIdAndSubscriberId(Integer parkingId,Integer subscriberId);
	
	List<Parking> findByBuildingBuildingId(Integer buildingId);
}