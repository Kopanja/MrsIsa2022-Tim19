package com.IsaMrsTim19.projekat.model;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.IsaMrsTim19.projekat.util.DateTimeStringConverter;

@Node
public class VerificationToken {
	 
	@Id
	@GeneratedValue
	private Long id;
	
	@Relationship(type = "FOR_USER", direction = Relationship.Direction.OUTGOING)
	private User user;
	
	private String token;
	
	@ConvertWith(converter =  DateTimeStringConverter.class)
	private Date expiryDate;

	
	
	public VerificationToken() {
		super();
	}

	

	public VerificationToken(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}



	public VerificationToken(User user, String token, Date expiryDate) {
		super();
		this.user = user;
		this.token = token;
		this.expiryDate = expiryDate;
	}



	public VerificationToken(Long id, User user, String token, Date expiryDate) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
		this.expiryDate = expiryDate;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public Date getExpiryDate() {
		return expiryDate;
	}



	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}



	@Override
	public String toString() {
		return "VerificationToken [id=" + id + ", user=" + user + ", token=" + token + ", expiryDate=" + expiryDate
				+ "]";
	}

	

	
	
	
	
	


}
