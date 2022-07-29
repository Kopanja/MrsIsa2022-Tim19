package com.IsaMrsTim19.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.AccommodationDTO;
import com.IsaMrsTim19.projekat.dto.BoatDTO;
import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.model.Accommodation;
import com.IsaMrsTim19.projekat.model.Boat;
import com.IsaMrsTim19.projekat.model.NavigationEquipment;
import com.IsaMrsTim19.projekat.repository.BoatRepository;


@Service
public class BoatService {
	
	@Autowired
	BoatRepository boatRepo;
	
	@Autowired
	OfferService offerService;
	
	public OfferListByPageDTO getAllBoatsByPage(int pageNum){
		OfferListByPageDTO offersByPageDTO = new OfferListByPageDTO();
		List<Boat> offersByPage = boatRepo.getBoatsByPage(pageNum);
		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		for(Boat offer:offersByPage) {
			OfferDTO dto = this.toDTO(offer);
			dto.setOfferType("boat");
			dtos.add(dto);
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
		return new OfferDTO(offer.getId(),offer.getName(),address,offer.getDescription(),offer.getRating());
	}
	
	private List<String> getNavEquipement(Boat boat){
		List<String> equipement = new ArrayList<String>();
		for(NavigationEquipment eq : boat.getNavigationEquipment()) {
			equipement.add(eq.getName());
		}
		
		return equipement;
	}
	/*
	public BoatDTO getDTOById(Long id) {
		Boat obj = boatRepo.findById(id).orElse(null);
		System.out.println(obj);
		BoatDTO boatDTO = new BoatDTO();
		OfferDTO offerDTO = this.toDTO(obj);
		boatDTO.setOfferDTO(offerDTO);
		boatDTO.setCapacity(obj.getNumOfPeople());
		boatDTO.setLength(obj.getLength());
		boatDTO.setMaxSpeed(obj.getMaxSpeed());
		boatDTO.setMotorStrength(obj.getMotorStrength());
		boatDTO.setNumOfMotors(obj.getNumOfMotors());
		boatDTO.setContentImages(offerService.createImageURLs(obj));
		boatDTO.setEquipment(this.getNavEquipement(obj));
		return boatDTO;
	}
*/
}
