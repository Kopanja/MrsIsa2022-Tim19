package com.IsaMrsTim19.projekat.dto;

import java.util.List;

public class OfferListByPageDTO {

	List<OfferDTO> dtos;
	int totalNumberOfPages;
	
	
	public OfferListByPageDTO() {
		super();
	}


	public OfferListByPageDTO(List<OfferDTO> dtos, int totalNumberOfPages) {
		super();
		this.dtos = dtos;
		this.totalNumberOfPages = totalNumberOfPages;
	}


	public List<OfferDTO> getDtos() {
		return dtos;
	}


	public void setDtos(List<OfferDTO> dtos) {
		this.dtos = dtos;
	}


	public int getTotalNumberOfPages() {
		return totalNumberOfPages;
	}


	public void setTotalNumberOfPages(int totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}


	@Override
	public String toString() {
		return "OfferListByPageDTO [dtos=" + dtos + ", totalNumberOfPages=" + totalNumberOfPages + "]";
	}
	
	
	
}
