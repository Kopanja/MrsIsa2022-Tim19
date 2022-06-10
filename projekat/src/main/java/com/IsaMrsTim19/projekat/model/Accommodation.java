package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Accommodation extends Offer {

	
	private int roomNumber;
	private int numberOfPeople;
	

	public Accommodation() {
		super();
	}


	public Accommodation(int roomNumber, int numberOfPeople) {
		super();
		this.roomNumber = roomNumber;
		this.numberOfPeople = numberOfPeople;
	}


	public int getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}


	public int getNumberOfPeople() {
		return numberOfPeople;
	}


	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}


	@Override
	public String toString() {
		
		return super.toString() + " Accommodation [roomNumber=" + roomNumber + ", numberOfPeople=" + numberOfPeople + "name" + getName() + "]";
	}


}
