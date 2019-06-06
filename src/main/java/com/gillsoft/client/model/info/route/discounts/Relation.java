package com.gillsoft.client.model.info.route.discounts;

import java.io.Serializable;

public class Relation implements Serializable {

	private static final long serialVersionUID = 8769527398529256052L;

	private String type;

    private String comment;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
