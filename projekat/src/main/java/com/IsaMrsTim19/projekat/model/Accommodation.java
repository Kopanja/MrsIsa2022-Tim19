package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Accommodation extends Offer {

	
	private int roomNumber;
	
	

	public Accommodation() {
		super();
	}


	public Accommodation(int roomNumber) {
		super();
		this.roomNumber = roomNumber;
	}


	public int getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}



	@Override
	public String toString() {
		
		return super.toString() + " Accommodation [roomNumber=" + roomNumber + "]";
	}


}
