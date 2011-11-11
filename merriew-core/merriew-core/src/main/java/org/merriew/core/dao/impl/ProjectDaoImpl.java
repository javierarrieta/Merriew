package org.merriew.core.dao.impl;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.merriew.core.dao.ProjectDao;
import org.merriew.core.entity.Project;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ProjectDaoImpl implements ProjectDao {
	
	private EntityManager em;

	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public void create(Project project) {
		
		project.setId( UUID.randomUUID().toString() ); 
		
		em.persist(project);

	}

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
