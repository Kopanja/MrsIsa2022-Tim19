package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class FishingTour {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private City city;
	private String address;
	private String description;
	private int maxNumOfPeople;
	private double rating;
	private double price;

	public FishingTour() {
		super();
	}

	

	public FishingTour(String name, City city, String address, String description, int maxNumOfPeople, double rating,
			double price) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.description = description;
		this.maxNumOfPeople = maxNumOfPeople;
		this.rating = rating;
		this.price = price;
	}



	public FishingTour(Long id, String name, City city, String address, String description, int maxNumOfPeople,
			double rating, double price) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.description = description;
		this.maxNumOfPeople = maxNumOfPeople;
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

	public int getMaxNumOfPeople() {
		return maxNumOfPeople;
	}

	public void setMaxNumOfPeople(int maxNumOfPeople) {
		this.maxNumOfPeople = maxNumOfPeople;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	@Override
	public String toString() {
		return "FishingTour [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", description="
				+ description + ", maxNumOfPeople=" + maxNumOfPeople + ", rating=" + rating + ", price=" + price + "]";
	}

	
	

}
