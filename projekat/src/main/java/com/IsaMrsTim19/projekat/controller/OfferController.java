package com.IsaMrsTim19.projekat.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
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
	public ResponseEntity<OfferListByPageDTO> getAllOffers(@PathVariable Integer pageNum) {
		
		OfferListByPageDTO offersByPage = offerService.getAllOfferByPage(pageNum);
		return new ResponseEntity<>(offersByPage, HttpStatus.OK);

	}
	
	@GetMapping("/{id}/thumbnail")
	public ResponseEntity<?> getProfileImage(@PathVariable Long id) {
		try {
			Path imagePath = offerService.getOfferThumbnailPath(id);
			if (imagePath != null) {
				Resource resource = new ByteArrayResource(Files.readAllBytes(imagePath.normalize()));

				return ResponseEntity.ok().contentLength(imagePath.toFile().length()).contentType(MediaType.IMAGE_JPEG)
						.body(resource);

			} else {

				return ResponseEntity.status(HttpStatus.OK).build();
			}
			
		} catch (Exception e) {
			System.out.println("Doslo je do greske");

		}
		System.out.println("Ovde");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
