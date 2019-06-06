package com.gillsoft.client.model.booking.order;

import java.io.Serializable;
import java.util.List;

import com.gillsoft.client.model.booking.order.details.Order;

public class NewOrderResponse implements Serializable {

	private static final long serialVersionUID = -2205492514065422031L;

	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
