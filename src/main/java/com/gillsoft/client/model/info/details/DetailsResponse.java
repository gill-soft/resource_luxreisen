package com.gillsoft.client.model.info.details;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailsResponse implements Serializable {

	private static final long serialVersionUID = -55164526430080404L;

	@JsonProperty("trip_details")
	private TripDetails tripDetails;

	public TripDetails getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(TripDetails tripDetails) {
		this.tripDetails = tripDetails;
	}

}
