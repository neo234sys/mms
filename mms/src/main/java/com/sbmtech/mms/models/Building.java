package com.sbmtech.mms.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "building")
public class Building implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "building_id")
	private Integer buildingId;

	@Column(name = "building_name")
	private String buildingName;

	@Column(name = "address")
	private String address;

	private String buildingLogoFileName;

	@Column(name = "has_gym")
	private Boolean hasGym;

	@Column(name = "has_swimpool")
	private Boolean hasSwimpool;

	@Column(name = "has_kids_playground")
	private Boolean hasKidsPlayground;

	@Column(name = "has_playcourt")
	private Boolean hasPlaycourt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "community_id")
	private Community community;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Area area;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_id")
	private Subscriber subscriber;

	@Column(name = "no_of_floors")
	private Integer noOfFloors;

	@Column(name = "no_of_units")
	private Integer noOfUnits;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBuildingLogoFileName() {
		return buildingLogoFileName;
	}

	public void setBuildingLogoFileName(String buildingLogoFileName) {
		this.buildingLogoFileName = buildingLogoFileName;
	}

	public Boolean getHasGym() {
		return hasGym;
	}

	public void setHasGym(Boolean hasGym) {
		this.hasGym = hasGym;
	}

	public Boolean getHasSwimpool() {
		return hasSwimpool;
	}

	public void setHasSwimpool(Boolean hasSwimpool) {
		this.hasSwimpool = hasSwimpool;
	}

	public Boolean getHasKidsPlayground() {
		return hasKidsPlayground;
	}

	public void setHasKidsPlayground(Boolean hasKidsPlayground) {
		this.hasKidsPlayground = hasKidsPlayground;
	}

	public Boolean getHasPlaycourt() {
		return hasPlaycourt;
	}

	public void setHasPlaycourt(Boolean hasPlaycourt) {
		this.hasPlaycourt = hasPlaycourt;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public Integer getNoOfFloors() {
		return noOfFloors;
	}

	public void setNoOfFloors(Integer noOfFloors) {
		this.noOfFloors = noOfFloors;
	}

	public Integer getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(Integer noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Building [buildingId=" + buildingId + ", buildingName=" + buildingName + ", address=" + address
				+ ", buildingLogoFileName=" + buildingLogoFileName + ", hasGym=" + hasGym + ", hasSwimpool="
				+ hasSwimpool + ", hasKidsPlayground=" + hasKidsPlayground + ", hasPlaycourt=" + hasPlaycourt
				+ ", community=" + community + ", area=" + area + ", subscriber=" + subscriber + ", noOfFloors="
				+ noOfFloors + ", noOfUnits=" + noOfUnits + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", createdAt=" + createdAt + ", isDeleted=" + isDeleted + "]";
	}

}