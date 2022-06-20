package com.IsaMrsTim19.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.RejectionDTO;
import com.IsaMrsTim19.projekat.service.OwnerApplicationService;

@RestController
@RequestMapping(value="api/owner-application")
public class OwnerApplicationController {

	@Autowired
	OwnerApplicationService ownerAppService;
	
	@RequestMapping(value = "/{id}/decline", method = RequestMethod.POST)
	public ResponseEntity<?> declineApplication(@PathVariable Long id, @RequestBody RejectionDTO rejection) {
		
		ownerAppService.declineApplication(id, rejection.getRejectionReason());
		return new ResponseEntity<>("ok", HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}/accept", method = RequestMethod.GET)
	public ResponseEntity<?> acceptApplication(@PathVariable Long id) {
		
		ownerAppService.acceptApplication(id);
		return new ResponseEntity<>("ok", HttpStatus.OK);

	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		
		ownerAppService.findAll();
		return new ResponseEntity<>(ownerAppService.findAll(), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		System.out.println(id);
		return new ResponseEntity<>(ownerAppService.findById(id), HttpStatus.OK);

	}
}
