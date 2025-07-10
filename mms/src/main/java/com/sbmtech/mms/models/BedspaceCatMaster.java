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
@Table(name = "bedspace_cat_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BedspaceCatMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bedspaceCatId;

    private String bedspaceCatName;
}
