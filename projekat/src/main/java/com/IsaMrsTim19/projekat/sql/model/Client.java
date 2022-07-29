package com.IsaMrsTim19.projekat.sql.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Client extends User {
	
	@ManyToMany(mappedBy = "subscriptions")
	Set<Offer> subscriptions;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Set<Reservation> reservations;
	
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Set<Review> reviews;

	
	
	public Set<Offer> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Offer> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
	
	

}
