package com.IsaMrsTim19.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.sql.model.City;
import com.IsaMrsTim19.projekat.repository.CityRepository;
import com.IsaMrsTim19.projekat.sql.repository.CitySQLRepository;

@Service
public class CityService {

	//@Autowired
	//CityRepository cityRepo;
	
	
	@Autowired
	CitySQLRepository cityRepo;
	
	
	public List<String> getAllCities(){
		List<City> cities= cityRepo.findAll();
		List<String> cityNames = new ArrayList<String>();
		for(City c : cities) {
			cityNames.add(c.getName());
		}
		
		return cityNames;
	}
}
