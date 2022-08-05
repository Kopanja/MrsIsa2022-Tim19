package com.IsaMrsTim19.projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.DeletionRequestDTO;
import com.IsaMrsTim19.projekat.model.DeletionRequest;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.service.DeletionRequestService;

@RestController
@RequestMapping(value="api/deletion-request")
public class DeletionRequestController {

	@Autowired
	DeletionRequestService delReqService;
	
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(){
		List<DeletionRequestDTO> req = null;
		try {
			req = delReqService.findAllDTO();
			
			
		
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		System.out.println(req);
		
		return new ResponseEntity<>(req, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'OWNER')")
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
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
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
