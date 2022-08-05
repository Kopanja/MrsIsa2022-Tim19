package com.IsaMrsTim19.projekat.dto;

import java.util.List;

import com.IsaMrsTim19.projekat.model.AdditionalService;

public class AccommodationDTO {

	
	private OfferDTO offerDTO;
	private int roomNumber;
	private int numberOfPeople;
	private List<String> contentImages;
	private List<AdditionalService> additionalServices;
	
	
	public AccommodationDTO() {
		super();
	}


	public AccommodationDTO(OfferDTO offerDTO, int roomNumber, int numberOfPeople) {
		super();
		this.offerDTO = offerDTO;
		this.roomNumber = roomNumber;
		this.numberOfPeople = numberOfPeople;
	}

	


	public AccommodationDTO(OfferDTO offerDTO, int roomNumber, int numberOfPeople, List<String> contentImages) {
		super();
		this.offerDTO = offerDTO;
		this.roomNumber = roomNumber;
		this.numberOfPeople = numberOfPeople;
		this.contentImages = contentImages;
	}


	public OfferDTO getOfferDTO() {
		return offerDTO;
	}


	public void setOfferDTO(OfferDTO offerDTO) {
		this.offerDTO = offerDTO;
	}


	public int getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}


	public int getNumberOfPeople() {
		return numberOfPeople;
	}


	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}


	
	public List<String> getContentImages() {
		return contentImages;
	}


	public void setContentImages(List<String> contentImages) {
		this.contentImages = contentImages;
	}




	public List<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}


	public void setAdditionalServices(List<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}


	@Override
	public String toString() {
		return "AccommodationDTO [offerDTO=" + offerDTO + ", roomNumber=" + roomNumber + ", numberOfPeople="
				+ numberOfPeople + "]";
	}
	
	
	
	
}
