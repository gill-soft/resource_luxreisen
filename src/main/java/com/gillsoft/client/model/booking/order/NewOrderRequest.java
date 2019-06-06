package com.gillsoft.client.model.booking.order;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gillsoft.client.model.booking.order.Passenger;

public class NewOrderRequest implements Serializable {

	private static final long serialVersionUID = -201280582228861920L;

	@JsonProperty("route_id")
	private String routeId;

	private List<Passenger> passengers;

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
}
