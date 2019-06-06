package com.gillsoft.client.model.info.details;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TripDetails implements Serializable {

	private static final long serialVersionUID = -6474430421580255055L;

	private Frequency frequency;

	private List<Timetable> timetable;

	private Refunds refunds;

    private String luggage;

    @JsonProperty("public_offer")
    private String publicOffer;

	private String regulations;

    public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public List<Timetable> getTimetable() {
		return timetable;
	}

	public void setTimetable(List<Timetable> timetable) {
		this.timetable = timetable;
	}

	public Refunds getRefunds() {
		return refunds;
	}

	public void setRefunds(Refunds refunds) {
		this.refunds = refunds;
	}

	public String getLuggage() {
		return luggage;
	}

	public void setLuggage(String luggage) {
		this.luggage = luggage;
	}

	public String getPublicOffer() {
		return publicOffer;
	}

	public void setPublicOffer(String publicOffer) {
		this.publicOffer = publicOffer;
	}

	public String getRegulations() {
		return regulations;
	}

	public void setRegulations(String regulations) {
		this.regulations = regulations;
	}

}
