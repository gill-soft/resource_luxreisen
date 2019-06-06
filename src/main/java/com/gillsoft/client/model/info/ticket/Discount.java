package com.gillsoft.client.model.info.ticket;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Discount implements Serializable {

	private static final long serialVersionUID = -8313465793245285393L;

	private Integer id;

	private BigDecimal rate;

	private BigDecimal price;

	@JsonProperty("agent_profit")
	private BigDecimal agentProfit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAgentProfit() {
		return agentProfit;
	}

	public void setAgentProfit(BigDecimal agentProfit) {
		this.agentProfit = agentProfit;
	}

}
