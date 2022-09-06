package com.IsaMrsTim19.projekat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.PromotionDTO;
import com.IsaMrsTim19.projekat.model.AdditionalService;
import com.IsaMrsTim19.projekat.model.Promotion;
import com.IsaMrsTim19.projekat.repository.PromotionRepository;

@Service
public class PromotionService {

	@Autowired
	PromotionRepository promotionRepo;
	
	@Autowired
	AdditionalServicesService additionalService;
	
	public Promotion save(Promotion promotion) {
		return promotionRepo.save(promotion);
	}
	
	public Promotion findById(Long id) {
		return promotionRepo.findById(id).orElse(null);
	}
	
	public Promotion toEntity(PromotionDTO dto) throws ParseException {
		//Date dateFrom = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dto.getDateFrom());
		//Date dateTo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dto.getDateTo());
		
		System.out.println(dto);
		Date dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDateFrom());
		Date dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDateTo());
		List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();
		
			for (Long id : dto.getAdditionalServicesIds()) {
				additionalServices.add(additionalService.findById(id));
			}

		
		
		return new Promotion(dateFrom, dateTo,additionalServices,dto.getPrice());
	}
	
	public void delete(Promotion promotion) {
		promotionRepo.delete(promotion);
	}

	public List<Promotion> getPromotionsForOffer(Long offerId) {
		// TODO Auto-generated method stub
		return promotionRepo.getPromotionsForOffer(offerId);
	}
}
