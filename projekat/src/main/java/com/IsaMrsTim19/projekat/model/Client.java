package com.IsaMrsTim19.projekat.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public class Client extends User {

	@Relationship(type="MADE_RESERVATION", direction=Direction.OUTGOING)
	private List<Reservation> reservations;

	public Client() {
		super();
	}

	public Client(List<Reservation> reservations) {
		super();
		this.reservations = reservations;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
	
}
