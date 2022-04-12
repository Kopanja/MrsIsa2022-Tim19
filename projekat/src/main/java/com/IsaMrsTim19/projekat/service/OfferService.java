package com.IsaMrsTim19.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.repository.OfferRepository;

@Service
public class OfferService {
	
	@Autowired
	OfferRepository offerRepo;
	
	
	public List<Offer> getAllOffers(){
		this.getNumberOfPages();
		return offerRepo.findAll();
	}
	
	public OfferListByPageDTO getAllOfferByPage(int pageNum){
		OfferListByPageDTO offersByPageDTO = new OfferListByPageDTO();
		List<Offer> offersByPage = offerRepo.getOffersByPage(pageNum);
		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		for(Offer offer:offersByPage) {
			dtos.add(this.toDTO(offer));
		}
		offersByPageDTO.setDtos(dtos);
		offersByPageDTO.setTotalNumberOfPages(this.getNumberOfPages());
		return offersByPageDTO;
	}
	
	
	public int getNumberOfPages() {
		int numOfOffers = offerRepo.getNumberOfOffers();
		int numOfPages = numOfOffers/8;
		if(numOfOffers%8 != 0) {
			numOfPages++;
		}
		return numOfPages;
	}
	
	public OfferDTO toDTO(Offer offer) {
		String address = offer.getAddress() + ", " + offer.getCity().getName();
		return new OfferDTO(offer.getName(),address,offer.getDescription(),offer.getRating());
	}

}
