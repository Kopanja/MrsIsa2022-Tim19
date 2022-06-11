package com.IsaMrsTim19.projekat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.AccommodationDTO;
import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.model.Accommodation;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.repository.AccommodationRepository;

@Service
public class AccommodationService {

	
	@Autowired
	AccommodationRepository accommRepo;
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	QueryService queryService;
	
	public OfferListByPageDTO getAllOfferByPage(int pageNum){
		OfferListByPageDTO offersByPageDTO = new OfferListByPageDTO();
		List<Accommodation> offersByPage = accommRepo.getAccommodationsByPage(pageNum);
		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		for(Accommodation offer:offersByPage) {
			OfferDTO dto = this.toDTO(offer);
			dto.setOfferType("accommodation");
			dtos.add(dto);
		}
		offersByPageDTO.setDtos(dtos);
		offersByPageDTO.setTotalNumberOfPages(this.getNumberOfPages());
		return offersByPageDTO;
	}
	
	
	public int getNumberOfPages() {
		int numOfOffers = accommRepo.getNumberOfAccommodation();
		int numOfPages = numOfOffers/8;
		if(numOfOffers%8 != 0) {
			numOfPages++;
		}
		return numOfPages;
	}
	
	public OfferDTO toDTO(Accommodation offer) {
		String address = offer.getAddress() + ", " + offer.getCity().getName();
		return new OfferDTO(offer.getId(),offer.getName(),address,offer.getDescription(),offer.getRating());
	}
	
	
	public AccommodationDTO getDTOById(Long id) {
		Accommodation obj = accommRepo.findById(id).orElse(null);
		AccommodationDTO accDTO = new AccommodationDTO();
		OfferDTO offerDTO = this.toDTO(obj);
		accDTO.setOfferDTO(offerDTO);
		accDTO.setNumberOfPeople(obj.getNumOfPeople());
		accDTO.setRoomNumber(obj.getRoomNumber());
		accDTO.setContentImages(offerService.createImageURLs(obj));
		return accDTO;
	}
	
	
}
