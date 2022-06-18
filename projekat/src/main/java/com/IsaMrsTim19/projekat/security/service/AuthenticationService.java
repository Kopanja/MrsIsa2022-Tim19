package com.IsaMrsTim19.projekat.security.service;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.LoggedInUserDTO;
import com.IsaMrsTim19.projekat.dto.LoginDTO;
import com.IsaMrsTim19.projekat.dto.NewClientDTO;
import com.IsaMrsTim19.projekat.dto.UserDTO;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.model.VerificationToken;
import com.IsaMrsTim19.projekat.security.util.TokenUtils;
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
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	public LoggedInUserDTO login(LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDTO.getEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user = (User) authentication.getPrincipal();
		UserDTO userDTO = userService.toDTO(user);
		
		String jwt = tokenUtils.generateToken(user.getUsername(), (List<GrantedAuthority>) user.getAuthorities());
		
		LoggedInUserDTO dto = new LoggedInUserDTO(jwt,userDTO);
		
		return dto;
		
		//int expiresIn = tokenUtils.getExpiredIn();
	}
	
	
	
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
