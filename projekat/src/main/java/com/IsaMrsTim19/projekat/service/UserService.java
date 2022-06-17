package com.IsaMrsTim19.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.NewClientDTO;
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

	
	
	public User createClient(NewClientDTO dto) {
		
		User user = new User();
		user.setActive(false);
		user.setEmail(dto.getClient().getEmail());
		user.setAddress(dto.getClient().getAddress());
		user.setFirstname(dto.getClient().getFirstname());
		user.setLastname(dto.getClient().getLastname());
		user.setPhoneNumber(dto.getClient().getPhoneNumber());
		user.setPassword(dto.getPassword());
		
		Role role = roleRepo.findByRole("CLIENT");
		user.setRole(role);
		
		user = userRepo.save(user);
		
		return user;
		
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User save(User user) {
		return userRepo.save(user);
	}
}
