package com.IsaMrsTim19.projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.service.CityService;

@RestController
@RequestMapping(value="api/city")
public class CityController {
	
	@Autowired
	CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllOffers() {
		List<String> allCities = cityService.getAllCities();
		return new ResponseEntity<>(allCities, HttpStatus.OK);

	}

}
