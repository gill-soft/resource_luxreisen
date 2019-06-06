package com.gillsoft.client.model.booking;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error implements Serializable {

	private static final long serialVersionUID = 2932680879683453068L;

	@JsonProperty("seat_id")
	private Integer seatId;

	@JsonProperty("transport_id")
	private Integer transportId;

	private String firstname;

	private String lastname;

	private String relation;

	@JsonProperty("discount_id")
	private Integer discountId;

	private String message;

	private Boolean success;

	private String lang;

	private Response response;

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Error: [" + String.join(";", this.seatId != null ? "seat_id:" + this.seatId : "",
				this.transportId != null ? "transport_id:" + this.transportId : "",
				this.firstname != null ? "firstname:" + this.firstname : "",
				this.lastname != null ? "lastname:" + this.lastname : "",
				this.relation != null ? "relation:" + this.relation : "",
				this.discountId != null ? "discount_id:" + this.discountId : "",
				this.message != null ? "message:" + this.message : "") + ']';
	}

}
