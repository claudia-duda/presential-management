package com.managment.presentialmanagment.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.managment.presentialmanagment.domain.enums.CategoryEnum;
import com.managment.presentialmanagment.domain.enums.PriorityEnum;

@Entity
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@EmbeddedId
	private RequestPK id = new RequestPK();
	private Integer priority;
	private LocalDateTime date;
	private Integer category;
	private String optionalMessage;


	public Request() {

	}

	public Request(User user, Cellphone cellphone, int priority, LocalDateTime date, int category, String optionalMessage) {
		id.setUser(user);
		id.setCellphone(cellphone);
		this.priority = priority;
		this.date = date;
		this.category = category;
		this.optionalMessage = optionalMessage;
	}

	public RequestPK getId() {
		return id;
	}

	public void setId(RequestPK id) {
		this.id = id;
	}

	public User getUser() {
		return id.getUser();
	}
	
	public Cellphone getCellphone() {
		return id.getCellphone();
	}
	public PriorityEnum getPriority() {
		return PriorityEnum.toEnum(priority);
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority.getCode();
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public CategoryEnum getCategory() {
		return CategoryEnum.toEnum(category);
	}

	public void setCategory(CategoryEnum category) {
		this.category = category.getCode();
	}

	public String getOptionalMessage() {
		return optionalMessage;
	}

	public void setOptionalMessage(String optionalMessage) {
		this.optionalMessage = optionalMessage;
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
		Request other = (Request) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
