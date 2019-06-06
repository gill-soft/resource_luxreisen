package com.gillsoft.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gillsoft.client.model.booking.Error;

public class Data<T> implements Serializable {

	private static final long serialVersionUID = -5063137358408060631L;

	private static ObjectMapper objectMapper;

	private Boolean success;

	private JsonNode response;

    private JsonNode error;

    private String lang;

    static {
    	objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setResponse(JsonNode response) {
		this.response = response;
	}

	public T getResponse(Class<T> type) {
		try {
			return objectMapper.readValue(response.toString().getBytes(), type);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isError() {
		if (error == null) {
			return false;
		}
		return error.isBoolean() ? error.asBoolean()
				: error.isTextual() ? !error.asText().isEmpty() : error.isArray() ? error.elements().hasNext() : false;
	}

	public void setError(JsonNode error) {
		this.error = error;
	}

	public String getErrorMsg() {
		return error != null && error.isTextual() ? error.asText() : null;
	}

	public List<String> getErrorMsgList() {
		try {
			return error != null && error.isArray() && error.elements().hasNext()
					? Arrays.asList(objectMapper.readValue(error.toString().getBytes(), String[].class)) : null;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public List<Error> getErrorList() {
		try {
			return error != null && error.isArray() && error.elements().hasNext()
					? Arrays.asList(objectMapper.readValue(error.toString().getBytes(), Error[].class)) : null;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
