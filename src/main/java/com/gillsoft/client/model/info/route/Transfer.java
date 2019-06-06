package com.gillsoft.client.model.info.route;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transfer implements Serializable {

	private static final long serialVersionUID = -6139285840452590776L;

	@JsonProperty("city_name")
    private String cityName;

    private String station;

    @JsonProperty("station_coord")
    private String stationCoord;

    @JsonProperty("date_arrival")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date dateArrival;

    @JsonProperty("time_arrival")
    private String timeArrival;

    @JsonProperty("date_departure")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date dateDeparture;

    @JsonProperty("time_departure")
    private String timeDeparture;

    @JsonProperty("change_time")
    private Time changeTime;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStationCoord() {
		return stationCoord;
	}

	public void setStationCoord(String stationСoord) {
		this.stationCoord = stationСoord;
	}

	public Date getDateArrival() {
		return dateArrival;
	}

	public void setDateArrival(Date dateArrival) {
		this.dateArrival = dateArrival;
	}

	public String getTimeArrival() {
		return timeArrival;
	}

	public void setTimeArrival(String timeArrival) {
		this.timeArrival = timeArrival;
	}

	public Date getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public String getTimeDeparture() {
		return timeDeparture;
	}

	public void setTimeDeparture(String timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	public Time getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Time changeTime) {
		this.changeTime = changeTime;
	}

}
