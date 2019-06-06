package com.gillsoft.client.model.info.seats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gillsoft.model.Seat;

public class FreeSeat implements Serializable {

	private static final long serialVersionUID = -8322977256657743549L;

    private String description;

    private Integer seats;

    @JsonProperty("transport_id")
    private Integer transportId;

    @JsonProperty("free_seats")
    private Integer freeSeats;

    private List<Available> available;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	public Integer getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(Integer freeSeats) {
		this.freeSeats = freeSeats;
	}

	public List<Available> getAvailable() {
		return available;
	}

	public void setAvailable(List<Available> available) {
		this.available = available;
	}

	public List<Seat> toSeats() {
		if (freeSeats != 0 && available != null && !available.isEmpty()) {
			return available.stream().map(seat -> seat.toSeat(this.transportId)).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

}
