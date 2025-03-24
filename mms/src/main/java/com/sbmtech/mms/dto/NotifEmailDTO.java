package com.sbmtech.mms.dto;

import java.util.List;

public class NotifEmailDTO {
	
	private String emailTo;
	private String emailCC;
	private String emailBCC;
	private String subject;
	private String emailBody;
	private String url;
	private String attachmentFileName;
	private String attachmentFilePath;
	private byte[] attachmentObj;
	private byte[] attachmentExcelObj;
	private String type;
	private Long referenceNo;
	private String customerName;
	private Long otpCode;
	private String pwd;
	
	private String msg1;
	private String msg2;
	private String msg3;
	
	private String buildingId;
	private String unitId;
	private String buildingName;
	private String unitName;
	private String reserveFromDate;
	private String reserveToDate;
	
	private List<MultipleAttachmentsEmailDTO> multipleAttachmentsEmailDTO;
	
	private List<EmailAttachmentDTO> multipleAttachList;
	
	public List<MultipleAttachmentsEmailDTO> getMultipleAttachmentsEmailDTO() {
		return multipleAttachmentsEmailDTO;
	}
	public void setMultipleAttachmentsEmailDTO(
			List<MultipleAttachmentsEmailDTO> multipleAttachmentsEmailDTO) {
		this.multipleAttachmentsEmailDTO = multipleAttachmentsEmailDTO;
	}
	public byte[] getAttachmentExcelObj() {
		return attachmentExcelObj;
	}
	public void setAttachmentExcelObj(byte[] attachmentExcelObj) {
		this.attachmentExcelObj = attachmentExcelObj;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public String getAttachmentFilePath() {
		return attachmentFilePath;
	}
	public void setAttachmentFilePath(String attachmentFilePath) {
		this.attachmentFilePath = attachmentFilePath;
	}
	public byte[] getAttachmentObj() {
		return attachmentObj;
	}
	public void setAttachmentObj(byte[] attachmentObj) {
		this.attachmentObj = attachmentObj;
	}
	public List<EmailAttachmentDTO> getMultipleAttachList() {
		return multipleAttachList;
	}
	public void setMultipleAttachList(List<EmailAttachmentDTO> multipleAttachList) {
		this.multipleAttachList = multipleAttachList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(Long referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getEmailCC() {
		return emailCC;
	}
	public void setEmailCC(String emailCC) {
		this.emailCC = emailCC;
	}
	public String getEmailBCC() {
		return emailBCC;
	}
	public void setEmailBCC(String emailBCC) {
		this.emailBCC = emailBCC;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getOtpCode() {
		return otpCode;
	}
	public void setOtpCode(Long otpCode) {
		this.otpCode = otpCode;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMsg1() {
		return msg1;
	}
	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	public String getMsg3() {
		return msg3;
	}
	public void setMsg3(String msg3) {
		this.msg3 = msg3;
	}
	public String getReserveFromDate() {
		return reserveFromDate;
	}
	public void setReserveFromDate(String reserveFromDate) {
		this.reserveFromDate = reserveFromDate;
	}
	public String getReserveToDate() {
		return reserveToDate;
	}
	public void setReserveToDate(String reserveToDate) {
		this.reserveToDate = reserveToDate;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	
}