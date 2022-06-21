package com.IsaMrsTim19.projekat.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.dto.PromotionDTO;
import com.IsaMrsTim19.projekat.dto.ReservationDTO;
import com.IsaMrsTim19.projekat.model.Accommodation;
import com.IsaMrsTim19.projekat.model.Boat;
import com.IsaMrsTim19.projekat.model.Client;
import com.IsaMrsTim19.projekat.model.FishingTour;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.Owner;
import com.IsaMrsTim19.projekat.model.Promotion;
import com.IsaMrsTim19.projekat.model.Reservation;
import com.IsaMrsTim19.projekat.repository.OfferRepository;

@Service
public class OfferService {

	@Autowired
	OfferRepository offerRepo;

	@Autowired
	QueryService queryService;

	@Autowired
	ReservationService reservationService;

	@Autowired
	ClientService clientService;

	@Autowired
	EmailSenderService emailService;

	@Autowired
	OwnerService ownerService;
	
	@Autowired
	PromotionService promotionService;

	public List<Offer> getAllOffers() {
		this.getNumberOfPages();
		return offerRepo.findAll();
	}

	public Offer save(Offer offer) {
		return offerRepo.save(offer);
	}

	public OfferListByPageDTO getAllOfferByPage(int pageNum) {

		OfferListByPageDTO offersByPageDTO = new OfferListByPageDTO();
		List<Offer> offersByPage = offerRepo.getOffersByPage(pageNum);
		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		for (Offer offer : offersByPage) {
			dtos.add(this.toDTO(offer));
		}
		offersByPageDTO.setDtos(dtos);
		offersByPageDTO.setTotalNumberOfPages(this.getNumberOfPages());
		return offersByPageDTO;
	}

	public List<OfferDTO> searchResult(Map<String, String> queryParams) {
		String query = queryService.generateSearchQuery(queryParams);
		List<Offer> offers = offerRepo.customQuery(query);
		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		for (Offer offer : offers) {
			System.out.println(offer);
			dtos.add(this.toDTO(offer));
			// System.out.println(accomm.getNumberOfPeople());
		}
		return dtos;
	}

	public int getNumberOfPages() {
		int numOfOffers = offerRepo.getNumberOfOffers();
		int numOfPages = numOfOffers / 8;
		if (numOfOffers % 8 != 0) {
			numOfPages++;
		}
		return numOfPages;
	}

	public OfferDTO getOfferById(Long id) {
		Offer offer = offerRepo.findById(id).orElse(null);
		return this.toDTO(offer);
	}

	public Offer findById(Long id) {
		return offerRepo.findById(id).orElse(null);
	}

	public Path getOfferThumbnailPath(Long id) {
		Offer offer = offerRepo.findById(id).orElse(null);
		String imgPathFolder = offer.getImgFolderPath();
		String thumbnail = offer.getThumbnail();
		Path imagePath = Paths.get(".\\src\\main\\resources\\images\\" + imgPathFolder + "\\" + thumbnail);
		return imagePath;
	}

	public OfferDTO toDTO(Offer offer) {
		String address = offer.getAddress() + ", " + offer.getCity().getName();
		String offerType = null;
		if (offer instanceof Accommodation) {
			offerType = "accommodation";
		} else if (offer instanceof Boat) {
			offerType = "boat";
		} else if (offer instanceof FishingTour) {
			offerType = "fishingTour";
		}
		OfferDTO dto = new OfferDTO(offer.getId(), offer.getName(), address, offer.getDescription(), offer.getRating());
		dto.setOfferType(offerType);
		return dto;
	}

