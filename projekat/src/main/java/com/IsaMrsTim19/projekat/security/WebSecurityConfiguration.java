package com.IsaMrsTim19.projekat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// komunikacija izmedju klijenta i servera je stateless
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			
			// za neautorizovane zahteve posalji 401 gresku
			//.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			// za cors
			.cors().and()
			
			// svim korisnicima dopusti da pristupe putanjama /auth/** i /h2-console/**
			.authorizeRequests()
			
			 
			//.antMatchers("/api/item/pdf/*.pdf").permitAll()
			//.antMatchers("/api/report/pdf/*.pdf").permitAll()
			//.antMatchers("/api/auth/**").permitAll()
			
			//.antMatchers("/api/item-category/**").hasAnyAuthority("BARTENDER", "ADMIN")
			.antMatchers("/api/**").permitAll();
			// svaki zahtev mora biti autorizovan
			//.anyRequest().authenticated();
			
			// presretni svaki zahtev filterom
		//http.addFilterBefore(jWTFilter, UsernamePasswordAuthenticationFilter.class);
		http.csrf().disable();
		http.httpBasic().disable();
		http.headers().frameOptions().disable();
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	/*
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	*/
	public void configure(WebSecurity web) {
		// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		 
		web.ignoring().antMatchers("/ws/**");
		
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/*.png", "/pics/*.png", "/*.jpg", "/pics/*.jpg");
	}

}
