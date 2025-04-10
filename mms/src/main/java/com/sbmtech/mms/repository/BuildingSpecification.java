package com.sbmtech.mms.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.sbmtech.mms.models.Area;
import com.sbmtech.mms.models.Building;
import com.sbmtech.mms.models.City;

public class BuildingSpecification {

    public static Specification<Building> searchByKeyword(String keyword,Integer subscriberId) {
        return (root, query, cb) -> {
            //root.fetch("area", JoinType.LEFT).fetch("city", JoinType.LEFT); // for performance
            Join<Building, Area> areaJoin = root.join("area", JoinType.LEFT);
            Join<Area, City> cityJoin = areaJoin.join("city", JoinType.LEFT);
            
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.isFalse(root.get("isDeleted")));
            
            if (keyword != null && !keyword.trim().isEmpty()) {
                String likeKeyword = "%" + keyword.toLowerCase() + "%";
                Predicate keywordPredicate = cb.or(
                    cb.like(cb.lower(root.get("buildingName")), likeKeyword),
                    cb.like(cb.lower(areaJoin.get("areaName")), likeKeyword),
                    cb.like(cb.lower(cityJoin.get("name")), likeKeyword)
                );
                predicates.add(keywordPredicate);
            }
            
            if (subscriberId != null) {
                predicates.add(cb.equal(root.get("subscriber").get("subscriberId"), subscriberId));
            }
            
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}