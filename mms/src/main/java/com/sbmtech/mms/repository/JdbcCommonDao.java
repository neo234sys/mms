package com.sbmtech.mms.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public class JdbcCommonDao {

	@PersistenceContext()
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}
	
//	 @Transactional
//	 public String generateCustomId(String prefix) {
//	        EntityManager em = this.getEm();
//
//	        // Lock the row
//	        Integer lastNumber = (Integer) em.createNativeQuery(
//	            "SELECT last_number FROM id_sequence WHERE prefix = :prefix FOR UPDATE")
//	            .setParameter("prefix", prefix)
//	            .getSingleResult();
//
//	        if (lastNumber == null) {
//	            em.createNativeQuery("INSERT INTO id_sequence (prefix, last_number) VALUES (:prefix, 0)")
//	                .setParameter("prefix", prefix)
//	                .executeUpdate();
//	            lastNumber = 0;
//	        }
//
//	        int newNumber = lastNumber + 1;
//
//	        em.createNativeQuery("UPDATE id_sequence SET last_number = :val WHERE prefix = :prefix")
//	            .setParameter("val", newNumber)
//	            .setParameter("prefix", prefix)
//	            .executeUpdate();
//
//	        return prefix + String.format("%03d", newNumber);
//	    }
}
