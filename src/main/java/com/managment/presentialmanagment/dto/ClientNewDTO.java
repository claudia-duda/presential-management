package com.managment.presentialmanagment.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ClientNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Mandatory filling")
	@Length(min=5, max=120, message="The lenght must be into 5 and 120 characters")
	private String name;
	
	@NotEmpty(message = "Mandatory filling")
	@Email(message = "Invalid Email")
	private String email;
	
	@NotEmpty(message = "Mandatory filling")
	private String password;
	
	
	private Integer teamId;

	public ClientNewDTO() {
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	

}
