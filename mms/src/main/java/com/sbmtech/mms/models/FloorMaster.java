package com.sbmtech.mms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "floor_master")
public class FloorMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "floor_name")
	private String floorName;

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

}