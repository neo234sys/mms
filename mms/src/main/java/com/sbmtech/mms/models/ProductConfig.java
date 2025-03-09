package com.sbmtech.mms.models;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Table(name = "product_config")
@Setter
@Getter
@TypeDef(name = "json", typeClass = JsonType.class)
public class ProductConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subscriber_id")
	private Integer subscriberId;

	 @Type(type = "json")
	 @Column(columnDefinition = "json")
	 private Map<String, String> configjson;
	


}