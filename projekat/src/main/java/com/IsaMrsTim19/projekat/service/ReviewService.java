package com.IsaMrsTim19.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IsaMrsTim19.projekat.dto.ReviewDTO;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.Owner;
import com.IsaMrsTim19.projekat.model.Review;
import com.IsaMrsTim19.projekat.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	OwnerService ownerService;
	
	@Autowired
	EmailSenderService emailService;
	
	public Review toEntity(ReviewDTO dto) {
		return new Review(dto.getRating(), dto.getReviewText());
	}
	
	public Review save(Review review) {
		return reviewRepo.save(review);
	}

	public List<Review> getReviewsByClientId(Long id) {
		return reviewRepo.getReviewsByClientId(id);
	}
	
	@Transactional
	public double acceptReview(Long id, Offer offer) throws Exception {
		System.out.println(offer);
		Review review = reviewRepo.findById(id).orElse(null);
		System.out.println(review);
		if(review == null) {
			throw new Exception("Something went wrong");
		}
		
		
		
		Owner owner = ownerService.findOwnerByReviewId(id);
		if(owner == null) {
			throw new Exception("Something went wrong");
		}
		
		double newOfferRating = (offer.getRating()*offer.getReviews().size() + review.getRating())/(offer.getReviews().size() + 1);
		review.setAccepted(true);
		reviewRepo.save(review);
		String subject = "You Have A New Review";
		String body = "Review rating: " + review.getRating() + "\n";
		body += "Review text: " + review.getReviewText();
		
		emailService.sendEmail(owner.getEmail(), subject, body);
		
		return newOfferRating;
	}
	
	public void denyReview(Long id) throws Exception {
		Review review = reviewRepo.findById(id).orElse(null);
		if(review == null) {
			throw new Exception("Something went wrong");
		}
		
		reviewRepo.delete(review);
		
	}

	public List<Review> getReviewsByClientEmail(String email) {
		return reviewRepo.getReviewsByClientEmail(email);
	}

	public List<Review> getReviewsThatAreNotAccepted() {
		return reviewRepo.getReviewsThatAreNotAccepted();
	}
}
