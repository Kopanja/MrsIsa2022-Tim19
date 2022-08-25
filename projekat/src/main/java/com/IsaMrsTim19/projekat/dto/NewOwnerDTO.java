package com.IsaMrsTim19.projekat.dto;

public class NewOwnerDTO {

	private UserDTO userDto;
	private String password;
	private String ownerType;
	private String requestDescription;
	
	
	public NewOwnerDTO() {
		super();
	}
	public NewOwnerDTO(UserDTO userDto, String password, String ownerType) {
		super();
		this.userDto = userDto;
		this.password = password;
		this.ownerType = ownerType;
	}
	
	
	public NewOwnerDTO(UserDTO userDto, String password, String ownerType, String requestDescription) {
		super();
		this.userDto = userDto;
		this.password = password;
		this.ownerType = ownerType;
		this.requestDescription = requestDescription;
	}
	public UserDTO getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public String getRequestDescription() {
		return requestDescription;
	}
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	@Override
	public String toString() {
		return "NewOwnerDTO [userDto=" + userDto + ", password=" + password + ", ownerType=" + ownerType
				+ ", requestDescription=" + requestDescription + "]";
	}
	
	
	
}
