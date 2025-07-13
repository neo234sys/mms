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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bedspace")

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Bedspace implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bedspaceId;

    private String bedspaceName;

    @ManyToOne(fetch = FetchType.LAZY)
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
    
    @Column(name = "bs_main_pic1_name")
	private String bsMainPic1Name;

	@Column(name = "bs_pic2_name")
	private String bsPic2Name;

	@Column(name = "bs_pic3_name")
	private String bsPic3Name;

	@Column(name = "bs_pic4_name")
	private String bsPic4Name;

	@Column(name = "bs_pic5_name")
	private String bsPic5Name;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;
}
