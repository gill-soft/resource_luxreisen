package com.gillsoft.client.model.info.seats;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FreeSeatsResponse implements Serializable {

	private static final long serialVersionUID = -2217913130676985932L;

	@JsonProperty("free_seats")
	private List<FreeSeat> freeSeats;

	public List<FreeSeat> getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(List<FreeSeat> freeSeats) {
		this.freeSeats = freeSeats;
	}
	
}
