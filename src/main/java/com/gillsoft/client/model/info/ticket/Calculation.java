package com.gillsoft.client.model.info.ticket;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Calculation implements Serializable {

	private static final long serialVersionUID = 6784011725142943385L;

    private BigDecimal price;

    private String currency;

    @JsonProperty("currency_name")
    private String currencyName;

    @JsonProperty("agent_profit")
    private BigDecimal agentProfit;

    private Discount discount;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public BigDecimal getAgentProfit() {
		return agentProfit;
	}

	public void setAgentProfit(BigDecimal agentProfit) {
		this.agentProfit = agentProfit;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

}
