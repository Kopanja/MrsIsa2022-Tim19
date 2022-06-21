package com.IsaMrsTim19.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.NewClientDTO;
import com.IsaMrsTim19.projekat.dto.NewOwnerDTO;
import com.IsaMrsTim19.projekat.dto.UserDTO;
import com.IsaMrsTim19.projekat.model.Client;
import com.IsaMrsTim19.projekat.model.Owner;
import com.IsaMrsTim19.projekat.model.Role;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.repository.RoleRepository;
import com.IsaMrsTim19.projekat.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	OwnerService ownerService;
	
	@Autowired
	ClientService clientService;
	
	public User createClient(NewClientDTO dto) {
		
		Client user = new Client();
		user.setActive(false);
		user.setEmail(dto.getClient().getEmail());
		user.setAddress(dto.getClient().getAddress());
		user.setFirstname(dto.getClient().getFirstname());
		user.setLastname(dto.getClient().getLastname());
		user.setPhoneNumber(dto.getClient().getPhoneNumber());
		user.setPassword(dto.getPassword());
		
		Role role = roleRepo.findByRole("CLIENT");
		user.setRole(role);
		
		user = clientService.save(user);
		
		return user;
		
	}
	

	public User createOwner(NewOwnerDTO dto) {
		Owner user = new Owner();
		user.setActive(false);
		user.setEmail(dto.getUserDto().getEmail());
		//user.setAddress(dto.getUserDto().getAddress());
		user.setFirstname(dto.getUserDto().getFirstname());
		user.setLastname(dto.getUserDto().getLastname());
		user.setPhoneNumber(dto.getUserDto().getPhoneNumber());
		user.setPassword(dto.getPassword());
		user.setOwnerType(dto.getOwnerType());
		Role role = roleRepo.findByRole("OWNER");
		user.setRole(role);
		
		user = ownerService.save(user);
		
		return user;
	}
	
	public UserDTO toDTO(User user) {
		return new UserDTO(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPhoneNumber());
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email).orElse(null);
	}
	
	public User save(User user) {
		return userRepo.save(user);
	}

	
}
