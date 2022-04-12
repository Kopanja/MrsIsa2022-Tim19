package com.IsaMrsTim19.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.service.FishingTourService;


@RestController
@RequestMapping(value="api/fishingTour")
public class FishingTourController {
	
	@Autowired
	FishingTourService fishingTourService;
	
	@RequestMapping(value = "/{pageNum}", method = RequestMethod.GET)
	public ResponseEntity<OfferListByPageDTO> getAllOffers(@PathVariable int pageNum) {
		OfferListByPageDTO offersByPage = fishingTourService.getAllFishingToursByPage(pageNum);
		return new ResponseEntity<>(offersByPage, HttpStatus.OK);

	}

}
