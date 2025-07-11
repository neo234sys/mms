package com.sbmtech.mms.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.sbmtech.mms.dto.BedspaceSearchCriteria;
import com.sbmtech.mms.models.Bedspace;

public class BedspaceSpecifications {

    public static Specification<Bedspace> matchesCriteria(BedspaceSearchCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Subscriber filter
            if (criteria.getSubscriberId() != null) {
                predicates.add(cb.equal(root.get("subscriber").get("subscriberId"), criteria.getSubscriberId()));
            }

            // 2. Area by name
            if (criteria.getArea() != null && !criteria.getArea().isBlank()) {
                predicates.add(cb.equal(
                        cb.lower(root.get("unit").get("building").get("area").get("areaName")),
                        criteria.getArea().toLowerCase()
                ));
            }

            // 3. Area by ID
            if (criteria.getAreaId() != null) {
                predicates.add(cb.equal(
                        root.get("unit").get("building").get("area").get("areaId"),
                        criteria.getAreaId()
                ));
            }

            // 4. City by name
            if (criteria.getCity() != null && !criteria.getCity().isBlank()) {
                predicates.add(cb.equal(
                        cb.lower(root.get("unit").get("building").get("area").get("city").get("cityName")),
                        criteria.getCity().toLowerCase()
                ));
            }

            // 5. City by ID
            if (criteria.getCityId() != null) {
                predicates.add(cb.equal(
                        root.get("unit").get("building").get("area").get("city").get("cityId"),
                        criteria.getCityId()
                ));
            }

            // 6. Building
            if (criteria.getBuildingId() != null) {
                predicates.add(cb.equal(
                        root.get("unit").get("building").get("buildingId"),
                        criteria.getBuildingId()
                ));
            }

            // 7. Partition type
            if (criteria.getPartitionId() != null) {
                predicates.add(cb.equal(
                        root.get("partition").get("bedspacePartitionId"),
                        criteria.getPartitionId()
                ));
            }

            // 8. Bedspace category
            if (criteria.getBedspaceCategoryId() != null) {
                predicates.add(cb.equal(
                        root.get("bedspaceCategory").get("bedspaceCatId"),
                        criteria.getBedspaceCategoryId()
                ));
            }

            // 9. Bathroom type
            if (criteria.getBedspaceBathroomTypeId() != null) {
                predicates.add(cb.equal(
                        root.get("bathroomType").get("bedspaceBathroomTypeId"),
                        criteria.getBedspaceBathroomTypeId()
                ));
            }

            // 10. Features text
            if (criteria.getFeatures() != null && !criteria.getFeatures().isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("features")),
                        "%" + criteria.getFeatures().toLowerCase() + "%"
                ));
            }

            // 11. Rent Month Range
            if (criteria.getMinRentMonth() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("rentMonth"),
                        criteria.getMinRentMonth()
                ));
            }
            if (criteria.getMaxRentMonth() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("rentMonth"),
                        criteria.getMaxRentMonth()
                ));
            }

            // 12. Rent Day Range
            if (criteria.getMinRentDay() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("rentDay"),
                        criteria.getMinRentDay()
                ));
            }
            if (criteria.getMaxRentDay() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("rentDay"),
                        criteria.getMaxRentDay()
                ));
            }

            // 13. Keyword giant OR filter
            if (criteria.getKeyword() != null && !criteria.getKeyword().isBlank()) {
                String kw = "%" + criteria.getKeyword().toLowerCase() + "%";

                List<Predicate> keywordPredicates = new ArrayList<>();
                keywordPredicates.add(cb.like(cb.lower(root.get("unit").get("building").get("area").get("areaName")), kw));
                keywordPredicates.add(cb.like(cb.lower(root.get("unit").get("building").get("area").get("city").get("cityName")), kw));
                keywordPredicates.add(cb.like(cb.lower(root.get("partition").get("bedspacePartitionName")), kw));
                keywordPredicates.add(cb.like(cb.lower(root.get("bedspaceCategory").get("bedspaceCatName")), kw));
                keywordPredicates.add(cb.like(cb.lower(root.get("bathroomType").get("bedspaceBathroomTypeName")), kw));
                keywordPredicates.add(cb.like(cb.lower(root.get("features")), kw));
                keywordPredicates.add(cb.like(cb.lower(cb.function("CAST", String.class, root.get("hasKitchen"))), kw));
                keywordPredicates.add(cb.like(cb.lower(cb.function("CAST", String.class, root.get("hasWardrobe"))), kw));

                predicates.add(cb.or(keywordPredicates.toArray(new Predicate[0])));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
