package com.gillsoft.client.model.info;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City extends Location implements Serializable {

	private static final long serialVersionUID = 2930222126204565473L;

    @JsonProperty("country_id")
    private Integer countryId;

    @JsonProperty("country_name")
    private String countryName;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
    
}
