package com.sbmtech.mms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingNewDTO {
    private Integer buildingId;
    private String buildingName;
    private String address;
    private String latitude;
    private String longitude;
    private String areaName;

    private List<UnitNewDTO> units;
}