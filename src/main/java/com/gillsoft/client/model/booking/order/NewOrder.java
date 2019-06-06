package com.gillsoft.client.model.booking.order;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewOrder implements Serializable {

	private static final long serialVersionUID = 3846597101203209568L;

	private List<Passenger> passengers;

	@JsonProperty("route_id")
	private String routeId;

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
}
