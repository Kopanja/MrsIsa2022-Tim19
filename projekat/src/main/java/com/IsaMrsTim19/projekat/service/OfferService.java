package com.IsaMrsTim19.projekat.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.model.Accommodation;
import com.IsaMrsTim19.projekat.model.Boat;
import com.IsaMrsTim19.projekat.model.FishingTour;
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
	
	public OfferDTO getOfferById(Long id) {
		Offer offer = offerRepo.findById(id).orElse(null);
		return this.toDTO(offer);
	}
	
	public Path getOfferThumbnailPath(Long id) {
		Offer offer = offerRepo.findById(id).orElse(null);
		String imgPathFolder = offer.getImgFolderPath();
		String thumbnail = offer.getThumbnail();
		Path imagePath = Paths.get(".\\src\\main\\resources\\images\\" + imgPathFolder + "\\" + thumbnail);
		return imagePath;
	}
	
	public OfferDTO toDTO(Offer offer) {
		String address = offer.getAddress() + ", " + offer.getCity().getName();
		String offerType = null;
		if(offer instanceof Accommodation) {
			offerType = "accommodation";
		}else if (offer instanceof Boat) {
			offerType = "boat";
		}else if (offer instanceof FishingTour) {
			offerType = "fishingTour";
		}
		OfferDTO dto = new OfferDTO(offer.getId(),offer.getName(),address,offer.getDescription(),offer.getRating());
		dto.setOfferType(offerType);
		return dto;
	}
	
	public List<String> createImageURLs(Offer offer){
		List<String> urls = new ArrayList<String>();
		File folder = new File( ".\\src\\main\\resources\\images\\" + offer.getImgFolderPath());
		File[] listOfFiles = folder.listFiles();
		String baseUrl = "http://localhost:8080/api/offer/image/" + offer.getImgFolderPath() + "/";
		String fileName = "";
		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				fileName = listOfFiles[i].getName();
				if(!fileName.equals(offer.getThumbnail())) {
					urls.add(baseUrl + fileName);
				}
			    
			  } 
			}
		return urls;
	}

}
