package com.gillsoft.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gillsoft.model.AbstractJsonModel;

@JsonInclude(Include.NON_NULL)
public class ServiceIdModel extends AbstractJsonModel {
	
	private static final long serialVersionUID = 7614884907411639503L;
	
	public ServiceIdModel() {
		
	}

	@Override
	public ServiceIdModel create(String json) {
		return (ServiceIdModel) super.create(json);
	}

}
