package com.IsaMrsTim19.projekat.sql.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Offer")
public class Offer {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	@ManyToOne
	private City city;
	
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
	
	
	
	
	
}
