package com.sbmtech.mms.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BedspaceDTO {
    private Long bedspaceId;
    private String bedspaceName;
    private String features;
    private Double securityDeposit;
    private Double rentMonth;
    private Double rentDay;
    //private Boolean hasWardrobe;
   // private Boolean hasKitchen;
    private Integer unitId;
    
    private Integer partitionId;
    private String partitionTypeName;
    
    private Integer bedspaceCategoryId;
    private String bedspaceCategoryName;

    private Integer bedspaceBathroomTypeId;
    private String bedspaceBathroomTypeName;
    private String status;
    
    private List<String> bsImages;

}