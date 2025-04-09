package com.sbmtech.mms.repository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;

import com.sbmtech.mms.models.Area;
import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.City;

public class BuildingSpecification {

    public static Specification<Building> searchByKeyword(String keyword) {
        return (root, query, cb) -> {
            //root.fetch("area", JoinType.LEFT).fetch("city", JoinType.LEFT); // for performance
            Join<Building, Area> areaJoin = root.join("area", JoinType.LEFT);
            Join<Area, City> cityJoin = areaJoin.join("city", JoinType.LEFT);

            String likeKeyword = "%" + keyword.toLowerCase() + "%";

            return cb.or(
                cb.like(cb.lower(root.get("buildingName")), likeKeyword),
                cb.like(cb.lower(areaJoin.get("areaName")), likeKeyword),
                cb.like(cb.lower(cityJoin.get("name")), likeKeyword)
            );
        };
    }
}