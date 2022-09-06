package com.IsaMrsTim19.projekat.dto;

import java.util.List;

public class PromotionDTO {
	
	private String dateFrom;
	
	private String dateTo;
	
	private List<Long> additionalServicesIds;
	
	private double price;

	public PromotionDTO() {
		super();
	}

	public PromotionDTO(String dateFrom, String dateTo) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	
	public PromotionDTO(String dateFrom, String dateTo, List<Long> additionalServicesIds, double price) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.additionalServicesIds = additionalServicesIds;
		this.price = price;
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

	public List<Long> getAdditionalServicesIds() {
		return additionalServicesIds;
	}

	public void setAdditionalServicesIds(List<Long> additionalServicesIds) {
		this.additionalServicesIds = additionalServicesIds;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PromotionDTO [dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", additionalServicesIds="
				+ additionalServicesIds + ", price=" + price + "]";
	}
	
	

}
