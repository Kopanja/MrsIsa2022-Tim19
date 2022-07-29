package com.IsaMrsTim19.projekat.controller;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.AccommodationDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.service.AccommodationService;
import com.IsaMrsTim19.projekat.service.OfferService;

@RestController
@RequestMapping(value="api/accommodation")
public class AccommodationController {
	
	@Autowired
	AccommodationService accommService;
	
	
	/*
	@RequestMapping(value = "/{pageNum}", method = RequestMethod.GET)
	public ResponseEntity<OfferListByPageDTO> getAccommodationsByPage(@PathVariable String pageNum) {
		int pageNumInt = Integer.parseInt(pageNum);
		OfferListByPageDTO offersByPage = accommService.getAllOfferByPage(pageNumInt);
		return new ResponseEntity<>(offersByPage, HttpStatus.OK);

	}
	*/
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationDTO> getAccommodationById(@PathVariable String id) {
		Long idL = Long.parseLong(id);
		AccommodationDTO dto = accommService.getDTOById(idL);
		return new ResponseEntity<>(dto, HttpStatus.OK);

	}
	

	

}
