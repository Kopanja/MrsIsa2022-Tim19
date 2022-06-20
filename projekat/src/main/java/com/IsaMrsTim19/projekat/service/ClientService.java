package com.IsaMrsTim19.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.model.Client;
import com.IsaMrsTim19.projekat.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepo;
	
	public Client save(Client client) {
		return clientRepo.save(client);
	}
}
