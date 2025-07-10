package com.sbmtech.mms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bedspace_bathroom_type_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BedspaceBathroomTypeMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bedspaceBathroomTypeId;

    private String bedspaceBathroomTypeName;
}
