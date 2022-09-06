package com.IsaMrsTim19.projekat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.model.Client;
import com.IsaMrsTim19.projekat.model.ClientLoyalty;
import com.IsaMrsTim19.projekat.model.OwnerLoyalty;
import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.repository.ClientLoyaltyRepository;
import com.IsaMrsTim19.projekat.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepo;
	
	@Autowired
	ClientLoyaltyRepository clientLoyaltyRepo;
	
	public Client save(Client client) {
		return clientRepo.save(client);
	}


	public Client findClientByReviewId(Long id) {
		return clientRepo.findClientByReviewId(id);
	}


	public Client findById(Long id) {
		return clientRepo.findById(id).orElse(null);
	}


	public double getLoyaltyBeneffit(Long id) {
		ClientLoyalty l = clientLoyaltyRepo.getLoyaltyBeneffit(id);
		return l.getBenefit();
	}
	


}
