package com.IsaMrsTim19.projekat.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.IsaMrsTim19.projekat.sql.model.User;
import com.IsaMrsTim19.projekat.sql.repository.UserSQLRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserSQLRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
		}
	}

}
