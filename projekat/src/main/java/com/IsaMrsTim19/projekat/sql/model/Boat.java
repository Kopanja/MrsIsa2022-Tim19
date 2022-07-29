package com.IsaMrsTim19.projekat.sql.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="Boat")
@PrimaryKeyJoinColumn(name="id")
public class Boat extends Offer {
	
	
	private double length;
	private int numOfMotors;
	private int motorStrength;
	private int maxSpeed;
	
	
	@ManyToMany
	@JoinTable(
	  name = "nav_equipments", 
	  joinColumns = @JoinColumn(name = "boat_id"), 
	  inverseJoinColumns = @JoinColumn(name = "navigation_equipment_id"))
		Set<NavigationEquipment> navigationEquipment;
	
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public int getNumOfMotors() {
		return numOfMotors;
	}
	public void setNumOfMotors(int numOfMotors) {
		this.numOfMotors = numOfMotors;
	}
	public int getMotorStrength() {
		return motorStrength;
	}
	public void setMotorStrength(int motorStrength) {
		this.motorStrength = motorStrength;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	
	

}
