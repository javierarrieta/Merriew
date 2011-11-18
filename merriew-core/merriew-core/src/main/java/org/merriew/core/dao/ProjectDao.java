package org.merriew.core.dao;

import org.merriew.core.entity.Project;

public interface ProjectDao {

	public abstract void create( Project project );
	
	public abstract Project[] findAllProjects();

	public abstract Project getProject(String id);
	
}
