package org.merriew.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="project")
@NamedQueries( 
    @NamedQuery(name="Project.findAll", query="select p from Project p")
)
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 624896015418142786L;
	
	@Id
	private String id;

	@Column(nullable=false,unique=true)
	private String name;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
