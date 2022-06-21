package com.IsaMrsTim19.projekat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.ReservationDTO;
import com.IsaMrsTim19.projekat.model.Reservation;
import com.IsaMrsTim19.projekat.repository.ReservationRepository;

@Service
public class ReservationService {

	
	@Autowired
	ReservationRepository reservationRepo;
	
	
	public Reservation save(Reservation reservation) {
		return reservationRepo.save(reservation);
	}
	
	
	public List<Reservation> getReservationsByClientId(Long clientId){
		return reservationRepo.getReservationsByClientId(clientId);
	}
	
	public Reservation toEntity(ReservationDTO dto) throws ParseException {
		 Date dateFrom = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dto.getDateFrom());
		 Date dateTo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dto.getDateTo());
		 
		 Reservation reservation = new Reservation();
		 reservation.setDateFrom(dateFrom);
		 reservation.setDateTo(dateTo);
		 return reservation;
	}
}
