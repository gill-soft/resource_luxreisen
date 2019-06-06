package com.gillsoft.client.model.info;

import java.io.Serializable;
import java.util.List;

public class CitiesResponse implements Serializable {

	private static final long serialVersionUID = -1233516223986430466L;

	private List<City> cities;

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
