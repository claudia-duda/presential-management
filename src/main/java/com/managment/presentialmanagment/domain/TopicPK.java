package com.managment.presentialmanagment.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TopicPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cellphone_id", nullable = false)
	private Cellphone cellphone;
	
	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
	private Team team;
	
	public Cellphone getCellphone() {
		return cellphone;
	}
	public void setCellphone(Cellphone cellphone) {
		this.cellphone = cellphone;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cellphone == null) ? 0 : cellphone.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		TopicPK other = (TopicPK) obj;
		if (cellphone == null) {
			if (other.cellphone != null) {
				return false;
			}
		} else if (!cellphone.equals(other.cellphone)) {
			return false;
		}
		if (team == null) {
			if (other.team != null) {
				return false;
			}
		} else if (!team.equals(other.team)) {
			return false;
		}
		return true;
	}
	
}
