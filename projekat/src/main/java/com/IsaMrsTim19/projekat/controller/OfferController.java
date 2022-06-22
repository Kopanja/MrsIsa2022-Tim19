package com.IsaMrsTim19.projekat.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.dto.OfferDTO;
import com.IsaMrsTim19.projekat.dto.OfferListByPageDTO;
import com.IsaMrsTim19.projekat.dto.PromotionDTO;
import com.IsaMrsTim19.projekat.dto.ReservationDTO;
import com.IsaMrsTim19.projekat.dto.ReviewDTO;
import com.IsaMrsTim19.projekat.model.Client;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.Owner;
import com.IsaMrsTim19.projekat.service.OfferService;

@RestController
@RequestMapping(value = "api/offer")
public class OfferController {

	@Autowired
	OfferService offerService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Offer>> getAllOffers() {
		List<Offer> allOffers = offerService.getAllOffers();
		System.out.println(allOffers);
		return new ResponseEntity<>(allOffers, HttpStatus.OK);

	}

	@RequestMapping(value = "/{pageNum}", method = RequestMethod.GET)
	public ResponseEntity<OfferListByPageDTO> getAllOffers(@PathVariable Integer pageNum) {

	
		OfferListByPageDTO offersByPage = offerService.getAllOfferByPage(pageNum);
		return new ResponseEntity<>(offersByPage, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}/unavailable-dates", method = RequestMethod.GET)
	public ResponseEntity<?> getAllOffers(@PathVariable Long id) {

	
		Offer offer = offerService.findById(id);
		List<Date> unavailableDates = offerService.getUnavailableDates(offer);
		return new ResponseEntity<>(unavailableDates, HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'OWNER')")
	@RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.GET)
	public ResponseEntity<?> getOfferByReservation(@PathVariable Long reservationId) {
		Offer offer = null;
		try {
			 offer= offerService.getOfferFromReservationId(reservationId);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(offerService.toDTO(offer), HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
		try {
			 offerService.cancelReservation(reservationId);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Reservation deleted", HttpStatus.OK);

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<?> getSearchResult(@RequestParam Map<String, String> searchParams) {

		List<OfferDTO> dtos;
		try {
			dtos = offerService.searchResult(searchParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		}
		// System.out.println(accommService.searchResult("MATCH (n:Accommodation) RETURN
		// n"));
		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() {
		Client user = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@RequestMapping(value = "/{id}/make-reservation", method = RequestMethod.POST)
	public ResponseEntity<?> makeReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {

		Client user = null;
		try {
			user = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return new ResponseEntity<>("He is null again", HttpStatus.BAD_REQUEST);
		}

		try {
			if (user != null) {
				offerService.createReservation(id, user, reservationDTO);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@RequestMapping(value = "/{id}/subscribe", method = RequestMethod.POST)
	public ResponseEntity<?> subscribeToOffer(@PathVariable Long id) {

		Client user = null;
		try {
			user = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return new ResponseEntity<>("He is null again", HttpStatus.BAD_REQUEST);
		}
		try {
			offerService.subscribe(id, user);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Subscribed", HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('OWNER')")
	@RequestMapping(value = "/{id}/create-promotion", method = RequestMethod.POST)
	public ResponseEntity<?> createPromotion(@PathVariable Long id, @RequestBody PromotionDTO promotionDTO) {

		Owner user = null;
		try {
			user = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return new ResponseEntity<>("He is null again", HttpStatus.BAD_REQUEST);
		}
		try {
			offerService.createPromotion(id, user, promotionDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Subscribed", HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@RequestMapping(value = "/{id}/create-review", method = RequestMethod.POST)
	public ResponseEntity<?> createReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {

		Client user = null;
		try {
			user = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return new ResponseEntity<>("He is null again", HttpStatus.BAD_REQUEST);
		}
		try {
			offerService.createReview(id, user, reviewDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Subscribed", HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@RequestMapping(value = "/{offerId}/promotion/{promotionId}/reserve", method = RequestMethod.POST)
	public ResponseEntity<?> fastReservation(@PathVariable Long offerId, @PathVariable Long promotionId) {

		Client user = null;
		try {
			user = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return new ResponseEntity<>("He is null again", HttpStatus.BAD_REQUEST);
		}
		try {
			offerService.createFastReservation(offerId, promotionId, user);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Subscribed", HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('CLIENT')")
	@RequestMapping(value = "/{id}/unsubscribe", method = RequestMethod.POST)
	public ResponseEntity<?> unsuscribeFromOffer(@PathVariable Long id) {

		Client user = null;
		try {
			user = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return new ResponseEntity<>("You have to be logedin", HttpStatus.BAD_REQUEST);
		}
		try {
			offerService.unsubscribe(id, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Unsubscribed", HttpStatus.OK);

	}

	@GetMapping("/{id}/thumbnail")
	public ResponseEntity<?> getProfileImage(@PathVariable Long id) {

		try {
			Path imagePath = offerService.getOfferThumbnailPath(id);
			if (imagePath != null) {
				Resource resource = new ByteArrayResource(Files.readAllBytes(imagePath.normalize()));

				return ResponseEntity.ok().contentLength(imagePath.toFile().length()).contentType(MediaType.IMAGE_JPEG)
						.body(resource);

			} else {

				return ResponseEntity.status(HttpStatus.OK).build();
			}

		} catch (Exception e) {
			System.out.println("Doslo je do greske");

		}
		System.out.println("Ovde");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/image/{folder}/{imageName}")
	public ResponseEntity<?> getImage(@PathVariable String folder, @PathVariable String imageName) {
		try {
			Path imagePath = Paths.get(".\\src\\main\\resources\\images\\" + folder + "\\" + imageName);
			if (imagePath != null) {
				Resource resource = new ByteArrayResource(Files.readAllBytes(imagePath.normalize()));

				return ResponseEntity.ok().contentLength(imagePath.toFile().length()).contentType(MediaType.IMAGE_JPEG)
						.body(resource);

			} else {

				return ResponseEntity.status(HttpStatus.OK).build();
			}

		} catch (Exception e) {
			System.out.println("Doslo je do greske");

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
