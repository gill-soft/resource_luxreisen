package com.gillsoft.client.model;

import java.io.Serializable;

public class Response<T> implements Serializable {

	private static final long serialVersionUID = -8215611730539384889L;

	Data<T> data;

	public Data<T> getData() {
		return data;
	}

	public void setData(Data<T> data) {
		this.data = data;
	}

}
