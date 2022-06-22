package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	
	private double rating;
	
	private boolean isAccepted;
	
	private String reviewText;

	public Review() {
		super();
	}

	public Review(double rating, String reviewText) {
		super();
		this.isAccepted = false;
		this.rating = rating;
		this.reviewText = reviewText;
	}

	public Review(Long id, double rating, String reviewText) {
		super();
		this.id = id;
		this.rating = rating;
		this.reviewText = reviewText;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	
	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", reviewText=" + reviewText + "]";
	}
	
	
	
}
