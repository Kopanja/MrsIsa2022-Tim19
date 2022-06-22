package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class AdditionalService {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String type;
	private double price;
	
	public AdditionalService() {
		super();
	}

	public AdditionalService(String name, String type, double price) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public AdditionalService(Long id, String name, String type, double price) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AdditionalService [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + "]";
	}
	
	
	
}
