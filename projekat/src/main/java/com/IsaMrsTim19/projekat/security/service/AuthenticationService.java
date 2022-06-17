package com.IsaMrsTim19.projekat.security.service;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.NewClientDTO;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.model.VerificationToken;
import com.IsaMrsTim19.projekat.service.EmailSenderService;
import com.IsaMrsTim19.projekat.service.UserService;
import com.IsaMrsTim19.projekat.service.VerificationTokenService;


@Service
public class AuthenticationService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VerificationTokenService verTokenService;
	
	@Autowired
	private EmailSenderService emailService;
	
	public User register(NewClientDTO newUserDTO) throws Exception {
		
	
		if(userService.findByEmail(newUserDTO.getClient().getEmail()) != null) {
			throw new Exception();
		}
		
		
		String hashedPassword = passwordEncoder.encode(newUserDTO.getPassword());
		newUserDTO.setPassword(hashedPassword);
		User user = userService.createClient(newUserDTO);
		if(user != null) {
			String token = UUID.randomUUID().toString();
			verTokenService.save(user, token);
		}
		
		VerificationToken t = verTokenService.findByUser(user);
		if(t != null) {
			emailService.sendEmail(user.getEmail(), "Confirmation Email", "Confirmation Link: http://localhost:8080/api/auth/confirmation?token=" + t.getToken());
		}
		System.out.println(t);
		return user;
		
	}
	
	
	public boolean confirmRegistration(String token) {
		VerificationToken t = verTokenService.findByToken(token);
		if(t.getExpiryDate().before(new Date())) {
			return false;
		}
		User user = t.getUser();
		user.setActive(true);
		userService.save(user);
		verTokenService.delete(t);
		return true;
	}
	
}
