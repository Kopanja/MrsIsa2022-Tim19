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
public class Offer {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@Relationship(type="IS_IN", direction=Direction.OUTGOING)
	private City city;
	
	@Relationship(type="HAS_RESERVATION", direction=Direction.OUTGOING)
	private List<Reservation> reservations;
	
	@Relationship(type="HAS_SUBSCRIBER", direction=Direction.OUTGOING)
	private List<Client> subscribers;
	
	@Relationship(type="HAS_PROMOTION", direction=Direction.OUTGOING)
	private List<Promotion> promotions;
	
	@Relationship(type="HAS_SERVICE", direction=Direction.OUTGOING)
	private List<AdditionalService> additionalServices;
	
	@Relationship(type="HAS_REVIEW", direction=Direction.OUTGOING)
	private List<Review> reviews;
	
	@ConvertWith(converter =  DateTimeStringConverter.class)
	private Date avaliableFrom;
	
	@ConvertWith(converter =  DateTimeStringConverter.class)
	private Date avaliableUntil;
	
	
	private String address;
	private String description;
	private double rating;
	private double price;
	private String imgFolderPath;
	private String thumbnail;
	private int numOfPeople;
	
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
	public int getNumOfPeople() {
		return numOfPeople;
	}
	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	public List<Client> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(List<Client> subscribers) {
		this.subscribers = subscribers;
	}
	public List<Promotion> getPromotions() {
		return promotions;
	}
	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	public List<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(List<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", city=" + city + ", reservations=" + reservations
				+ ", subscribers=" + subscribers + ", promotions=" + promotions + ", additionalServices="
				+ additionalServices + ", reviews=" + reviews + ", avaliableFrom=" + avaliableFrom + ", avaliableUntil="
				+ avaliableUntil + ", address=" + address + ", description=" + description + ", rating=" + rating
				+ ", price=" + price + ", imgFolderPath=" + imgFolderPath + ", thumbnail=" + thumbnail
				+ ", numOfPeople=" + numOfPeople + "]";
	}
	

	
	
	
	

}
