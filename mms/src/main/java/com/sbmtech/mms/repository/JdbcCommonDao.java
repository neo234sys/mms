package com.sbmtech.mms.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JdbcCommonDao {

	@PersistenceContext()
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}
}
