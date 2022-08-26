package com.IsaMrsTim19.projekat.dto;

public class AdminActivationDTO {

	LoginDTO loginDTO;
	String newPassword;
	
	
	public AdminActivationDTO() {
		super();
	}
	public AdminActivationDTO(LoginDTO loginDTO, String newPassword) {
		super();
		this.loginDTO = loginDTO;
		this.newPassword = newPassword;
	}
	public LoginDTO getLoginDTO() {
		return loginDTO;
	}
	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "AdminActivationDTO [loginDTO=" + loginDTO + ", newPassword=" + newPassword + "]";
	}
	
	
}
