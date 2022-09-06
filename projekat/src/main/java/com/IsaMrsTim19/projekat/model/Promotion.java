package com.IsaMrsTim19.projekat.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.IsaMrsTim19.projekat.util.DateTimeStringConverter;

@Node
public class Promotion {

	@Id
	@GeneratedValue
	private Long id;
	
	@ConvertWith(converter =  DateTimeStringConverter.class)
	private Date dateFrom;
	
	@ConvertWith(converter =  DateTimeStringConverter.class)
	private Date dateTo;
	
	@Relationship(type="HAS_SERVICE", direction=Direction.OUTGOING)
	private List<AdditionalService> additionalServices;
	
	private double price;

	public Promotion() {
		super();
	}

	public Promotion(Date dateFrom, Date dateTo) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	
	public Promotion(Long id, Date dateFrom, Date dateTo, double price) {
		super();
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.price = price;
	}

	
	
	public Promotion(Date dateFrom, Date dateTo, List<AdditionalService> additionalServices, double price) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.additionalServices = additionalServices;
		this.price = price;
	}

	public Promotion(Date dateFrom, Date dateTo, double price) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.price = price;
	}

	public Promotion(Long id, Date dateFrom, Date dateTo) {
		super();
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", additionalServices="
				+ additionalServices + ", price=" + price + "]";
	}
	
	
	
}
