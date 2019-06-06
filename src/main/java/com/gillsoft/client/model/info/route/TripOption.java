package com.gillsoft.client.model.info.route;

import java.io.Serializable;

public class TripOption implements Serializable {

	private static final long serialVersionUID = 3304167912329065460L;

	private String option;

	private String description;

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
