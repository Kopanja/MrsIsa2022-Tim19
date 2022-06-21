package com.IsaMrsTim19.projekat.model;

import java.util.Date;

import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

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

	public Promotion() {
		super();
	}

	public Promotion(Date dateFrom, Date dateTo) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
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
