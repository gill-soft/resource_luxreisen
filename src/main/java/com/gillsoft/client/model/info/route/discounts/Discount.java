package com.gillsoft.client.model.info.route.discounts;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Discount implements Serializable {

	private static final long serialVersionUID = -8415695416828035720L;

	@JsonProperty("discount_id")
    private Integer discountId;

    private String name;

    private Byte value;

    private Relation relation;

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getValue() {
		return value;
	}

	public void setValue(Byte value) {
		this.value = value;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

}
