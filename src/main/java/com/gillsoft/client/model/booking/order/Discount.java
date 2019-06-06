package com.gillsoft.client.model.booking.order;

import java.io.Serializable;

public class Discount implements Serializable {

	private static final long serialVersionUID = -8682877999363909364L;

	private String description;

	private Integer id;

	public Discount() {
		
	}

	public Discount(Integer id) {
		this(id, null);
	}

	public Discount(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
