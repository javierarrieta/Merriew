package org.merriew.core;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.merriew.core.dao.ProjectDao;
import org.merriew.core.entity.Project;
import org.merriew.core.entity.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
 		"/META-INF/spring/merriew-core-entities.xml",
 		"/META-INF/spring/merriew-core-persistence.xml" })
public class ProjectDAoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	public ProjectDao projectDao;
	
	@PersistenceContext
	public EntityManager em;
	
	@Test
	public void testCreateProject() {
		
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
			
		} catch ( PersistenceException e ) { 
			if(! (e.getCause() instanceof ConstraintViolationException ) ) {
				Assert.fail(e.getLocalizedMessage() );
			}
		}		
		
	}
	
	@Test
	public void testGetProject() {
		
		Project p = new Project();
		p.setName("1");
		p.setDescription("one");
		
		projectDao.create(p);
		
		Project p1 = projectDao.getProject(p.getId());
		
		Assert.assertEquals(p, p1);
	}

	@Test
	public void findAllProjects() {
		
		Project p1 = new Project();
		
		p1.setName("1");
		p1.setDescription("one");
		
		Project p2 = new Project();
		
		p2.setName("2");
		p2.setDescription("two");
		
		projectDao.create(p1);
		projectDao.create(p2);
		
		Project[] projects = projectDao.findAllProjects();
		
		Assert.assertArrayEquals(projects, new Project[] { p1, p2 } );
		
		
		
	}
	
	@Test
	public void testCreateRepository() {
		
		Project p = new Project();
		p.setName("1");
		p.setDescription("one");
		
		projectDao.create(p);
		
		Repository r = new Repository();
		
		r.setName("r1");
		r.setUri("file:///repos/r1");
		r.setProject(p);
		
		projectDao.create(r);
		
		List<Repository> repos = em.createQuery("select r from Repository r", Repository.class ).getResultList();
		
		Assert.assertEquals("Result should have one element",1, repos.size() );
		
		Assert.assertEquals("Repository differs from inserted", r, repos.get(0) );
	}
	
	@Test
	public void testGetRepository() {

		Project p = new Project();
		p.setName("1");
		p.setDescription("one");
		
		projectDao.create(p);
		
		Repository r = new Repository();
		
		r.setName("r1");
		r.setUri("file:///repos/r1");
		r.setProject(p);
		
		projectDao.create(r);
		
		Repository repo = projectDao.getRepository(r.getId());
		
		Assert.assertEquals("Retrieved repository should equal the created repository", r,repo);
		
	}
	
	@Test
	public void testGetRepositories() {

		Project p = new Project();
		p.setName("1");
		p.setDescription("one");
		
		projectDao.create(p);
		
		Repository r1 = new Repository();
		
		r1.setName("r1");
		r1.setUri("file:///repos/r1");
		r1.setProject(p);

		
		Repository r2 = new Repository();
		
		r2.setName("r2");
		r2.setUri("file:///repos/r2");
		r2.setProject(p);
		
		projectDao.create(r1);
		projectDao.create(r2);
		
		Assert.assertArrayEquals("Retrieved repositories should equal the created repositories", 
				new Repository[] { r1, r2 }, projectDao.getRepositories(p) );
		
	}
}
