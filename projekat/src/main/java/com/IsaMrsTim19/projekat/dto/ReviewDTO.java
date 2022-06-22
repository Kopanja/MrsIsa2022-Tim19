package com.IsaMrsTim19.projekat.dto;

public class ReviewDTO {

	private double rating;
	
	private String reviewText;

	public ReviewDTO() {
		super();
	}

	public ReviewDTO(double rating, String reviewText) {
		super();
		this.rating = rating;
		this.reviewText = reviewText;
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
	
	
}
