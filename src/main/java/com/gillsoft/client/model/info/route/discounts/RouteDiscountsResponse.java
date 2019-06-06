package com.gillsoft.client.model.info.route.discounts;

import java.io.Serializable;
import java.util.List;

public class RouteDiscountsResponse implements Serializable {

	private static final long serialVersionUID = -2792894787713123786L;

	private List<Discount> discounts;

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

}
