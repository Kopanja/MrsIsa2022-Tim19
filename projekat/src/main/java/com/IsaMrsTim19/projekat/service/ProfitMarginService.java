package com.IsaMrsTim19.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IsaMrsTim19.projekat.model.ProfitMargin;
import com.IsaMrsTim19.projekat.repository.ProfitMarginRepository;

@Service
public class ProfitMarginService {
	
	@Autowired
	ProfitMarginRepository profitMarginRepo;
	
	public ProfitMargin findActive() {
		//ProfitMargin margin = profitMarginRepo.findActive();
		//return profitMarginRepo.findById(margin.getId()).orElse(null);
		return this.findByActive(true);
	}
	
	public ProfitMargin findByActive(boolean active) {
		return profitMarginRepo.findByActive(active);
	}
	public ProfitMargin save(ProfitMargin margin) {
		return profitMarginRepo.save(margin);
	}
	
	public void deactivateCurrentMargin() {
		ProfitMargin margin = this.findActive();
		margin.setActive(false);
		this.save(margin);
	}
	
	@Transactional
	public ProfitMargin update(double newMargin) {
		this.deactivateCurrentMargin();
		ProfitMargin newProfitMargin = new ProfitMargin(newMargin, true);
		return this.save(newProfitMargin);
	}

}
