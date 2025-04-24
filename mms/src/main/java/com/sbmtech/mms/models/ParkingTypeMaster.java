package com.sbmtech.mms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parking_type_master")
@Setter
@Getter
public class ParkingTypeMaster {

	@Id
	@Column(name = "parking_type_name", nullable = true, length = 50)
	private String parkingTypeName;

	public ParkingTypeMaster() {
	}

}