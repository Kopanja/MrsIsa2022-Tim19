package com.IsaMrsTim19.projekat.dto;

public class PromotionDTO {
	
	private String dateFrom;
	
	private String dateTo;

	public PromotionDTO() {
		super();
	}

	public PromotionDTO(String dateFrom, String dateTo) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	

}
