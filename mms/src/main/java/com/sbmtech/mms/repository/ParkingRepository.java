package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

	@Query(value = "SELECT P from Parking P join Building B ON P.building.buildingId=B.buildingId "
			+ " where P.parkingId=?1 and  B.subscriber.subscriberId =?2  and B.buildingId =?3")
	public Parking findByParkingIdAndSubscriberId(Integer parkingId, Integer subscriberId,Integer buildingId);

	List<Parking> findByBuildingBuildingId(Integer buildingId);

	@Query(value = "SELECT P from Parking P   where P.parkZone.parkZoneId=?1 and  P.building.buildingId =?2")
	List<Parking> findAllParkingByParkZoneIdAndBuildingId(Integer parkZoneId, Integer buildingId);

	List<Parking> findByBuilding(Building building);
	
	
	@Query("SELECT p FROM Parking p JOIN TenantUnit tu ON p.parkingId = tu.parking.parkingId where p.parkingId =?1")
	Parking findParkingWithTenantUnit(Integer parkingId);
}