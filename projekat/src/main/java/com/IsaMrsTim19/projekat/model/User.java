package com.IsaMrsTim19.projekat.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String firstname;

	private String lastname;

	private String email;

	private String password;

	@Relationship(type = "HAS_ROLE", direction = Relationship.Direction.OUTGOING)
	private Role role;

	@Relationship(type = "IS_IN", direction = Direction.OUTGOING)
	private City city;

	private String address;

	private String phoneNumber;

	private boolean active;

	public User() {
		super();
	}

	public User(String firstname, String lastname, String email, String password, Role role, City city, String address,
			String phoneNumber, boolean active) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.city = city;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.active = active;
	}

	public User(Long id, String firstname, String lastname, String email, String password, Role role, City city,
			String address, String phoneNumber, boolean active) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.city = city;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", city=" + city + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", active=" + active + "]";
	}

}
