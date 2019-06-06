package com.gillsoft.client.model.info.details;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Timetable implements Serializable {

	private static final long serialVersionUID = 6372600289017651151L;

    private String city;

    private String station;

    @JsonFormat(shape = Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date departure;

	@JsonProperty("departure_date")
	@JsonFormat(shape = Shape.STRING, pattern="yyyy-MM-dd")
    private Date departureDate;

    @JsonProperty("departure_time")
    private String departureTime;

    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

}
