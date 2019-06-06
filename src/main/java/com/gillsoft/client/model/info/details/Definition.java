package com.gillsoft.client.model.info.details;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Definition implements Serializable {

	private static final long serialVersionUID = 5108781111575575359L;

    private Integer from;

    private Integer to;

    @JsonProperty("return_rate")
    private Byte returnRate;

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public Byte getReturnRate() {
		return returnRate;
	}

	public void setReturnRate(Byte returnRate) {
		this.returnRate = returnRate;
	}

}
