package com.gillsoft.client.model.info.route;

import java.io.Serializable;
import java.util.List;

public class RequiredTicketField implements Serializable {

	private static final long serialVersionUID = -3815170571231533637L;

	private String relation;

	private List<Option> select;

	private String value;

	private String name;

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public List<Option> getSelect() {
		return select;
	}

	public void setSelect(List<Option> select) {
		this.select = select;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
