package com.IsaMrsTim19.projekat.dto;

public class RejectionDTO {

	private String rejectionReason;

	
	public RejectionDTO() {
		super();
	}


	public RejectionDTO(String rejectionReason) {
		super();
		this.rejectionReason = rejectionReason;
	}


	public String getRejectionReason() {
		return rejectionReason;
	}


	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	
	
	
}
