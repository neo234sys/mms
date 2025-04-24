package com.sbmtech.mms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbmtech.mms.models.DepartmentMaster;

@Repository
public interface DepartmentMasRepository extends JpaRepository<DepartmentMaster, Integer> {

	@Query(value = "SELECT B from DepartmentMaster B where B.deptId=?1 and B.subscriber.subscriberId =?2")
	public DepartmentMaster findByDeptIdAndSubscriberId(Integer deptId, Integer subscriberId);

	@Query(value = "SELECT B from DepartmentMaster B where B.deptName=?1 and B.subscriber.subscriberId =?2")
	public DepartmentMaster findByDeptNameAndSubscriberId(String deptName, Integer subscriberId);

}
