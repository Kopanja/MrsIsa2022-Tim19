package com.IsaMrsTim19.projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.Review;
import com.IsaMrsTim19.projekat.service.OfferService;
import com.IsaMrsTim19.projekat.service.ReviewService;

@RestController
@RequestMapping(value="api/review")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	OfferService offerService;
	
	@RequestMapping(value = "/{id}/accept", method = RequestMethod.PUT)
	public ResponseEntity<?> acceptReview(@PathVariable Long id) {

		try {
			Offer offer = offerService.findOfferByReviewId(id);
			double newRating = reviewService.acceptReview(id, offer);
			offer.setRating(newRating);
			offerService.save(offer);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Subscribed", HttpStatus.OK);

	}
	
	@RequestMapping(value = "/client/{email}", method = RequestMethod.GET)
	public ResponseEntity<?> getReviewsByClientEmail(@PathVariable String email) {
		List<Review> reviews = null;
		try {
			reviews = reviewService.getReviewsByClientEmail(email);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(reviews, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}/decline", method = RequestMethod.DELETE)
	public ResponseEntity<?> declineReview(@PathVariable Long id) {

		try {
			reviewService.denyReview(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Subscribed", HttpStatus.OK);

	}

}
