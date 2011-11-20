package org.merriew.core.entity.users;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.merriew.core.entity.Repository;

@Entity
@Table(name="user_mapping")
public class UserCommitterMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7080710342232951565L;

	@Id
	private String id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
