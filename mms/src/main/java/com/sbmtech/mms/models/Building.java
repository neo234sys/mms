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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "building")
@Setter
@Getter
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