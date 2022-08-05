package com.IsaMrsTim19.projekat.dto;

public class DeletionRequestDTO {

	private Long id;
	private String requestText;
	private String userName;
	private String userRole;

	public DeletionRequestDTO() {
		super();
	}

	public DeletionRequestDTO(String requestText) {
		super();
		this.requestText = requestText;
	}
	


	public DeletionRequestDTO(String requestText, String userName, String userRole) {
		super();
		this.requestText = requestText;
		this.userName = userName;
		this.userRole = userRole;
	}

	
	public DeletionRequestDTO(Long id, String requestText, String userName, String userRole) {
		super();
		this.id = id;
		this.requestText = requestText;
		this.userName = userName;
		this.userRole = userRole;
	}

	public String getRequestText() {
		return requestText;
	}

	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	
	
}
