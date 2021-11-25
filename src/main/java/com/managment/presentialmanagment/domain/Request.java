package com.managment.presentialmanagment.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.managment.presentialmanagment.domain.enums.CategoryEnum;
import com.managment.presentialmanagment.domain.enums.PriorityEnum;

@Entity
public class Request implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer priority;
	private LocalDateTime date;
	private Integer category;
	private String optionalMessage;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name= "cellphone_id", nullable = false)
	private Cellphone cellphone;

	public Request() {
		
	}

	public Request(Integer id, PriorityEnum priority, LocalDateTime date, CategoryEnum category, 
			String optionalMessage, User user, Cellphone cellphone) {
		this.id = id;
		this.priority = priority.getCode();
		this.date = date;
		this.category = category.getCode();
		this.optionalMessage = optionalMessage;
		this.user = user;
		this.setCellphone(cellphone);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cellphone getCellphone() {
		return cellphone;
	}

	public void setCellphone(Cellphone cellphone) {
		this.cellphone = cellphone;
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
