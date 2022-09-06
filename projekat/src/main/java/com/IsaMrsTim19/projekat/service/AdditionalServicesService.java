package com.IsaMrsTim19.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.model.AdditionalService;
import com.IsaMrsTim19.projekat.repository.AdditionalServiceRepository;

@Service
public class AdditionalServicesService {
	
	
	@Autowired
	AdditionalServiceRepository repo;
	
	
	
	 AdditionalService findById(Long id) {
		 return repo.findById(id).orElse(null);
	 }

}
