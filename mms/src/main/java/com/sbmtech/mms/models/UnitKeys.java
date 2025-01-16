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
@Table(name = "unit_keys")
public class UnitKeys implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_keys_id")
	private Integer unitKeysId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	private Unit unit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "key_id", referencedColumnName = "key_id")
	private KeyMaster keyMaster;

	public Integer getUnitKeysId() {
		return unitKeysId;
	}

	public void setUnitKeysId(Integer unitKeysId) {
		this.unitKeysId = unitKeysId;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public KeyMaster getKeyMaster() {
		return keyMaster;
	}

	public void setKeyMaster(KeyMaster keyMaster) {
		this.keyMaster = keyMaster;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}