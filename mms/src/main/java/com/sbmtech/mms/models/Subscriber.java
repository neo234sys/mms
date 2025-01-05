package com.sbmtech.mms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "subscriber")
public class Subscriber implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscriber_id")
	private Integer subscriberId;

	@Column(name = "subscriber_name")
	private String subscriberName;

	@Column(name = "companay_contact_name")
	private String companyContactName;

	@Column(name = "company_email")
	private String companyEmail;

	@Column(name = "company_landline_no")
	private String companyLandlineNo;

	@Column(name = "company_mobile_no")
	private String companyMobileNo;

	@Column(name = "companay_name")
	private String companyName;

	@Column(name = "company_tradelicense")
	private String companyTradeLincense;

	@Lob
	@Column(name = "company_tradelicense_copy")
	private  byte[]  companyTradecopy;
	
	@Lob
	@Column(name = "company_logo")
	private  byte[]  companyLogo;

	@Column(name = "channel_id")
	private Integer channelId;
	
	@Column(name = "otp_verified")
	private Integer otpVerified;


	


}
