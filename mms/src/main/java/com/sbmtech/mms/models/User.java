package com.sbmtech.mms.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile_no")
	private Long mobileNo;

	@Column(name = "password")
	private String password;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "emirates_id")
	private Long emiratesId;

	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "address")
	private String address;

	@Lob
	@Column(name = "eida_copy", columnDefinition = "longblob")
	private byte[] eidaCopy;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nat_id", referencedColumnName = "country_id")
	private Countries nationality;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id")
	private UserTypeMaster userType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_id", referencedColumnName = "subscriber_id")
	private Subscriber subscriber;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Column(name = "tenent_id")
	private Integer tenantId;

	@Column(name = "employee_id")
	private Integer employeeId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "company_id")
	private Integer companyId;

	@Column(name = "country_id")
	private Integer countryId;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
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

	public Countries getNationality() {
		return nationality;
	}

	public void setNationality(Countries nationality) {
		this.nationality = nationality;
	}

	public UserTypeMaster getUserType() {
		return userType;
	}

	public void setUserType(UserTypeMaster userType) {
		this.userType = userType;
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

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}