package com.sbmtech.mms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unit_type")
@Setter
@Getter
public class UnitType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "unit_type_id")
    private int unitTypeId;

    @Column(name = "unit_type_name", nullable = true, length = 50)
    private String unitTypeName;


    public UnitType() {}


    public UnitType(String unitTypeName) {
        this.unitTypeName = unitTypeName;
    }

    
}