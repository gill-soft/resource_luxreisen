package com.gillsoft.client.model.info.route;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Option implements Serializable {

	private static final long serialVersionUID = -3173904696002131791L;

	@JsonProperty("doc_id")
    private String docId;

	@JsonProperty("doc_name")
    private String docName;

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

}
