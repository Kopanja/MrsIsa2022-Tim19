package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class FishingTour extends Offer {


	private int maxNumOfPeople;

	public FishingTour() {
		super();
	}

	public FishingTour(int maxNumOfPeople) {
		super();
		this.maxNumOfPeople = maxNumOfPeople;
	}

	public int getMaxNumOfPeople() {
		return maxNumOfPeople;
	}

	public void setMaxNumOfPeople(int maxNumOfPeople) {
		this.maxNumOfPeople = maxNumOfPeople;
	}

	
	
	

}
