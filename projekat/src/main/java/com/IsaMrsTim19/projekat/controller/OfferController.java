package com.IsaMrsTim19.projekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.service.OfferService;

@RestController
@RequestMapping(value="api/offer")
public class OfferController {
	
	@Autowired
	OfferService offerService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Offer>> getAllOffers() {
		List<Offer> allOffers = offerService.getAllOffers();
		return new ResponseEntity<>(allOffers, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{pageNum}", method = RequestMethod.GET)
	public ResponseEntity<List<OfferDTO>> getAllOffers(@PathVariable int pageNum) {
		List<Offer> offersByPage = offerService.getAllOfferByPage(pageNum);
		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		for(Offer offer:offersByPage) {
			dtos.add(offerService.toDTO(offer));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}

}
