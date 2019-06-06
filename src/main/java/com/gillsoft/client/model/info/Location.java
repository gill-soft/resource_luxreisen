package com.gillsoft.client.model.info;

import java.io.Serializable;
import java.util.List;

public class Location implements Serializable {

	private static final long serialVersionUID = 4375269498696466556L;

	protected int id;

	protected String name;

	private List<Location> cities;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Location> getCities() {
		return cities;
	}

	public void setCities(List<Location> cities) {
		this.cities = cities;
	}

}
