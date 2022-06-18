package com.IsaMrsTim19.projekat.dto;

public class UserDTO {

	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	
	
	
	public UserDTO() {
		super();
	}



	public UserDTO(String firstname, String lastname, String email, String phoneNumber) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	@Override
	public String toString() {
		return "UserDTO [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
}
