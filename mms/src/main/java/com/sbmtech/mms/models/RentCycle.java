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
@Table(name = "rent_cycle")
@Setter
@Getter
public class RentCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "rent_cycle_id")
    private int rentCycleId;

    @Column(name = "rent_cycle_name", nullable = true, length = 50)
    private String rentCycleName;


    public RentCycle() {}


  

    
}