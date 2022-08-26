package com.IsaMrsTim19.projekat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.UserDTO;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.Reservation;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.security.service.AuthenticationService;
import com.IsaMrsTim19.projekat.service.UserService;

@RestController
@RequestMapping(value="api/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationService authService;

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
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping(value = "/register-admin")
	public ResponseEntity<?> registerAdmin(@RequestBody UserDTO newUserDTO, HttpServletResponse response)
			{
		System.out.println(newUserDTO);
		User newUser;
		try {
			newUser = authService.registerAdmin(newUserDTO);
		} catch (Exception e) {
			return new ResponseEntity<>("User with email: " + newUserDTO.getEmail() + " already exists",HttpStatus.BAD_REQUEST);
		}
		if (newUser != null) {
			System.out.println(newUser);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Sorry, something went wrong",HttpStatus.BAD_REQUEST);
		}

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

}
