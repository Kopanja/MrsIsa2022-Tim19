package com.IsaMrsTim19.projekat.dto;

import java.util.List;

public class ReservationDTO {

	private String dateFrom;
	
	private String dateTo;
	
	private List<Long> additionalServicesIds;

	public ReservationDTO() {
		super();
	}

	public ReservationDTO(String dateFrom, String dateTo) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	
	public ReservationDTO(String dateFrom, String dateTo, List<Long> additionalServicesIds) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.additionalServicesIds = additionalServicesIds;
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
	
	
	
}
