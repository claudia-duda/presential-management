package com.managment.presentialmanagment.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.managment.presentialmanagment.domain.Team;

public class TeamDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Mandatory filling")
	@Length(min=3, max=20, message = "The lenght must be into 2 and 20 characters")
	private String name;
	
	public TeamDTO() {
		
	}
	
	public TeamDTO(Team team) {
		id = team.getId();
		name = team.getName();
		
		                        
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
