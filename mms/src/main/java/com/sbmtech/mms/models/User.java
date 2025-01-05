package com.sbmtech.mms.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile_no")
	private Long mobileNo;
	
	@Column(name = "password")
	private String password;

	@Column(name = "active")
	private Integer  active;

	
	@Column(name = "emirates_id")
	private Long emiratesId;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "gender")
	private Integer  gender;

	@OneToOne
	@JoinColumn(name="nat_id",insertable=false, updatable=false)
	Countries countries;
	
	@OneToOne
	@JoinColumn(name="user_type_id",insertable=false, updatable=false)
	UserTypeMaster userTypeMaster;
	
	@OneToOne
	@JoinColumn(name="subscriber_id",insertable=false, updatable=false)
	Subscriber subscriber;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	

	@Column(name = "address")
	private String address;

	@Lob
	@Column(name = "eida_copy", columnDefinition = "longblob")
	private byte[] eidaCopy;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Long getEmiratesId() {
		return emiratesId;
	}

	public void setEmiratesId(Long emiratesId) {
		this.emiratesId = emiratesId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Countries getCountries() {
		return countries;
	}

	public void setCountries(Countries countries) {
		this.countries = countries;
	}

	public UserTypeMaster getUserTypeMaster() {
		return userTypeMaster;
	}

	public void setUserTypeMaster(UserTypeMaster userTypeMaster) {
		this.userTypeMaster = userTypeMaster;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getEidaCopy() {
		return eidaCopy;
	}

	public void setEidaCopy(byte[] eidaCopy) {
		this.eidaCopy = eidaCopy;
	}

	


	
}