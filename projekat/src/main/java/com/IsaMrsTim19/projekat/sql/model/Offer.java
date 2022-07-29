package com.IsaMrsTim19.projekat.sql.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.neo4j.core.convert.ConvertWith;

import com.IsaMrsTim19.projekat.util.DateTimeStringConverter;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Offer")
public class Offer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private City city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Owner owner;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
	private Set<Promotion> promotions;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
	private Set<Reservation> reservations;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
	private Set<Review> reviews;
	
	@ManyToMany
	@JoinTable(
	  name = "subscription", 
	  joinColumns = @JoinColumn(name = "offer_id"), 
	  inverseJoinColumns = @JoinColumn(name = "client_id"))
	Set<Client> subscriptions;
	
	private String name;
	private Date avaliableFrom;
	
	private Date avaliableUntil;
	private String address;
	private String description;
	private double rating;
	private double price;
	private String imgFolderPath;
	private String thumbnail;
	private int numOfPeople;

	public Offer() {
		super();
	}

	public Offer(Long id, City city, String name) {
		super();
		this.id = id;
		this.city = city;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Set<Client> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Client> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
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

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
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

	/*
	@Override
	public String toString() {
		return "Offer [id=" + id + ", city=" + city + ", owner=" + owner + ", promotions=" + promotions
				+ ", reservations=" + reservations + ", reviews=" + reviews + ", subscriptions=" + subscriptions
				+ ", name=" + name + ", avaliableFrom=" + avaliableFrom + ", avaliableUntil=" + avaliableUntil
				+ ", address=" + address + ", description=" + description + ", rating=" + rating + ", price=" + price
				+ ", imgFolderPath=" + imgFolderPath + ", thumbnail=" + thumbnail + ", numOfPeople=" + numOfPeople
				+ "]";
	}
	
	*/
	
	
	
}
