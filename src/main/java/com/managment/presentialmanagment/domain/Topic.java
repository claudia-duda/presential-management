package com.managment.presentialmanagment.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topic implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private TopicPK id = new TopicPK();

	private LocalDateTime initialDateUsage;
	private LocalDateTime finalDateUsage;

	public Topic() {
		
	}
	
	public Topic( Cellphone cellphone, Team team, LocalDateTime initialDateUsage, LocalDateTime finalDateUsage) {
		
		id.setCellphone(cellphone);
		id.setTeam(team);
		this.initialDateUsage = initialDateUsage;
		this.finalDateUsage = finalDateUsage;
	}
	public TopicPK getId() {
		return id;
	}
	public void setId(TopicPK id) {
		this.id = id;
	}
	public LocalDateTime getInitialDateUsage() {
		return initialDateUsage;
	}
	public void setInitialDateUsage(LocalDateTime initialDateUsage) {
		this.initialDateUsage = initialDateUsage;
	}
	public LocalDateTime getFinalDateUsage() {
		return finalDateUsage;
	}
	public void setFinalDateUsage(LocalDateTime finalDateUsage) {
		this.finalDateUsage = finalDateUsage;
	}
	public Cellphone getCellphone() {
		return id.getCellphone();
	}
	@JsonIgnore
	public Team getTeam() {
		return id.getTeam();
	}
	public void setCellphone(Cellphone cellphone) {
		id.setCellphone(cellphone);
	}
	public void setTeam(Team team) {
		id.setTeam(team);
	}


	@Override
	public int hashCode() {
		return Objects.hash(finalDateUsage, id, initialDateUsage);
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
		Topic other = (Topic) obj;
		return Objects.equals(finalDateUsage, other.finalDateUsage) && Objects.equals(id, other.id)
				&& Objects.equals(initialDateUsage, other.initialDateUsage);
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Model DUT: "+getCellphone().getModel());
		builder.append(", Initial Date Usage: "+ initialDateUsage.format(formatter));
		builder.append(", Final Date Usage: "+ finalDateUsage.format(formatter));
		builder.append("\n");
		return builder.toString();
	}
	
	
}
