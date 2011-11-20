package org.merriew.core.dao;

import org.merriew.core.entity.Project;
import org.merriew.core.entity.Repository;

public interface ProjectDao {

	public abstract void create( Project project );
	
	public abstract Project[] findAllProjects();

	public abstract Project getProject(String id);
	
	public abstract void create( Repository repo );
	
	public abstract Repository getRepository( String id );
	
	public abstract Repository[] getRepositories( Project project );
	
}