	public void createReservation(Long offerId, Client client, ReservationDTO reservationDTO) throws ParseException {

		Offer offer = offerRepo.findById(offerId).orElse(null);
		Reservation reservation = reservationService.toEntity(reservationDTO);

		if (client.getReservations() != null) {
			client.getReservations().add(reservation);
		} else {
			client.setReservations(new ArrayList<Reservation>());
			client.getReservations().add(reservation);
		}

		if (offer.getReservations() != null) {
			offer.getReservations().add(reservation);
		} else {
			offer.setReservations(new ArrayList<Reservation>());
			offer.getReservations().add(reservation);
		}

		// treba racunanje cene
		reservationService.save(reservation);

		offerRepo.save(offer);
		clientService.save(client);

		String clientSubject = "Reservation is successful!";

		String clientBody = "Reservation details: \n";
		clientBody += "You have successfuly made a reservation for the '" + offer.getName() + "\n";
		clientBody += "Reservation start: " + reservationDTO.getDateFrom() + "\n";
		clientBody += "Reservation end: " + reservationDTO.getDateTo() + "\n";

		String ownerSubject = "Reservation Has Been Made!";

		String ownerBody = "Reservation details: \n";
		ownerBody += "There was a reservation made for '" + offer.getName();
		ownerBody += "Reservation start: " + reservationDTO.getDateFrom() + "\n";
		ownerBody += "Reservation end: " + reservationDTO.getDateTo() + "\n";

		String ownerEmail = ownerService.findOwnerByOfferId(offerId).getEmail();
		String clientEmail = client.getEmail();

		emailService.sendEmail(clientEmail, clientSubject, clientBody);
		emailService.sendEmail(ownerEmail, ownerSubject, ownerBody);

	}
	
	public void subscribe(Long offerId, Client client) throws Exception {

		Offer offer = offerRepo.findById(offerId).orElse(null);

		if(offer == null) {
			throw new Exception("Something went wrong");
		}
		if (offer.getSubscribers() != null) {
			offer.getSubscribers().add(client);
		} else {
			offer.setSubscribers(new ArrayList<Client>());
			offer.getSubscribers().add(client);
		}
		offerRepo.save(offer);

	}
	
	public void unsubscribe(Long offerId, Client client) throws Exception {

		Offer offer = offerRepo.findById(offerId).orElse(null);
		if(offer == null || offer.getSubscribers() == null) {
			throw new Exception("Something went wrong");
		}
		int counter = 0;
		for(Client c : offer.getSubscribers()) {
			if(c.getEmail().equals(client.getEmail())) {
				break;
			}
			counter++;
		}
		if(counter >= offer.getSubscribers().size()) {
			throw new Exception("Something went wrong");
		}
		offer.getSubscribers().remove(counter);
		offerRepo.save(offer);
	}
	
	public void createPromotion(Long offerId, Owner loggedInUser, PromotionDTO promotionDto) throws Exception {
		Offer offer = offerRepo.findById(offerId).orElse(null);
		Owner owner = ownerService.findOwnerByOfferId(offerId);
		
		if(offer == null || owner == null) {
			throw new Exception("Something went wrong");
		}
		
		if(!owner.getEmail().equals(loggedInUser.getEmail())) {
			throw new Exception("Only the owner can make a promotion");
		}
		
		Promotion promotion = promotionService.toEntity(promotionDto);
		promotionService.save(promotion);
		
		
		if (offer.getPromotions() != null) {
			offer.getPromotions().add(promotion);
		} else {
			offer.setPromotions(new ArrayList<Promotion>());
			offer.getPromotions().add(promotion);
		}
		
		
		offerRepo.save(offer);
		this.notifySubscribers(offer, promotion);
	}

	private void notifySubscribers(Offer offer, Promotion promotion) {
		String subject = "There is a new promotion for: " + offer.getName();
		String body = "There is a new promotion for: " + offer.getName() + "\n";
		body += "It start at: " + promotion.getDateFrom() + " and ends: " + promotion.getDateTo();
		for(Client client : offer.getSubscribers()) {
			emailService.sendEmail(client.getEmail(), subject, body);
		}
	}
	public List<String> createImageURLs(Offer offer) {
		List<String> urls = new ArrayList<String>();
		File folder = new File(".\\src\\main\\resources\\images\\" + offer.getImgFolderPath());
		File[] listOfFiles = folder.listFiles();
		String baseUrl = "http://localhost:8080/api/offer/image/" + offer.getImgFolderPath() + "/";
		String fileName = "";
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileName = listOfFiles[i].getName();
				if (!fileName.equals(offer.getThumbnail())) {
					urls.add(baseUrl + fileName);
				}

			}
		}
		return urls;
	}

}
