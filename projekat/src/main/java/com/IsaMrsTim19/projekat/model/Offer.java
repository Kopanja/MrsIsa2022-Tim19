package com.IsaMrsTim19.projekat.model;

import java.util.Date;

import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.IsaMrsTim19.projekat.util.DateStringConverter;

@Node
public class Offer {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@Relationship(type="IS_IN", direction=Direction.OUTGOING)
	private City city;
	
	@ConvertWith(converter =  DateStringConverter.class)
	private Date avaliableFrom;
	
	@ConvertWith(converter =  DateStringConverter.class)
	private Date avaliableUntil;
	
	
	private String address;
	private String description;
	private double rating;
	private double price;
	private String imgFolderPath;
	private String thumbnail;
	
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
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImgFolderPath() {
		return imgFolderPath;
	}
	public void setImgFolderPath(String imgFolderPath) {
		this.imgFolderPath = imgFolderPath;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Date getAvaliableFrom() {
		return avaliableFrom;
	}
	public void setAvaliableFrom(Date avaliableFrom) {
		this.avaliableFrom = avaliableFrom;
	}
	public Date getAvaliableUntil() {
		return avaliableUntil;
	}
	public void setAvaliableUntil(Date avaliableUntil) {
		this.avaliableUntil = avaliableUntil;
	}
	
	
	
	

}
