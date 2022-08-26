package com.IsaMrsTim19.projekat.security.service;


import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.AdminActivationDTO;
import com.IsaMrsTim19.projekat.dto.LoggedInUserDTO;
import com.IsaMrsTim19.projekat.dto.LoginDTO;
import com.IsaMrsTim19.projekat.dto.NewClientDTO;
import com.IsaMrsTim19.projekat.dto.NewOwnerDTO;
import com.IsaMrsTim19.projekat.dto.UserDTO;
import com.IsaMrsTim19.projekat.model.Owner;
import com.IsaMrsTim19.projekat.model.OwnerApplication;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.model.VerificationToken;
import com.IsaMrsTim19.projekat.repository.UserRepository;
import com.IsaMrsTim19.projekat.security.util.TokenUtils;
import com.IsaMrsTim19.projekat.service.EmailSenderService;
import com.IsaMrsTim19.projekat.service.OwnerApplicationService;
import com.IsaMrsTim19.projekat.service.OwnerService;
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
	
	@Autowired
	private OwnerApplicationService ownerAppService;
	

	
	public LoggedInUserDTO login(LoginDTO loginDTO) throws BadCredentialsException {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDTO.getEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		User user = (User) authentication.getPrincipal();
		UserDTO userDTO = userService.toDTO(user);
		System.out.println(user);
		String jwt = tokenUtils.generateToken(user.getUsername(), (List<GrantedAuthority>) user.getAuthorities());
		
		LoggedInUserDTO dto = new LoggedInUserDTO(jwt,userDTO, user.getRole().getRole());
		
		return dto;
		
		//int expiresIn = tokenUtils.getExpiredIn();
	}
	
	
	
	public User registerClient(NewClientDTO newUserDTO) throws Exception {
		
		
		if(userService.doesUserExist(newUserDTO.getClient().getEmail()) != null) {
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
	
	public User registerOwner(NewOwnerDTO newUserDTO) throws Exception {
		
		if(userService.doesUserExist(newUserDTO.getUserDto().getEmail()) != null) {
			throw new Exception();
		}
		
		String hashedPassword = passwordEncoder.encode(newUserDTO.getPassword());
		newUserDTO.setPassword(hashedPassword);
		User user = userService.createOwner(newUserDTO);
		if(user != null) {
			OwnerApplication ownerApp = new OwnerApplication();
			ownerApp.setOwner((Owner) user);
			ownerApp.setRequestDescription(newUserDTO.getRequestDescription());
			ownerAppService.save(ownerApp);
		}
		
		return user;
		
	}
	
	
	public boolean confirmRegistration(String token) throws Exception {
		VerificationToken t = verTokenService.findByToken(token);
		if(t.getExpiryDate().before(new Date())) {
			return false;
		}
		User user = userService.findByEmail(t.getUser().getEmail());
		
		user.setActive(true);
		userService.save(user);
		verTokenService.delete(t);
		return true;
	}



	public User registerAdmin(UserDTO newUserDTO) throws Exception {
		if(userService.doesUserExist(newUserDTO.getEmail()) != null) {
			
			throw new Exception();
		}
		
		String generatedPassword = generateRandomPassword(5);
		String hashedPassword = passwordEncoder.encode(generatedPassword);
		System.out.println("Generated password: " + generatedPassword);
		System.out.println("Hashed password: " + hashedPassword);
		User user = userService.createAdmin(newUserDTO, hashedPassword);
		if(user != null) {
			emailService.sendEmail(user.getEmail(), "You became an administrator", "Your generated password is: " + generatedPassword + " ;Activation Link: http://localhost:8080/api/auth/activate-admin");

		}
		
		return user;
	}
	
	private static String generateRandomPassword(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
          +"jklmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}



	public UserDTO activateAdmin(AdminActivationDTO adminActivationDto) throws Exception {
		User user = userService.findByEmail(adminActivationDto.getLoginDTO().getEmail());
		if(!passwordEncoder.matches(adminActivationDto.getLoginDTO().getPassword(), user.getPassword())) {
			throw new Exception("Bad Credentials");
		}
		String hashedPassword = passwordEncoder.encode(adminActivationDto.getNewPassword());
		user.setPassword(hashedPassword);
		user.setActive(true);
		user = userService.save(user);
		return userService.toDTO(user);
	}
	
}
