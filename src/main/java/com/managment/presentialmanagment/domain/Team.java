package com.managment.presentialmanagment.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Team implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
	private List<Client> clients = new ArrayList<>();
	
	@OneToMany(mappedBy = "id.team")
	@ElementCollection
	private Set<Topic> topics = new HashSet<>();

	public Team() {
		
	}

	public Team(Integer id, String name) {
		
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	public List<Cellphone> getCellphones(){
		List<Cellphone> list = new ArrayList<>();
		
		for (Topic topic : topics) {
			list.add(topic.getCellphone());
		}
		return list;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Client> getUsers() {
		return clients;
	}

	public void addUsers(Client user) {
		this.clients.add(user);
	}
	
	public void removeUser(Client user) {
		this.clients.remove(user);
	}
	
	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	
	public void addTopic(Topic topic) {
		this.topics.add(topic);
	}
	
	public boolean removeTopic(Topic topic) {
		for(Topic obj : topics) {
			if(topic.equals(obj)) {
				this.topics.remove(topic);
				return true;
			}
	     }
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Team other = (Team) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Team: ");
		builder.append(name);
		builder.append(", Topics: ");
		for( Topic topic: topics) {
			builder.append(topic.toString());
		}
		builder.append(topics.toString());
		builder.append("\n");
		return builder.toString();
	}
	
}

