package com.IsaMrsTim19.projekat.dto;


import java.util.List;

import com.IsaMrsTim19.projekat.model.AdditionalService;
import com.IsaMrsTim19.projekat.model.City;

public class OfferDTO {

	private Long id;
	private String name;
	private String address;
	private String description;
	private String offerType;
	private double rating;
	private double price;
	private List<AdditionalService> additionalServices;
	
	
	
	public OfferDTO() {
		super();
	}

	
	public OfferDTO(Long id, String name, String address, String description, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rating = rating;
	}

	

	public OfferDTO(Long id, String name, String address, String description,  double rating,
			double price) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rating = rating;
		this.price = price;
	}


	public OfferDTO(String name, String address, String description, double rating) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.rating = rating;
	}

	
	public OfferDTO(Long id, String name, String address, String description, String offerType, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.offerType = offerType;
		this.rating = rating;
	}


	public String getOfferType() {
		return offerType;
	}


	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	
	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	
	public List<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}


	public void setAdditionalServices(List<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}


	@Override
	public String toString() {
		return "OfferDTO [name=" + name + ", address=" + address + ", rating=" + rating
				+ "]";
	}
	
	
	
}
