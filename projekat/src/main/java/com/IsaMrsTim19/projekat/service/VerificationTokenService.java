package com.IsaMrsTim19.projekat.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.model.VerificationToken;
import com.IsaMrsTim19.projekat.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {

	private static final int EXPIRATION = 60 * 24;
	@Autowired
	VerificationTokenRepository verTokenRepo;
	
	
	public List<VerificationToken> findAll(){
		return verTokenRepo.findAll();
	}
	
	public VerificationToken findByToken(String token) {
		return verTokenRepo.findByToken(token).orElse(null);
	}
	
	
	public VerificationToken findByUser(User user) {
		return verTokenRepo.findByUserEmail(user.getEmail());
	}
	
	public void delete(VerificationToken token) {
		verTokenRepo.delete(token);
	}
	
	public void save(User user, String token) {
		VerificationToken newToken = new VerificationToken(user, token);
		newToken.setExpiryDate(this.calculateExpiryDate(EXPIRATION));
		verTokenRepo.save(newToken);
	}
	
	
	  private Date calculateExpiryDate(int expiryTimeInMinutes) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date(cal.getTime().getTime()));
	        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
	        return new Date(cal.getTime().getTime());
	    }
}
