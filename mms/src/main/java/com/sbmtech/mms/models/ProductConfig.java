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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_config")
@Setter
@Getter
public class ProductConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="config_id")
	private Integer configId;

	@Column(name="config_name")
	private String configName;
	
	@Column(name="config_value")
	private String configValue;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_id", referencedColumnName = "subscriber_id")
	private Subscriber subscriber;
	
	
	public ProductConfig() {}
	


}