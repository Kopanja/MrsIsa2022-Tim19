package com.IsaMrsTim19.projekat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.dto.ReservationDTO;
import com.IsaMrsTim19.projekat.model.Offer;
import com.IsaMrsTim19.projekat.model.Reservation;
import com.IsaMrsTim19.projekat.repository.ReservationRepository;

@Service
public class ReservationService {

	
	@Autowired
	ReservationRepository reservationRepo;
	
	
	public Reservation save(Reservation reservation) {
		return reservationRepo.save(reservation);
	}
	
	public Long getNumberOfDays(Reservation reservation) {
		Long diff = reservation.getDateTo().getTime() - reservation.getDateFrom().getTime();
		return  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
	}
	
	public List<Date> getAllReservationDates(Reservation r){
		List<Date> resDates = new ArrayList<Date>();
		Calendar calendar;
		Calendar endCalendar;
		Date startDate = r.getDateFrom();
		Date endDate = r.getDateTo();
		resDates.add(startDate);
		resDates.add(endDate);
		calendar = getCalendarWithoutTime(startDate);
		endCalendar = getCalendarWithoutTime(endDate);
		while(calendar.before(endCalendar)) {
			calendar.add(Calendar.DATE, 1);
			Date result = calendar.getTime();
			resDates.add(result);
		}
		return resDates;
	}
	
	private static Calendar getCalendarWithoutTime(Date date) {
		  Calendar calendar = new GregorianCalendar();
		  calendar.setTime(date);
		  calendar.set(Calendar.HOUR, 0);
		  calendar.set(Calendar.HOUR_OF_DAY, 0);
		  calendar.set(Calendar.MINUTE, 0);
		  calendar.set(Calendar.SECOND, 0);
		  calendar.set(Calendar.MILLISECOND, 0);
		  return calendar;
		}
	

	
	
	public List<Reservation> getReservationsByClientId(Long clientId){
		return reservationRepo.getReservationsByClientId(clientId);
	}
	
	
	public boolean doesClientHaveCancelledReservationAtSameStartDate(Long clientId, Long offerId, ReservationDTO reservationDTO) throws ParseException {
		List<Reservation> reservations = reservationRepo.getCancelledReservationsBetweenClientAndOffer(clientId, offerId);
		System.out.println(reservations);
		Date dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(reservationDTO.getDateFrom());
		Calendar c1 = getCalendarWithoutTime(dateFrom);
		
		System.out.println(dateFrom);
		for(Reservation r : reservations) {
			if(getCalendarWithoutTime(r.getDateFrom()).equals(c1)){
				return true;
			}
		}
		
		
		return false;
	}
	
	public void cancelReservation(Long reservationId) throws Exception {
		Reservation reservation = reservationRepo.findById(reservationId).orElse(null);
		if(reservation == null) {
			throw new Exception("Something went wrong");
		}
		Calendar nowCalendar = getCalendarWithoutTime(new Date());
		Calendar startCalendar = getCalendarWithoutTime(reservation.getDateFrom());
		nowCalendar.add(Calendar.DATE, 3);
		if(nowCalendar.after(startCalendar)) {
			throw new Exception("You cant cancel the reservation when its 3 days or less away");
		}
		reservation.setCanceled(true);
		reservationRepo.save(reservation);
	}
	
	public Reservation toEntity(ReservationDTO dto) throws ParseException {
		// Date dateFrom = new SimpleDateFormat("MM/dd/yyyy").parse(dto.getDateFrom());
		// Date dateTo = new SimpleDateFormat("MM/dd/yyyy").parse(dto.getDateTo());
		 
		 
		 Date dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDateFrom());
		 Date dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDateTo());
		 
		 
		 Calendar cal = Calendar.getInstance(); 
		 
		 
		 cal.setTime(dateFrom);               
		 cal.add(Calendar.HOUR_OF_DAY, 16);     
		 dateFrom = cal.getTime();
		 
		 
		 cal.setTime(dateTo);               
		 cal.add(Calendar.HOUR_OF_DAY, 12);     
		 dateTo = cal.getTime();	 
		 
		 System.out.println("Datum dto od: " + dto.getDateFrom());
		 System.out.println("Datum od: " + dateFrom);
		 System.out.println("Datum dto do: " + dto.getDateTo());
		 System.out.println("Datum do: " + dateTo);
		 
		 
		 Reservation reservation = new Reservation();
		 reservation.setDateFrom(dateFrom);
		 reservation.setDateTo(dateTo);
		 return reservation;
	}

	public List<Reservation> getReservationsFromEmail(String email) {
		return reservationRepo.getReservationsFromEmail(email);
	}
}
