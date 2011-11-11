package org.merriew.core;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.merriew.core.dao.ProjectDao;
import org.merriew.core.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
 		"/META-INF/spring/merriew-core-entities.xml",
 		"/META-INF/spring/merriew-core-persistence.xml" })
public class ProjectDAoTest {
	
	@Autowired
	public ProjectDao projectDao;
	
	@PersistenceContext
	public EntityManager em;
	
	@Test
	public void testCreate() {
		
		Project project1 = new Project();
		
		project1.setName("test");
		project1.setDescription("A sample test");
		
		projectDao.create(project1);
		
		Assert.assertNotNull("Project should have a valid id after being persisted", project1.getId() );
		
		Project project2 = new Project();
		project2.setName(project1.getName());
		project2.setDescription("Another test");
		
		try {
			
			//Try to insert duplicated project name
			projectDao.create(project2);
			
			TypedQuery<Project> q = em.createQuery("select p from Project p", Project.class);
			q.getResultList();
			

			Assert.fail("Inserted two projects with the same name, should have raised an error");
			
		} catch ( DataIntegrityViolationException dive ) { 
			
		}		
		
	}

}