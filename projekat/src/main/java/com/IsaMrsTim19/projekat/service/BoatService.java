package com.IsaMrsTim19.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.model.Boat;
import com.IsaMrsTim19.projekat.repository.BoatRepository;


@Service
public class BoatService {
	
	@Autowired
	BoatRepository boatRepo;
	
	
	public OfferListByPageDTO getAllBoatsByPage(int pageNum){
		OfferListByPageDTO offersByPageDTO = new OfferListByPageDTO();
		List<Boat> offersByPage = boatRepo.getBoatsByPage(pageNum);
		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		for(Boat offer:offersByPage) {
			dtos.add(this.toDTO(offer));
		}
		offersByPageDTO.setDtos(dtos);
		offersByPageDTO.setTotalNumberOfPages(this.getNumberOfPages());
		return offersByPageDTO;
	}
	
	
	public int getNumberOfPages() {
		int numOfOffers = boatRepo.getNumberOfBoats();
		int numOfPages = numOfOffers/8;
		if(numOfOffers%8 != 0) {
			numOfPages++;
		}
		return numOfPages;
	}
	
	public OfferDTO toDTO(Boat offer) {
		String address = offer.getAddress() + ", " + offer.getCity().getName();
		return new OfferDTO(offer.getName(),address,offer.getDescription(),offer.getRating());
	}

}
