package com.gillsoft.client.model.info.ticket;

import java.io.Serializable;

public class CalculationResponse implements Serializable {

	private static final long serialVersionUID = 2377066766909774711L;

	private Calculation calculation;

	public Calculation getCalculation() {
		return calculation;
	}

	public void setCalculation(Calculation calculation) {
		this.calculation = calculation;
	}

}
