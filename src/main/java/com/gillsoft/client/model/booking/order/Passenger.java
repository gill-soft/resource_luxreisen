package com.gillsoft.client.model.booking.order;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger implements Serializable {

	private static final long serialVersionUID = 2097254679595188398L;

	private String seat;

	private String firstname;

	private String phone;

	@JsonProperty("seat_id")
	private String seatId;

	@JsonProperty("transport_id")
	private String transportId;

	private Discount discount;

	private Relations relations;

	private String email;

	private String lastname;

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getTransportId() {
		return transportId;
	}

	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Relations getRelations() {
		return relations;
	}

	public void setRelations(Relations relations) {
		this.relations = relations;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
