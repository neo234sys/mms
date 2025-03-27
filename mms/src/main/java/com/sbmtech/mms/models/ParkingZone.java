package com.sbmtech.mms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parking_zone")
public class ParkingZone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "park_zone_id")
	private Integer parkZoneId;

	@Column(name = "park_zone_name")
	private String parkZoneName;

	@ManyToOne
	@JoinColumn(name = "subscriber_id", referencedColumnName = "subscriber_id")
	private Subscriber subscriber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "building_id", referencedColumnName = "building_id")
	private Building building;

	public Integer getParkZoneId() {
		return parkZoneId;
	}

	public void setParkZoneId(Integer parkZoneId) {
		this.parkZoneId = parkZoneId;
	}

	public String getParkZoneName() {
		return parkZoneName;
	}

	public void setParkZoneName(String parkZoneName) {
		this.parkZoneName = parkZoneName;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}



}
