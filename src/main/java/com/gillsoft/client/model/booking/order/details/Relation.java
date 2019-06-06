package com.gillsoft.client.model.booking.order.details;

import java.io.Serializable;

public class Relation implements Serializable {

	private static final long serialVersionUID = 5461054803799872069L;

    private String relation;

    private String name;

    private String value;

    private String description;

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
