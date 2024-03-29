package com.IsaMrsTim19.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.model.Owner;
import com.IsaMrsTim19.projekat.model.OwnerLoyalty;
import com.IsaMrsTim19.projekat.repository.OwnerLoyaltyRepository;
import com.IsaMrsTim19.projekat.repository.OwnerRepository;

@Service
public class OwnerService {

	@Autowired
	OwnerRepository ownerRepo;
	
	@Autowired
	OwnerLoyaltyRepository ownerLoyaltyRepo;
	
	public Owner save(Owner owner) {
		return ownerRepo.save(owner);
	}
	
	public Owner findOwnerByApplicationId(Long applicationId) {
		return ownerRepo.findOwnerByApplicationID(applicationId);
	}
	
	public Owner findOwnerByOfferId(Long offerId) {
		return ownerRepo.findOwnerByOfferID(offerId);
	}
	
	public double getLoyaltyBeneffit(Long ownerId) {
		OwnerLoyalty l = ownerLoyaltyRepo.getLoyaltyBeneffit(ownerId);
		return l.getBenefit();
	}
	
	public void delete(Owner owner) {
		ownerRepo.delete(owner);
	}

	public Owner findOwnerByReviewId(Long id) {
		return ownerRepo.findOwnerByReviewId(id);
	}
}
