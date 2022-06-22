package com.IsaMrsTim19.projekat.dto;

public class LoggedInUserDTO {

	private String accessToken;
	private UserDTO user;
	private String role;
	
	public LoggedInUserDTO() {
		super();
	}
	public LoggedInUserDTO(String accessToken, UserDTO user) {
		super();
		this.accessToken = accessToken;
		this.user = user;
	}
	
	
	public LoggedInUserDTO(String accessToken, UserDTO user, String role) {
		super();
		this.accessToken = accessToken;
		this.user = user;
		this.role = role;
	}
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "LoggedInUserDTO [accessToken=" + accessToken + ", user=" + user + "]";
	}
	
	
}
