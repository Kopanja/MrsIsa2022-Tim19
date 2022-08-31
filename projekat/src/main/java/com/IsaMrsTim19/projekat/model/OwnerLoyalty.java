package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class OwnerLoyalty {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private double benefit;
	
	
	
	public OwnerLoyalty() {
		super();
	}



	public OwnerLoyalty(String name, double benefit) {
		super();
		this.name = name;
		this.benefit = benefit;
	}



	public OwnerLoyalty(Long id, String name, double benefit) {
		super();
		this.id = id;
		this.name = name;
		this.benefit = benefit;
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



	public double getBenefit() {
		return benefit;
	}



	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}



	@Override
	public String toString() {
		return "OwnerLoyalty [id=" + id + ", name=" + name + ", benefit=" + benefit + "]";
	}
	
	
	
}
