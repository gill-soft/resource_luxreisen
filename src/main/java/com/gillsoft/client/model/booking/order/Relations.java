package com.gillsoft.client.model.booking.order;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Relations implements Serializable {

	private static final long serialVersionUID = -3881748867572998927L;

	@JsonProperty("doc_expire_date")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private Date docExpireDate;

	private String gender;

	private String citizenship;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birth;

	@JsonProperty("doc_number")
	private String docNumber;

	@JsonProperty("doc_type")
	private Integer docType;

	public Date getDocExpireDate() {
		return docExpireDate;
	}

	public void setDocExpireDate(Date docExpireDate) {
		this.docExpireDate = docExpireDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public Integer getDocType() {
		return docType;
	}

	public void setDocType(Integer docType) {
		this.docType = docType;
	}
}
