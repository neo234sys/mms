package com.sbmtech.mms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer>, JpaSpecificationExecutor<Building>  {

	@Query(value = "SELECT B from Building B where B.buildingId=?1 and B.subscriber.subscriberId =?2")
	public Building findByBuildingIdAndSubscriberId(Integer buildingId, Integer subscriberId);

	Page<Building> findAll(Pageable pageable);

	@Query("SELECT b FROM Building b WHERE b.subscriber.id = :subscriberId AND b.isDeleted = false")
	List<Building> findAllBySubscriberIdAndIsDeletedFalse(@Param("subscriberId") Integer subscriberId);
	
	
	@Query(value = "SELECT company_logo_filename AS file_name FROM subscriber WHERE company_logo_filename IS NOT NULL AND company_logo_filename <> '' " +
            "UNION ALL " +
            "SELECT company_tradelicense_copy_filename FROM subscriber WHERE company_tradelicense_copy_filename IS NOT NULL AND company_tradelicense_copy_filename <> '' " +
            "UNION ALL " +
            "SELECT eida_copy_filename FROM tenants WHERE eida_copy_filename IS NOT NULL AND eida_copy_filename <> '' " +
            "UNION ALL " +
            "SELECT passport_copy_filename FROM tenants WHERE passport_copy_filename IS NOT NULL AND passport_copy_filename <> '' " +
            "UNION ALL " +
            "SELECT photo_filename FROM tenants WHERE photo_filename IS NOT NULL AND photo_filename <> '' " +
            "UNION ALL " +
            "SELECT building_logo_file_name FROM building WHERE building_logo_file_name IS NOT NULL AND building_logo_file_name <> '' " +
            "UNION ALL " +
            "SELECT unit_main_pic1_name FROM unit WHERE unit_main_pic1_name IS NOT NULL AND unit_main_pic1_name <> '' " +
            "UNION ALL " +
            "SELECT unit_pic2_name FROM unit WHERE unit_pic2_name IS NOT NULL AND unit_pic2_name <> '' " +
            "UNION ALL " +
            "SELECT unit_pic3_name FROM unit WHERE unit_pic3_name IS NOT NULL AND unit_pic3_name <> '' " +
            "UNION ALL " +
            "SELECT unit_pic4_name FROM unit WHERE unit_pic4_name IS NOT NULL AND unit_pic4_name <> '' " +
            "UNION ALL " +
            "SELECT unit_pic5_name FROM unit WHERE unit_pic5_name IS NOT NULL AND unit_pic5_name <> ''",
    nativeQuery = true)
	List<String> findAllFileNames();

}