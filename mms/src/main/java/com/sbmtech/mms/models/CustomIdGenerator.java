package com.sbmtech.mms.models;
import java.time.Year;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomIdGenerator {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String generate(String basePrefix) {
    	  // Get current year as string, e.g., "2025"
        String year = String.valueOf(Year.now().getValue());
        year= year.substring(0, 1) + year.substring(2); // Removes the 2nd digit
        // Final prefix used for storage and output, e.g., "INV2025"
        String fullPrefix = basePrefix + year;
        Integer lastNumber;

        try {
            lastNumber = (Integer) entityManager.createNativeQuery(
                "SELECT last_number FROM id_sequence WHERE prefix = :prefix FOR UPDATE")
                .setParameter("prefix", fullPrefix)
                .getSingleResult();
        } catch (NoResultException e) {
            entityManager.createNativeQuery(
                "INSERT INTO id_sequence (prefix, last_number) VALUES (:prefix, 0)")
                .setParameter("prefix", fullPrefix)
                .executeUpdate();
            lastNumber = 0;
        }

        int newNumber = lastNumber + 1;

        entityManager.createNativeQuery(
            "UPDATE id_sequence SET last_number = :val WHERE prefix = :prefix")
            .setParameter("val", newNumber)
            .setParameter("prefix", fullPrefix)
            .executeUpdate();

        return fullPrefix + "-" + String.format("%03d", newNumber); // e.g. INV225-001
    }
}