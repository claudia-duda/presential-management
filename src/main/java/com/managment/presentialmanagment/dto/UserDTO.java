 package com.managment.presentialmanagment.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.managment.presentialmanagment.domain.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Mandatory filling")
	@Length(min=5, max=120, message="The lenght must be into 5 and 120 characters")
	private String name;
	
	@NotEmpty(message = "Mandatory filling")
	@Email(message = "Invalid Email")
	private String email;
	
	public UserDTO() {
		
	}
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
