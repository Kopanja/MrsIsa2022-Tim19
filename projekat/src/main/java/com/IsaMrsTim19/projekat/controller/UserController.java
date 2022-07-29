package com.IsaMrsTim19.projekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.UserDTO;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.Reservation;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.service.UserService;
import com.IsaMrsTim19.projekat.sql.model.Client;
import com.IsaMrsTim19.projekat.sql.repository.Client2Repository;
import com.IsaMrsTim19.projekat.sql.repository.UserSQLRepository;

@RestController
@RequestMapping(value="api/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserSQLRepository sqlUserRepo;

	
	@Autowired
	Client2Repository sqlClientRepo;
	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
		User user;
		try {
			user = userService.findByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(userService.toDTO(user), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/testing", method = RequestMethod.GET)
	public ResponseEntity<?> getTest() {
		
		Client client = new Client();
		client.setActive(false);
		client.setEmail("alkdjssakldjsaklj");
		sqlClientRepo.save(client);
		return new ResponseEntity<>(sqlClientRepo.findAll(), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody UserDTO dto) {
		System.out.println(dto);
		
		User user;
		
		try {
			user = userService.updateUser(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(userService.toDTO(user), HttpStatus.OK);
	}
	
	/*
	@PreAuthorize("hasAnyAuthority('CLIENT', 'ADMIN')")
	@RequestMapping(value = "/{email}/subscriptions", method = RequestMethod.GET)
	public ResponseEntity<?> getUserSubscriptions(@PathVariable String email) {
		List<OfferDTO> subscriptions;
		try {
			subscriptions = userService.getSubscriptionsFromUserEmail(email);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(subscriptions, HttpStatus.OK);
	}
	*/
	
	/*
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
	@RequestMapping(value = "/{email}/reservations", method = RequestMethod.GET)
	public ResponseEntity<?> getUserReservations(@PathVariable String email) {
		List<Reservation> reservations;
		try {
			reservations = userService.getReservationsFromEmail(email);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
*/
}
