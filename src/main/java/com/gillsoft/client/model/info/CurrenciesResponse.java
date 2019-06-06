package com.gillsoft.client.model.info;

import java.io.Serializable;
import java.util.List;

public class CurrenciesResponse implements Serializable {

	private static final long serialVersionUID = 3810960334847480053L;

	private List<String> currencies;

	public List<String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<String> currencies) {
		this.currencies = currencies;
	}

}
