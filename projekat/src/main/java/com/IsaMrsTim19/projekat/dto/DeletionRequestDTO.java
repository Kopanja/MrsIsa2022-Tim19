package com.IsaMrsTim19.projekat.dto;

public class DeletionRequestDTO {

	private String requestText;

	public DeletionRequestDTO() {
		super();
	}

	public DeletionRequestDTO(String requestText) {
		super();
		this.requestText = requestText;
	}

	public String getRequestText() {
		return requestText;
	}

	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}
	
	
}
