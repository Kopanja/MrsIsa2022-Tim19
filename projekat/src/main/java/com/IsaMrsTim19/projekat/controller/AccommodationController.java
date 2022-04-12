package com.IsaMrsTim19.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.service.AccommodationService;
import com.IsaMrsTim19.projekat.service.OfferService;

@RestController
@RequestMapping(value="api/accommodation")
public class AccommodationController {
	
	@Autowired
	AccommodationService accommService;
	
	
	@RequestMapping(value = "/{pageNum}", method = RequestMethod.GET)
	public ResponseEntity<OfferListByPageDTO> getAllOffers(@PathVariable int pageNum) {
		OfferListByPageDTO offersByPage = accommService.getAllOfferByPage(pageNum);
		System.out.println(offersByPage.getDtos().size());
		System.out.println(offersByPage);
		return new ResponseEntity<>(offersByPage, HttpStatus.OK);

	}

}
