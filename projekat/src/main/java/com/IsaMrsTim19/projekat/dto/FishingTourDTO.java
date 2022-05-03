package com.IsaMrsTim19.projekat.dto;

import java.util.List;

public class FishingTourDTO {

	private OfferDTO offerDTO;
	private int maxNumOfPeople;
	private List<String> contentImages;
	
	
	public FishingTourDTO() {
		super();
	}


	public FishingTourDTO(OfferDTO offerDTO, int maxNumOfPeople) {
		super();
		this.offerDTO = offerDTO;
		this.maxNumOfPeople = maxNumOfPeople;
	}


	public FishingTourDTO(OfferDTO offerDTO, int maxNumOfPeople, List<String> contentImages) {
		super();
		this.offerDTO = offerDTO;
		this.maxNumOfPeople = maxNumOfPeople;
		this.contentImages = contentImages;
	}


	public OfferDTO getOfferDTO() {
		return offerDTO;
	}


	public void setOfferDTO(OfferDTO offerDTO) {
		this.offerDTO = offerDTO;
	}


	public int getMaxNumOfPeople() {
		return maxNumOfPeople;
	}


	public void setMaxNumOfPeople(int maxNumOfPeople) {
		this.maxNumOfPeople = maxNumOfPeople;
	}


	public List<String> getContentImages() {
		return contentImages;
	}


	public void setContentImages(List<String> contentImages) {
		this.contentImages = contentImages;
	}


	@Override
	public String toString() {
		return "FishingTourDTO [offerDTO=" + offerDTO + ", maxNumOfPeople=" + maxNumOfPeople + ", contentImages="
				+ contentImages + "]";
	}
	
	
	
}
