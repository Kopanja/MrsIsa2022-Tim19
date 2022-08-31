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
public class Reservation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ConvertWith(converter =  DateTimeStringConverter.class)
	private Date dateFrom;
	
	@ConvertWith(converter =  DateTimeStringConverter.class)
	private Date dateTo;
	
	@ConvertWith(converter =  DateTimeStringConverter.class)
	private Date dateCreated;
	
	@Relationship(type="HAS_PROFIT_MARGIN", direction=Direction.OUTGOING)
	private ProfitMargin profitMargin;
	
	private boolean isComplete;
	
	private boolean isCanceled;
	
	private double price;
	
	private double ownerProfit;
	
	private double appProfit;

	
	
	
	
	public Reservation() {
		super();
	}


	public Reservation(Date dateFrom, Date dateTo, double price) {
		super();
		this.isComplete = false;
		this.isCanceled = false;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.price = price;
	}


	public Reservation(Long id, Date dateFrom, Date dateTo, double price) {
		super();
		this.id = id;
		this.isComplete = false;
		this.isCanceled = false;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.price = price;
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


	public boolean isComplete() {
		return isComplete;
	}


	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}


	
	public boolean isCanceled() {
		return isCanceled;
	}


	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	

	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public double getOwnerProfit() {
		return ownerProfit;
	}


	public void setOwnerProfit(double ownerProfit) {
		this.ownerProfit = ownerProfit;
	}


	public double getAppProfit() {
		return appProfit;
	}


	public void setAppProfit(double appProfit) {
		this.appProfit = appProfit;
	}

	
	public ProfitMargin getProfitMargin() {
		return profitMargin;
	}


	public void setProfitMargin(ProfitMargin profitMargin) {
		this.profitMargin = profitMargin;
	}


	@Override
	public String toString() {
		return "Reservation [id=" + id + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", price=" + price + "]";
	}
	
	

}
