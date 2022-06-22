package com.IsaMrsTim19.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.DeletionRequestDTO;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.service.DeletionRequestService;

@RestController
@RequestMapping(value="api/deletion-request")
public class DeletionRequestController {

	@Autowired
	DeletionRequestService delReqService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> deletionRequest(@RequestBody DeletionRequestDTO dto) {

		User user = null;
		try {
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			delReqService.createRequest(dto, user);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Subscribed", HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}/accept", method = RequestMethod.DELETE)
	public ResponseEntity<?> accept(@PathVariable Long id) {

		try {
			delReqService.deleteUser(id);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Subscribed", HttpStatus.OK);

	}
}
