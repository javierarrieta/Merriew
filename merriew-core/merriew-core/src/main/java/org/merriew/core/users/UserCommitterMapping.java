package org.merriew.core.users;

import java.io.Serializable;

import org.merriew.core.Repository;

public class UserCommitterMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7080710342232951565L;

	private User user;
	
	private Repository repository;
	
	private String[] committers;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public String[] getCommitters() {
		return committers;
	}

	public void setCommitters(String[] committers) {
		this.committers = committers;
	}
	
	
}
