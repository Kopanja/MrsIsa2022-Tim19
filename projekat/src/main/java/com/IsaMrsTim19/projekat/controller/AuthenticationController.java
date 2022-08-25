package com.IsaMrsTim19.projekat.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.LoggedInUserDTO;
import com.IsaMrsTim19.projekat.dto.LoginDTO;
import com.IsaMrsTim19.projekat.dto.NewClientDTO;
import com.IsaMrsTim19.projekat.dto.NewOwnerDTO;
import com.IsaMrsTim19.projekat.model.Client;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.security.service.AuthenticationService;
import com.IsaMrsTim19.projekat.service.EmailSenderService;

@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationService authService;

	
	@Autowired
	EmailSenderService emailService;

	
	@RequestMapping(value = "/confirmation", method = RequestMethod.GET)
	public void confirmEmail(HttpServletResponse response,@RequestParam String token) {
		boolean isConfirmed = false;
		try {
			isConfirmed = authService.confirmRegistration(token);
		} catch (Exception e) {
			
		}
		if(isConfirmed) {
			response.setHeader("Location", "http://localhost:3000/");
			response.setStatus(302);  
		}

	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() {
		Client user = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
	}
	
	
	@PostMapping(value = "/register-client")
	public ResponseEntity<?> registerClient(@RequestBody NewClientDTO newUserDTO, HttpServletResponse response)
			{
		User newUser;
		try {
			newUser = authService.registerClient(newUserDTO);
		} catch (Exception e) {
			return new ResponseEntity<>("User with email: " + newUserDTO.getClient().getEmail() + " already exists",HttpStatus.BAD_REQUEST);
		}
		if (newUser != null) {
			System.out.println(newUser);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Sorry, something went wrong",HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping(value = "/register-owner")
	public ResponseEntity<?> registerOwner(@RequestBody NewOwnerDTO newUserDTO, HttpServletResponse response)
			{
		System.out.println(newUserDTO);
		User newUser;
		try {
			newUser = authService.registerOwner(newUserDTO);
		} catch (Exception e) {
			return new ResponseEntity<>("User with email: " + newUserDTO.getUserDto().getEmail() + " already exists",HttpStatus.BAD_REQUEST);
		}
		if (newUser != null) {
			System.out.println(newUser);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Sorry, something went wrong",HttpStatus.BAD_REQUEST);
		}

	}
	
	
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDto, HttpServletResponse response)
			{
		LoggedInUserDTO dto = null;
		try {
			
			dto = authService.login(loginDto);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<LoggedInUserDTO>(dto,HttpStatus.OK);

	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public void handleGet(HttpServletResponse response) {
		 response.setHeader("Location", "http://localhost:3000/");
		 response.setStatus(302);   
	    //return new ResponseEntity(headers, HttpStatus.FOUND);
	}
}
