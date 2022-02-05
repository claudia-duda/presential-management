package com.managment.presentialmanagment.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cellphone implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Mandatory filling")
	private String model;
	
	@NotEmpty(message = "Mandatory filling")
	@Length(min=3, max=5, message="The lenght must be into 3 and 5 characters")
	private String HWVersion;
	
	@NotEmpty(message = "Mandatory filling")
	@Column(unique = true)
	private String code;
	
	private String imei1;
	private String imei2;
	
	@OneToMany(mappedBy = "id.cellphone")
	private Set<Topic> topics = new HashSet<>();
	
	@OneToMany(mappedBy = "id.cellphone")
	private Set<Request> requests = new HashSet<>();


	public Cellphone() {
		
		
	}

	public Cellphone(Integer id, String model, String hWVersion, String code, String imei1, String imei2) {
	
		this.id = id;
		this.model = model;
		HWVersion = hWVersion;
		this.code = code;
		this.imei1 = imei1;
		this.imei2 = imei2;
	}
	@JsonIgnore
	public List<Team> getTeams(){
		List<Team> list = new ArrayList<>();
		
		for (Topic topic : topics) {
			list.add(topic.getTeam());
		}
		return list;
	}
	@JsonIgnore
	public List<Client> getClients(){
		List<Client> list = new ArrayList<>();
		
		for (Request request : requests) {
			list.add(request.getClient());
		}
		return list;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getHWVersion() {
		return HWVersion;
	}

	public void setHWVersion(String hWVersion) {
		HWVersion = hWVersion;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImei1() {
		return imei1;
	}

	public void setImei1(String imei1) {
		this.imei1 = imei1;
	}

	public String getImei2() {
		return imei2;
	}

	public void setImei2(String imei2) {
		this.imei2 = imei2;
	}
	
	@JsonIgnore
	public Set<Topic> getTopics() {
		return topics;
	}
	
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
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
	
	public void addTopic(Topic topic) {
		this.topics.add(topic);
	}	
	 
	public void addRequest(Request request) {
		this.requests.add(request);
	}
	
	public boolean removeRequest(Request request) {
		for(Request obj : requests) {
			if(request.equals(obj)) {
				this.requests.remove(request);
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
		Cellphone other = (Cellphone) obj;
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
		builder.append("Model: ");
		builder.append(model);
		builder.append(",HW Version: ");
		builder.append(HWVersion);
		builder.append(",Code: ");
		builder.append(code);
		builder.append("\n");
		return builder.toString();
	}
	
	
}
