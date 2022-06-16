package com.IsaMrsTim19.projekat.dto;

public class NewClientDTO {
	
	private ClientDTO client;
	
	private String password;

	
	
	public NewClientDTO() {
		super();
	}



	public NewClientDTO(ClientDTO client, String password) {
		super();
		this.client = client;
		this.password = password;
	}



	public ClientDTO getClient() {
		return client;
	}



	public void setClient(ClientDTO client) {
		this.client = client;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "NewClientDTO [client=" + client + ", password=" + password + "]";
	}
	
	
	
	

}
