package com.managment.presentialmanagment.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.domain.Topic;

public class TeamDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private Set<Topic> topics = new HashSet<>();
	
	public TeamDTO() {
		
	}
	
	public TeamDTO(Team team) {
		name = team.getName();
		topics = team.getTopics();
		                        
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	
	
}
