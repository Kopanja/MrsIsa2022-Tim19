package com.IsaMrsTim19.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IsaMrsTim19.projekat.dto.DeletionRequestDTO;
import com.IsaMrsTim19.projekat.dto.NewClientDTO;
import com.IsaMrsTim19.projekat.dto.NewOwnerDTO;
import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.UserDTO;
import com.IsaMrsTim19.projekat.model.Admin;
import com.IsaMrsTim19.projekat.model.Client;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.Owner;
import com.IsaMrsTim19.projekat.model.Reservation;
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
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	ReservationService reservationService;

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
	
	
	public User updateUser(UserDTO dto) {
	
		User user = userRepo.findById(dto.getId()).orElse(null);
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setEmail(dto.getEmail());
		user.setPhoneNumber(dto.getPhoneNumber());
		if(user.getRole() == null) {
			user.setRole(roleRepo.findByRole("CLIENT"));
		}
		user = userRepo.save(user);
		
		return user;
	}
	public List<OfferDTO> getSubscriptionsFromUserEmail(String email){
		List<Offer> subscriptions = offerService.getSubscriptionsFromUserEmail(email);
		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		
		for (Offer o : subscriptions) {
			dtos.add(offerService.toDTO(o));
		}
		return dtos;
	}
	
	public List<Reservation> getReservationsFromEmail(String email){
		return reservationService.getReservationsFromEmail(email);
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
		return new UserDTO(user.getId(),user.getFirstname(), user.getLastname(), user.getEmail(), user.getPhoneNumber());
	}
	
	public User findByEmail(String email) throws Exception {
		User user = userRepo.findByEmail(email).orElse(null);
		if(user == null) {
			throw new Exception("User was not found");
		}
		
		return user;
	}
	
	
	public User doesUserExist(String email) {
		User user = userRepo.findByEmail(email).orElse(null);
		return user;
	}
	
	public User save(User user) {
		return userRepo.save(user);
	}


	public void delete(User user) {
		userRepo.delete(user);
		
	}

	

	public User createAdmin(UserDTO newUserDTO, String password) {
		Admin user = new Admin();
		user.setActive(false);
		user.setEmail(newUserDTO.getEmail());
		user.setFirstname(newUserDTO.getFirstname());
		user.setLastname(newUserDTO.getLastname());
		user.setPhoneNumber(newUserDTO.getPhoneNumber());
		user.setPassword(password);
		Role role = roleRepo.findByRole("ADMIN");
		user.setRole(role);
		userRepo.save(user);
		return user;
	}

	
}
