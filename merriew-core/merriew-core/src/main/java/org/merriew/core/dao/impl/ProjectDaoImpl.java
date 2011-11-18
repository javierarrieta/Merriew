package org.merriew.core.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
	public Project[] findAllProjects() {
		
		TypedQuery<Project> q = em.createNamedQuery("Project.findAll", Project.class);
		
		List<Project> projects = q.getResultList();
		
		return projects.toArray(new Project[projects.size()]);
	}
	
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
	public Project getProject( String id ) {
		
		return em.find(Project.class, id);
	}

}
