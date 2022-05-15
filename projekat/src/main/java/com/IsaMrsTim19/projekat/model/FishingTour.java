package com.IsaMrsTim19.projekat.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public class FishingTour extends Offer {


	private int maxNumOfPeople;

	@Relationship(type="HAS_FISH_EQUIP", direction=Direction.OUTGOING)
	private List<FishingEquipment> fishingEquipment;
	
	
	public FishingTour() {
		super();
	}

	public FishingTour(int maxNumOfPeople) {
		super();
		this.maxNumOfPeople = maxNumOfPeople;
	}

	
	public FishingTour(int maxNumOfPeople, List<FishingEquipment> fishingEquipment) {
		super();
		this.maxNumOfPeople = maxNumOfPeople;
		this.fishingEquipment = fishingEquipment;
	}

	public int getMaxNumOfPeople() {
		return maxNumOfPeople;
	}

	public void setMaxNumOfPeople(int maxNumOfPeople) {
		this.maxNumOfPeople = maxNumOfPeople;
	}

	public List<FishingEquipment> getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(List<FishingEquipment> fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	@Override
	public String toString() {
		return "FishingTour [maxNumOfPeople=" + maxNumOfPeople + ", fishingEquipment=" + fishingEquipment + "]";
	}

	
	
	
	

}
