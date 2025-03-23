package com.sbmtech.mms.service;

public interface CronJobService {

	public void expireSubscriptions() throws Exception;
	public void releaseReservedUnits() throws Exception;
	public void deleteUnusedS3Images() throws Exception;
}
