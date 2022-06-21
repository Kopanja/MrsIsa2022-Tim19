package com.IsaMrsTim19.projekat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.PromotionDTO;
import com.IsaMrsTim19.projekat.model.Promotion;
import com.IsaMrsTim19.projekat.repository.PromotionRepository;

@Service
public class PromotionService {

	@Autowired
	PromotionRepository promotionRepo;
	
	
	public Promotion save(Promotion promotion) {
		return promotionRepo.save(promotion);
	}
	
	public Promotion findById(Long id) {
		return promotionRepo.findById(id).orElse(null);
	}
	
	public Promotion toEntity(PromotionDTO dto) throws ParseException {
		Date dateFrom = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dto.getDateFrom());
		Date dateTo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dto.getDateTo());
		return new Promotion(dateFrom, dateTo);
	}
	
	public void delete(Promotion promotion) {
		promotionRepo.delete(promotion);
	}
}
