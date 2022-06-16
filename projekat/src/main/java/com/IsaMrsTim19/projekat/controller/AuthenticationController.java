package com.IsaMrsTim19.projekat.controller;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.NewClientDTO;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.model.VerificationToken;
import com.IsaMrsTim19.projekat.security.service.AuthenticationService;
import com.IsaMrsTim19.projekat.service.EmailSenderService;
import com.IsaMrsTim19.projekat.service.VerificationTokenService;

@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationService authService;

	
	@Autowired
	EmailSenderService emailService;

	
	@RequestMapping(value = "/confirmation", method = RequestMethod.GET)
	public void sendEmail(HttpServletResponse response,@RequestParam String token) {
		boolean isConfirmed = authService.confirmRegistration(token);
		if(isConfirmed) {
			response.setHeader("Location", "http://localhost:3000/");
			response.setStatus(302);  
		}

	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@RequestBody NewClientDTO newUserDTO, HttpServletResponse response)
			throws Exception {
		System.out.println(newUserDTO);
		User newUser = authService.register(newUserDTO);
		if (newUser != null) {
			System.out.println(newUser);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public void handleGet(HttpServletResponse response) {
		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
		 response.setHeader("Location", "http://localhost:3000/");
		 response.setStatus(302);   
	    //return new ResponseEntity(headers, HttpStatus.FOUND);
	}
}
