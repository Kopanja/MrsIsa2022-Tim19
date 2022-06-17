package com.IsaMrsTim19.projekat.dto;

public class ClientDTO {

	private String firstname;

	private String lastname;

	private String email;

	private String password;

	private String role;

	private String address;

	private String phoneNumber;
	
	

	public ClientDTO() {
		super();
	}



	public ClientDTO(String firstname, String lastname, String email, String password, String role, String address,
			String phoneNumber) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
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



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	@Override
	public String toString() {
		return "ClientDTO [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", role=" + role + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	
	

	
}
