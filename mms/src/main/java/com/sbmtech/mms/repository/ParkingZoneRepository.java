package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.ParkingZone;

@Repository
public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Integer> {

	@Query(value = "SELECT B from ParkingZone B where B.parkZoneId=?1 and B.subscriber.subscriberId =?2")
	public ParkingZone findByParkZoneIdAndSubscriberId(Integer parkZoneId, Integer subscriberId);

	@Query(value = "SELECT B from ParkingZone B where B.building.buildingId=?1 and B.subscriber.subscriberId =?2")
	public List<ParkingZone> findAllParkZoneByBuildingId(Integer buildingId, Integer subscriberId);

	@Query(value = "SELECT B from ParkingZone B where B.parkZoneId=?1 and B.building.buildingId =?2 and B.subscriber.subscriberId =?3")
	public ParkingZone findByParkZoneIdAndBuildingIdSubscriberId(Integer parkZoneId, Integer buildingId,
			Integer subscriberId);

	List<ParkingZone> findByBuilding(Building building);

}
