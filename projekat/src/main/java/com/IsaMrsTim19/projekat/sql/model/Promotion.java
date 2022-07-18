package com.IsaMrsTim19.projekat.sql.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Promotion")
public class Promotion {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	private Date dateFrom;
	
	
	private Date dateTo;

	@ManyToOne
    @JoinColumn(name="offer_id")
    private Offer offer;

	public Promotion() {
		super();
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
	
	
	

}
