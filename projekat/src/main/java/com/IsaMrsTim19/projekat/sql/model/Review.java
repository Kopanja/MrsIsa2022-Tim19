package com.IsaMrsTim19.projekat.sql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Review")
public class Review {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="offer_id")
    private Offer offer;
	
	@ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
	
	private double rating;
	
	private boolean isAccepted;
	
	private String reviewText;

	

	public Review(double rating, String reviewText) {
		super();
		this.rating = rating;
		this.reviewText = reviewText;
	}

	public Review() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", offer=" + offer + ", client=" + client + ", rating=" + rating + ", isAccepted="
				+ isAccepted + ", reviewText=" + reviewText + "]";
	}
	
	

}
