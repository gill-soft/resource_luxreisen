package com.gillsoft.client.model.info.route;

import java.io.Serializable;

public class Time implements Serializable {

	private static final long serialVersionUID = 7482350194746802355L;

    private Integer days;

	private Integer hours;

    private Integer mins;

    public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getMins() {
		return mins;
	}

	public void setMins(Integer mins) {
		this.mins = mins;
	}

}
