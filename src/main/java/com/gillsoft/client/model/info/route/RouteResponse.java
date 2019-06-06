package com.gillsoft.client.model.info.route;

import java.io.Serializable;
import java.util.List;

public class RouteResponse implements Serializable {

	private static final long serialVersionUID = -4337319544640803230L;

	private List<Route> routes;

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

}
