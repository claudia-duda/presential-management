package com.managment.presentialmanagment.dto;

import java.io.Serializable;

public class UserNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String password;
	
	private Integer teamId;

	public UserNewDTO() {
	
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
