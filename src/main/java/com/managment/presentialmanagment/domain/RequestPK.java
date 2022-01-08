package com.managment.presentialmanagment.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RequestPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@ManyToOne
	@JoinColumn(name = "cellphone_id", nullable = false)
	private Cellphone cellphone;

	public Client getClient() {
		return client;
	}

	public void setClient(Client user) {
		this.client = user;
	}

	public Cellphone getCellphone() {
		return cellphone;
	}

	public void setCellphone(Cellphone cellphone) {
		this.cellphone = cellphone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cellphone, client);
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
		RequestPK other = (RequestPK) obj;
		return Objects.equals(cellphone, other.cellphone) && Objects.equals(client, other.client);
	}

	
}
