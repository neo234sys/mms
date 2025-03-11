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
@Table(name = "unit_status")
@Setter
@Getter
public class UnitStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "unit_status_id")
    private int unitStatusId;

    @Column(name = "unit_status_name", nullable = true, length = 50)
    private String unitStatusName;

    // Default constructor (required by JPA)
    public UnitStatus() {}

    // Parameterized constructor
    public UnitStatus(String unitStatusName) {
        this.unitStatusName = unitStatusName;
    }

    
}