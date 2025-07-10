package com.sbmtech.mms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bedspace")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bedspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bedspaceId;

    private String bedspaceName;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @OneToOne
    @JoinColumn(name = "partition_id")
    private BedspacePartitionMaster partition;

    @OneToOne
    @JoinColumn(name = "bedspace_cat_id")
    private BedspaceCatMaster bedspaceCategory;

    @OneToOne
    @JoinColumn(name = "bedspace_bathroom_type_id")
    private BedspaceBathroomTypeMaster bathroomType;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    private Double securityDeposit;
    private Double rentMonth;
    private Double rentDay;

    private Boolean hasWardrobe;
    private Boolean hasKitchen;

    private String features;
}
