package com.sbmtech.mms.models;

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
@Table(name = "unit_subtype")
@Setter
@Getter
public class UnitSubType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "unit_subtype_id")
    private int unitSubtypeId;

    @Column(name = "unit_subtype_name", nullable = true, length = 50)
    private String unitSubtypeName;

    // Default constructor (required by JPA)
    public UnitSubType() {}

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_type_id", referencedColumnName = "unit_type_id")
	private UnitType unitType;

    
}