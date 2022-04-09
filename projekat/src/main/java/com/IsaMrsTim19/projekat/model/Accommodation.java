package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Accommodation {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private City city;
	private String address;
	private String description;
	private int roomNumber;
	private int numberOfPeople;
	private double rating;
	private double price;

	public Accommodation() {
		super();
	}

	public Accommodation(Long id, String name, City city, String address, String description, int roomNumber,
			int numberOfPeople, double rating, double price) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.description = description;
		this.roomNumber = roomNumber;
		this.numberOfPeople = numberOfPeople;
		this.rating = rating;
		this.price = price;
	}

	public Accommodation(String name, City city, String address, String description, int roomNumber, int numberOfPeople,
			double rating, double price) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.description = description;
		this.roomNumber = roomNumber;
		this.numberOfPeople = numberOfPeople;
		this.rating = rating;
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Accommodation [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address
				+ ", description=" + description + ", roomNumber=" + roomNumber + ", numberOfPeople=" + numberOfPeople
				+ ", rating=" + rating + ", price=" + price + "]";
	}

}
