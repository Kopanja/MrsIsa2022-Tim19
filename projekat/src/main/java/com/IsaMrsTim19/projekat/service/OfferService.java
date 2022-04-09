package com.IsaMrsTim19.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.repository.OfferRepository;

@Service
public class OfferService {
	
	@Autowired
	OfferRepository offerRepo;
	
	
	public List<Offer> getAllOffers(){
		System.out.println();
		return offerRepo.findAll();
	}
	
	public List<Offer> getAllOfferByPage(int pageNum){
		return offerRepo.getOffersByPage(pageNum);
	}
	
	
	public OfferDTO toDTO(Offer offer) {
		String address = offer.getAddress() + ", " + offer.getCity().getName();
		return new OfferDTO(offer.getName(),address,offer.getDescription(),offer.getRating());
	}

}
