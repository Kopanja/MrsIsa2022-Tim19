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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.IsaMrsTim19.projekat.security.auth.RestAuthenticationEntryPoint;
import com.IsaMrsTim19.projekat.security.auth.TokenAuthenticationFilter;
import com.IsaMrsTim19.projekat.security.service.UserDetailsServiceImpl;
import com.IsaMrsTim19.projekat.security.util.TokenUtils;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public static PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private UserDetailsServiceImpl customUserDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth

				.userDetailsService(customUserDetailsService)

				.passwordEncoder(encoder());
	}

	@Autowired
	private TokenUtils tokenUtils;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// komunikacija izmedju klijenta i servera je stateless
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint).and()
				// za neautorizovane zahteve posalji 401 gresku
				// .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
				// za cors

				// svim korisnicima dopusti da pristupe putanjama /auth/** i /h2-console/**
				.authorizeRequests().antMatchers("/auth/**").permitAll().anyRequest().authenticated().and()

				// za development svrhe ukljuci konfiguraciju za CORS iz WebConfig klase
				.cors().and()

				// umetni custom filter TokenAuthenticationFilter kako bi se vrsila provera JWT
				// tokena umesto cistih korisnickog imena i lozinke (koje radi
				// BasicAuthenticationFilter)
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService),
						BasicAuthenticationFilter.class);
		// svaki zahtev mora biti autorizovan
		// .anyRequest().authenticated();

		// presretni svaki zahtev filterom
		// http.addFilterBefore(jWTFilter, UsernamePasswordAuthenticationFilter.class);
		http.csrf().disable();
		//http.httpBasic().disable();
		//http.headers().frameOptions().disable();
	}

	@Override
	public void configure(WebSecurity web) {
		// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
		web.ignoring().antMatchers(HttpMethod.POST, "/api/auth/**");

		web.ignoring().antMatchers("/ws/**");

		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js", "/*.png", "/pics/*.png", "/*.jpg", "/pics/*.jpg");
	}

}
