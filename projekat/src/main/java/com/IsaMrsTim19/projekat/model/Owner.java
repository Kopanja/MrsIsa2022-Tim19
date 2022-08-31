package com.IsaMrsTim19.projekat.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public class Owner extends User {

	private String ownerType;
	
	@Relationship(type="HAS_OFFER", direction=Direction.OUTGOING)
	private List<Offer> offers;
	
	@Relationship(type="HAS_LOYALTY", direction=Direction.OUTGOING)
	private OwnerLoyalty loyalty;
	
	private int loyaltyPoints;

	public Owner() {
		super();
	}

	public Owner(String ownerType, List<Offer> offers) {
		super();
		this.ownerType = ownerType;
		this.offers = offers;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public OwnerLoyalty getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(OwnerLoyalty loyalty) {
		this.loyalty = loyalty;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	
	
}
