package com.IsaMrsTim19.projekat.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public class Boat extends Offer {

	
	private double length;
	private int numOfMotors;
	private int motorStrength;
	private int maxSpeed;
	@Relationship(type="HAS_NAV_EQUIP", direction=Direction.OUTGOING)
	private List<NavigationEquipment> navigationEquipment;

	public Boat() {
		super();
	}

	public Boat(double length, int numOfMotors, int motorStrength, int maxSpeed,
			List<NavigationEquipment> navigationEquipment) {
		super();
		this.length = length;
		this.numOfMotors = numOfMotors;
		this.motorStrength = motorStrength;
		this.maxSpeed = maxSpeed;
		this.navigationEquipment = navigationEquipment;
	}

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


	public List<NavigationEquipment> getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(List<NavigationEquipment> navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	@Override
	public String toString() {
		return super.toString() + " Boat [length=" + length + ", numOfMotors=" + numOfMotors + ", motorStrength=" + motorStrength
				+ ", maxSpeed=" + maxSpeed + ", navigationEquipment=" + navigationEquipment
				+ "]";
	}

	

}
