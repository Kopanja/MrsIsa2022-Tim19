package com.IsaMrsTim19.projekat.dto;

import java.util.List;

public class BoatDTO {

	OfferDTO offerDTO;
	private double length;
	private int numOfMotors;
	private int motorStrength;
	private int maxSpeed;
	private int capacity;
	private List<String> contentImages;
	
	
	public BoatDTO() {
		super();
	}


	public BoatDTO(OfferDTO offerDTO, double length, int numOfMotors, int motorStrength, int maxSpeed, int capacity) {
		super();
		this.offerDTO = offerDTO;
		this.length = length;
		this.numOfMotors = numOfMotors;
		this.motorStrength = motorStrength;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
	}

	

	public BoatDTO(OfferDTO offerDTO, double length, int numOfMotors, int motorStrength, int maxSpeed, int capacity,
			List<String> contentImages) {
		super();
		this.offerDTO = offerDTO;
		this.length = length;
		this.numOfMotors = numOfMotors;
		this.motorStrength = motorStrength;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
		this.contentImages = contentImages;
	}


	public OfferDTO getOfferDTO() {
		return offerDTO;
	}


	public void setOfferDTO(OfferDTO offerDTO) {
		this.offerDTO = offerDTO;
	}


	public double getLength() {
		return length;
	}


	public void setLength(double length) {
		this.length = length;
	}


	public int getNumOfMotors() {
		return numOfMotors;
	}


	public void setNumOfMotors(int numOfMotors) {
		this.numOfMotors = numOfMotors;
	}


	public int getMotorStrength() {
		return motorStrength;
	}


	public void setMotorStrength(int motorStrength) {
		this.motorStrength = motorStrength;
	}


	public int getMaxSpeed() {
		return maxSpeed;
	}


	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	
	public List<String> getContentImages() {
		return contentImages;
	}


	public void setContentImages(List<String> contentImages) {
		this.contentImages = contentImages;
	}


	@Override
	public String toString() {
		return "BoatDTO [offerDTO=" + offerDTO + ", length=" + length + ", numOfMotors=" + numOfMotors
				+ ", motorStrength=" + motorStrength + ", maxSpeed=" + maxSpeed + ", capacity=" + capacity + "]";
	}
	
	
	
}
