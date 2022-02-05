package com.managment.presentialmanagment.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.managment.presentialmanagment.domain.enums.CategoryEnum;
import com.managment.presentialmanagment.domain.enums.PriorityEnum;
import com.managment.presentialmanagment.domain.enums.StateEnum;

@Entity
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private RequestPK id = new RequestPK();
	private Integer priority;
	private LocalDateTime date;
	
	private Integer category;
	private Integer state;
	
	@Length(min=5, max=200, message="The lenght must be into 5 and 200 characters")
	private String optionalMessage;
	
	private LocalDateTime limitDate;

	public Request() {

	}

	public Request(Client user, Cellphone cellphone, int priority, LocalDateTime date, int category, 
			int state,String optionalMessage, LocalDateTime limitDate) {
		id.setClient(user);
		id.setCellphone(cellphone);
		this.priority = priority;
		this.date = date;
		this.category = category;
		this.state = state;
		this.optionalMessage = optionalMessage;
		this.limitDate = limitDate;
	}
	
	public RequestPK getId() {
		return id;
	}

	public void setId(RequestPK id) {
		this.id = id;
	}

	public Client getClient() {
		return id.getClient();
	}

	public void setClient(Client client) {
		id.setClient(client);
	}

	public Cellphone getCellphone() {
		return id.getCellphone();
	}

	public void setCellphone(Cellphone cellphone) {
		id.setCellphone(cellphone);
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

	public StateEnum getState() {
		return StateEnum.toEnum(state);
	}

	public void setState(StateEnum state) {
		this.state = state.getCode();
	}

	public String getOptionalMessage() {
		return optionalMessage;
	}

	public void setOptionalMessage(String optionalMessage) {
		this.optionalMessage = optionalMessage;
	}

	public LocalDateTime getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(LocalDateTime limitDate) {
		this.limitDate = limitDate;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(category, date, id, limitDate, optionalMessage, priority, state);
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
		return Objects.equals(category, other.category) && Objects.equals(date, other.date)
				&& Objects.equals(id, other.id) && Objects.equals(limitDate, other.limitDate)
				&& Objects.equals(optionalMessage, other.optionalMessage) && Objects.equals(priority, other.priority)
				&& Objects.equals(state, other.state);
	}

	@Override
	public String toString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");

		StringBuilder builder = new StringBuilder();
		builder.append(",Date: ");
		builder.append(date.format(formatter));
		builder.append("Priority: ");
		builder.append(getPriority().getDescription());
		builder.append(",Category: ");
		builder.append(getCategory().getDescription());
		builder.append(",Limit Date: ");
		builder.append(limitDate);		
		builder.append(",DUT code: ");
		builder.append(getCellphone().getCode());
		builder.append(",Optional Message: ");
		builder.append(optionalMessage);
		builder.append(",State: ");
		builder.append(getState().getDescription());
		builder.append(",Requested by: ");	
		builder.append(getClient().getName());
		builder.append("\n");
		return builder.toString();
	}

}
