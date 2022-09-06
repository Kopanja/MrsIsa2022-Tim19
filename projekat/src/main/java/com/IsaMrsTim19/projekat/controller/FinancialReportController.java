package com.IsaMrsTim19.projekat.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IsaMrsTim19.projekat.model.ProfitMargin;
import com.IsaMrsTim19.projekat.model.Reservation;
import com.IsaMrsTim19.projekat.service.ProfitMarginService;
import com.IsaMrsTim19.projekat.service.ReservationService;

@RestController
@RequestMapping(value="api/financial_report")
public class FinancialReportController {

	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ProfitMarginService profitMarginService;
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@RequestMapping(value = "/profits", method = RequestMethod.GET)
	public ResponseEntity<?> getPastReservations(@RequestParam Map<String, String> searchParams) {
		System.out.println(searchParams.get("dateFrom"));
		System.out.println(searchParams.get("dateTo"));
		List<Reservation> reservations = null;
		try {
			reservations = reservationService.getPastReservations(searchParams.get("dateFrom"),searchParams.get("dateTo"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(reservations, HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@RequestMapping(value = "/profit-margin", method = RequestMethod.GET)
	public ResponseEntity<?> getProfitMargin() {
		ProfitMargin pr = profitMarginService.findActive();
		return new ResponseEntity<>(pr, HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@RequestMapping(value = "/profit-margin/{newMargin}", method = RequestMethod.POST)
	public ResponseEntity<?> updateProfitMargin(@PathVariable double newMargin) {
		if(newMargin >=1) {
			newMargin = newMargin/100;
		}
		ProfitMargin pr = profitMarginService.update(newMargin);
		return new ResponseEntity<>(pr, HttpStatus.OK);

	}
}
