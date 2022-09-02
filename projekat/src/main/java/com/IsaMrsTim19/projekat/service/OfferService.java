package com.IsaMrsTim19.projekat.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.dto.PromotionDTO;
import com.IsaMrsTim19.projekat.dto.ReservationDTO;
import com.IsaMrsTim19.projekat.dto.ReviewDTO;
import com.IsaMrsTim19.projekat.model.Accommodation;
import com.IsaMrsTim19.projekat.model.AdditionalService;
import com.IsaMrsTim19.projekat.model.Boat;
import com.IsaMrsTim19.projekat.model.Client;
import com.IsaMrsTim19.projekat.model.FishingTour;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.Owner;
import com.IsaMrsTim19.projekat.model.ProfitMargin;
import com.IsaMrsTim19.projekat.model.Promotion;
import com.IsaMrsTim19.projekat.model.Reservation;
import com.IsaMrsTim19.projekat.model.Review;
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

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProfitMarginService profitMarginService;

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

	public List<OfferDTO> searchResult(Map<String, String> queryParams) throws Exception {
		String query = queryService.generateSearchQuery(queryParams);
		List<Offer> offers = offerRepo.customQuery(query);

		List<Offer> availableOffers = new ArrayList<Offer>();
		if (queryParams.containsKey("dateFrom") && queryParams.containsKey("dateTo")) {
			Date fromDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(queryParams.get("dateFrom"));
			Date toDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(queryParams.get("dateFrom"));

			Reservation reservation = new Reservation();
			reservation.setDateFrom(fromDate);
			reservation.setDateTo(toDate);
			for (Offer o : offers) {
				if (this.isOfferAvailable(reservation, o)) {
					availableOffers.add(o);
				}
			}
		} else {
			availableOffers = offers;
		}

		List<OfferDTO> dtos = new ArrayList<OfferDTO>();
		for (Offer offer : availableOffers) {
			dtos.add(this.toDTO(offer));
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

	public Offer findOfferByReviewId(Long id) {
		Offer offer = offerRepo.findOfferByReviewId(id);
		offer = offerRepo.findById(offer.getId()).orElse(null);
		return offer;
	}
	public OfferDTO toDTO(Offer offer) {
		String address = offer.getAddress();
		if(offer.getCity() != null) {
			address = offer.getAddress() + ", " + offer.getCity().getName();
		}
		
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
		dto.setAdditionalServices(offer.getAdditionalServices());
		dto.setPrice(offer.getPrice());
		return dto;
	}

	public List<Date> getUnavailableDates(Offer offer) {
		List<Reservation> reservations = offer.getReservations();
		List<Date> unavailableDates = new ArrayList<Date>();
		List<Date> resDates;
		
		for (Reservation r : reservations) {
			if(!r.isCanceled()) {
				resDates = reservationService.getAllReservationDates(r);
				for (Date d : resDates) {
					unavailableDates.add(d);
				}
			}
			
		}
		return unavailableDates;
	}

	private boolean isOfferAvailable(Reservation reservation, Offer offer) throws Exception {
		List<Date> unavailableDates = this.getUnavailableDates(offer);

		List<Date> resDate = reservationService.getAllReservationDates(reservation);
		System.out.println(unavailableDates);
		System.out.println(resDate);
		for (Date unDate : unavailableDates) {
			for (Date rDate : resDate) {
				if (unDate.equals(rDate)) {
					return false;
				}
			}
		}
		return true;
	}

	@Transactional
	public void createReservation(Long offerId, Client client, ReservationDTO reservationDTO) throws Exception {

		Offer offer = offerRepo.findById(offerId).orElse(null);
		Reservation reservation = reservationService.toEntity(reservationDTO);

		if (offer == null) {
			throw new Exception("Something went wrong");
		}

		if (!(reservation.getDateFrom().after(offer.getAvaliableFrom())
				&& reservation.getDateTo().before(offer.getAvaliableUntil()))) {
			throw new Exception("The offer is not available then");
		}

		if ((reservation.getDateFrom().after(reservation.getDateTo())
				&& reservation.getDateTo().before(offer.getAvaliableUntil()))) {
			throw new Exception("Start Date needs to be before End Date");
		}
		if (!this.isOfferAvailable(reservation, offer)) {
			throw new Exception("A reservation already exist in that time");
		}
		
		if(reservationService.doesClientHaveCancelledReservationAtSameStartDate(client.getId(), offerId, reservationDTO)) {
			throw new Exception("You can't make this reservation because you already cancelled a reservation for this offer at this date");
		}
		
		List<Reservation> clientReservations = reservationService.getReservationsByClientId(client.getId());
		if (clientReservations != null) {
			clientReservations.add(reservation);
			client.setReservations(clientReservations);
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

		
		double basePrice = offer.getPrice();
		Long numberOfNights = reservationService.getNumberOfDays(reservation);
		System.out.println("Number of Nights: " + numberOfNights);
		basePrice = basePrice * numberOfNights;

		for (AdditionalService a : offer.getAdditionalServices()) {
			for (Long id : reservationDTO.getAdditionalServicesIds()) {
				if (a.getId().equals(id)) {
					basePrice += a.getPrice() * numberOfNights;
				}
			}

		}

		reservation.setPrice(basePrice);
		reservation.setDateCreated(new Date());
		
		ProfitMargin profitMargin = profitMarginService.findActive();
		
		reservation.setProfitMargin(profitMargin);
		
		double appProfit = basePrice*profitMargin.getPercentage();
		reservation.setAppProfit(appProfit);
		reservation.setOwnerProfit(basePrice - appProfit);
		
		
		reservationService.save(reservation);
		System.out.println(reservation);
		offerRepo.save(offer);
		//System.out.println(client.getReservations());
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

	public void createFastReservation(Long offerId, Long promotionId, Client client) throws Exception {

		Offer offer = offerRepo.findById(offerId).orElse(null);
		Promotion promotion = promotionService.findById(promotionId);

		if (offer == null || promotion == null) {
			throw new Exception("Something went wrong");
		}

		Reservation reservation = new Reservation();
		reservation.setDateFrom(promotion.getDateFrom());
		reservation.setDateTo(promotion.getDateTo());

		if (!(reservation.getDateFrom().after(offer.getAvaliableFrom())
				&& reservation.getDateTo().before(offer.getAvaliableUntil()))) {
			throw new Exception("The offer is not available then");
		}

		if (!this.isOfferAvailable(reservation, offer)) {
			throw new Exception("A reservation already exist in that time");
		}
		List<Reservation> clientReservations = reservationService.getReservationsByClientId(client.getId());
		if (clientReservations != null) {
			clientReservations.add(reservation);
			client.setReservations(clientReservations);
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

		promotionService.delete(promotion);

		String clientSubject = "Reservation is successful!";

		String clientBody = "Reservation details: \n";
		clientBody += "You have successfuly made a reservation for the '" + offer.getName() + "\n";
		clientBody += "Reservation start: " + reservation.getDateFrom() + "\n";
		clientBody += "Reservation end: " + reservation.getDateTo() + "\n";

		String ownerSubject = "Reservation Has Been Made!";

		String ownerBody = "Reservation details: \n";
		ownerBody += "There was a reservation made for '" + offer.getName();
		ownerBody += "Reservation start: " + reservation.getDateFrom() + "\n";
		ownerBody += "Reservation end: " + reservation.getDateTo() + "\n";

		String ownerEmail = ownerService.findOwnerByOfferId(offerId).getEmail();
		String clientEmail = client.getEmail();

		emailService.sendEmail(clientEmail, clientSubject, clientBody);
		emailService.sendEmail(ownerEmail, ownerSubject, ownerBody);

	}

	public void subscribe(Long offerId, Client client) throws Exception {

		Offer offer = offerRepo.findById(offerId).orElse(null);

		if (offer == null) {
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
		if (offer == null || offer.getSubscribers() == null) {
			throw new Exception("Something went wrong");
		}
		int counter = 0;
		for (Client c : offer.getSubscribers()) {
			if (c.getEmail().equals(client.getEmail())) {
				break;
			}
			counter++;
		}
		if (counter >= offer.getSubscribers().size()) {
			throw new Exception("Something went wrong");
		}
		offer.getSubscribers().remove(counter);
		offerRepo.save(offer);
	}

	public void createPromotion(Long offerId, Owner loggedInUser, PromotionDTO promotionDto) throws Exception {
		Offer offer = offerRepo.findById(offerId).orElse(null);
		Owner owner = ownerService.findOwnerByOfferId(offerId);

		if (offer == null || owner == null) {
			throw new Exception("Something went wrong");
		}

		if (!owner.getEmail().equals(loggedInUser.getEmail())) {
			throw new Exception("Only the owner can make a promotion");
		}

		Promotion promotion = promotionService.toEntity(promotionDto);

		if (!(promotion.getDateFrom().after(offer.getAvaliableFrom())
				&& promotion.getDateTo().before(offer.getAvaliableUntil()))) {
			throw new Exception("The offer is not available then");
		}
		Reservation reservation = new Reservation();
		reservation.setDateFrom(promotion.getDateFrom());
		reservation.setDateTo(promotion.getDateTo());
		if (!this.isOfferAvailable(reservation, offer)) {
			throw new Exception("A reservation already exist in that time");
		}
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
		for (Client client : offer.getSubscribers()) {
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

	@Transactional
	public void createReview(Long offerId, Client client, ReviewDTO reviewDto) throws Exception {
		Offer offer = offerRepo.findById(offerId).orElse(null);
		client = clientService.findById(client.getId());
		
		if (offer == null) {
			throw new Exception("Something went wrong");
		}
		Review review = reviewService.toEntity(reviewDto);

		review = reviewService.save(review);

		if (review == null) {
			throw new Exception("Something went wrong");
		}
		
		List<Review> clientReviews = reviewService.getReviewsByClientId(client.getId());
		if (clientReviews != null) {
			clientReviews.add(review);
			client.setReviews(clientReviews);
		} else {
			client.setReviews(new ArrayList<Review>());
			client.getReviews().add(review);
		}

		if (offer.getReviews() != null) {
			offer.getReviews().add(review);
		} else {
			offer.setReviews(new ArrayList<Review>());
			offer.getReviews().add(review);
		}
		
		
		offerRepo.save(offer);
		clientService.save(client);
		
	}
	
	public Offer getOfferFromReservationId(Long id) {
		return offerRepo.getOfferFromReservationId(id);
	}

	public List<Offer> getSubscriptionsFromUserEmail(String email) {
		return offerRepo.getSubscriptionsFromUserEmail(email);
	}

	public void cancelReservation(Long reservationId) throws Exception {
		reservationService.cancelReservation(reservationId);
		
	}

	
	public void updateRating(Offer offer, double newRating) {
		offer = this.findById(offer.getId());
		offer.setRating(newRating);
		offerRepo.save(offer);
		
	}

	public void delete(Long offerId) {
		offerRepo.delete(this.findById(offerId));	
	}

}
