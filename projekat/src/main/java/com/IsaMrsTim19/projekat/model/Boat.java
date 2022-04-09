package com.IsaMrsTim19.projekat.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Boat {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private double length;
	private int numOfMotors;
	private int motorStrength;
	private int maxSpeed;
	private int capacity;
	private List<NavigationEquipment> navigationEquipment;
	private City city;
	private String address;
	private String description;
	private double rating;

	public Boat() {
		super();
	}

	public Boat(String name, double length, int numOfMotors, int motorStrength, int maxSpeed, int capacity,
			List<NavigationEquipment> navigationEquipment, City city, String address, String description,
			double rating) {
		super();
		this.name = name;
		this.length = length;
		this.numOfMotors = numOfMotors;
		this.motorStrength = motorStrength;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
		this.navigationEquipment = navigationEquipment;
		this.city = city;
		this.address = address;
		this.description = description;
		this.rating = rating;
	}

	public Boat(Long id, String name, double length, int numOfMotors, int motorStrength, int maxSpeed, int capacity,
			List<NavigationEquipment> navigationEquipment, City city, String address, String description,
			double rating) {
		super();
		this.id = id;
		this.name = name;
		this.length = length;
		this.numOfMotors = numOfMotors;
		this.motorStrength = motorStrength;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
		this.navigationEquipment = navigationEquipment;
		this.city = city;
		this.address = address;
		this.description = description;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setNumOfMottors(int numOfMotors) {
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<NavigationEquipment> getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(List<NavigationEquipment> navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Boat [id=" + id + ", name=" + name + ", length=" + length + ", numOfMottors=" + numOfMotors
				+ ", motorStrength=" + motorStrength + ", maxSpeed=" + maxSpeed + ", capacity=" + capacity
				+ ", navigationEquipment=" + navigationEquipment + ", city=" + city + ", address=" + address
				+ ", description=" + description + ", rating=" + rating + "]";
	}

}
