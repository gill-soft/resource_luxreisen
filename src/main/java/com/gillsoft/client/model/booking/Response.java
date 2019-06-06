package com.gillsoft.client.model.booking;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

	private static final long serialVersionUID = -4856967484041938318L;

	private List<Object> orders;

	public List<Object> getOrders() {
		return orders;
	}

	public void setOrders(List<Object> orders) {
		this.orders = orders;
	}

}
