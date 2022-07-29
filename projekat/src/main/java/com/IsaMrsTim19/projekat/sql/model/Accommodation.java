package com.IsaMrsTim19.projekat.sql.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Accommodation")
@PrimaryKeyJoinColumn(name="id")
public class Accommodation extends Offer {

	private int roomNumber;

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	
}
