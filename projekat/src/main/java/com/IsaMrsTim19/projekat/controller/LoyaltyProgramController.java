package com.IsaMrsTim19.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.service.ProfitMarginService;

@RestController
@RequestMapping(value = "api/loyalty")
public class LoyaltyProgramController {

	
	@Autowired
	ProfitMarginService profitMarginService;
	
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProfitMargin() {
		try {
			profitMarginService.update(0.25);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Profit Margin Updated", HttpStatus.OK);

	}
}
