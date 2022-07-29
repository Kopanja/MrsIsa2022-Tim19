package com.IsaMrsTim19.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.BoatDTO;
import com.IsaMrsTim19.projekat.dto.FishingTourDTO;
import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.model.Boat;
import com.IsaMrsTim19.projekat.model.FishingTour;
import com.IsaMrsTim19.projekat.repository.FishingTourRepository;

@Service
public class FishingTourService {
	
	@Autowired
	FishingTourRepository fishingTourRepo;
	
	@Autowired
	OfferService offerService;
	
	public OfferListByPageDTO getAllFishingToursByPage(int pageNum){
		OfferListByPageDTO offersByPageDTO = new OfferListByPageDTO();
		List<FishingTour> offersByPage = fishingTourRepo.getFishingToursByPage(pageNum);
		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		for(FishingTour offer:offersByPage) {
			OfferDTO dto = this.toDTO(offer);
			dto.setOfferType("fishingTour");
			dtos.add(dto);
		}
		offersByPageDTO.setDtos(dtos);
		offersByPageDTO.setTotalNumberOfPages(this.getNumberOfPages());
		return offersByPageDTO;
	}
	
	
	public int getNumberOfPages() {
		int numOfOffers = fishingTourRepo.getNumberOfFishingTours();
		int numOfPages = numOfOffers/8;
		if(numOfOffers%8 != 0) {
			numOfPages++;
		}
		return numOfPages;
	}
	
	public OfferDTO toDTO(FishingTour offer) {
		String address = offer.getAddress() + ", " + offer.getCity().getName();
		return new OfferDTO(offer.getId(),offer.getName(),address,offer.getDescription(),offer.getRating());
	}
	
	/*
	public FishingTourDTO getDTOById(Long id) {
		FishingTour obj = fishingTourRepo.findById(id).orElse(null);
		FishingTourDTO fishingTourDTO = new FishingTourDTO();
		OfferDTO offerDTO = this.toDTO(obj);
		fishingTourDTO.setOfferDTO(offerDTO);
		fishingTourDTO.setMaxNumOfPeople(obj.getNumOfPeople());
		
		fishingTourDTO.setContentImages(offerService.createImageURLs(obj));
		return fishingTourDTO;
	}
*/
}
