package com.IsaMrsTim19.projekat.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public class ProfitMargin {

	@Id
	@GeneratedValue
	private Long id;
	private double percentage;
	private boolean active;
	
	public ProfitMargin() {
		super();
	}



	public ProfitMargin(double percentage) {
		super();
		this.percentage = percentage;
	}

	

	public ProfitMargin(double percentage, boolean active) {
		super();
		this.percentage = percentage;
		this.active = active;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public double getPercentage() {
		return percentage;
	}



	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}


	
	
}
