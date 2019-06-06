package com.gillsoft.client.model.info.details;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Frequency implements Serializable {

	private static final long serialVersionUID = -4857754658293935073L;

	@JsonProperty("week_days")
    private List<String> weekDays;

	@JsonProperty("atdates")
    private List<String> atDates;

    private List<String> yearly;

    private List<Integer> monthly;

	public List<String> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(List<String> weekDays) {
		this.weekDays = weekDays;
	}

	public List<String> getAtDates() {
		return atDates;
	}

	public void setAtDates(List<String> atDates) {
		this.atDates = atDates;
	}

	public List<String> getYearly() {
		return yearly;
	}

	public void setYearly(List<String> yearly) {
		this.yearly = yearly;
	}

	public List<Integer> getMonthly() {
		return monthly;
	}

	public void setMonthly(List<Integer> monthly) {
		this.monthly = monthly;
	}

}
