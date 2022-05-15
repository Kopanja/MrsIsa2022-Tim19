package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;


@Node
public class AccommodationAmeneties {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String icon;
	
	@Relationship(type="IS_CHARGE_TYPE", direction=Direction.OUTGOING)
	private ChargeType chargeType;
	
	private double price;
	
	
	public AccommodationAmeneties(String name, String icon, ChargeType chargeType, double price) {
		super();
		this.name = name;
		this.icon = icon;
		this.chargeType = chargeType;
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public ChargeType getChargeType() {
		return chargeType;
	}


	public void setChargeType(ChargeType chargeType) {
		this.chargeType = chargeType;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "AccommodationAmeneties [name=" + name + ", icon=" + icon + ", chargeType=" + chargeType + ", price="
				+ price + "]";
	}
	
	

}
