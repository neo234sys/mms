package com.sbmtech.mms.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "password")
	private String password;

	@Column(name = "emirates_id")
	private Long emiratesId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nat_id")
	private Nationality nationality;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@Column(name = "address")
	private String address;

	@Lob
	@Column(name = "eida_copy", columnDefinition = "longblob")
	private byte[] eidaCopy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getEmiratesId() {
		return emiratesId;
	}

	public void setEmiratesId(Long emiratesId) {
		this.emiratesId = emiratesId;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}