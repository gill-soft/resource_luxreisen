package com.gillsoft.client.model.info.details;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Refunds implements Serializable {

	private static final long serialVersionUID = 4344895179308642886L;
	
	private static ObjectMapper objectMapper;
	private static final Logger LOGGER = LogManager.getLogger(Refunds.class);

	private List<Definition> definitionList;

	@JsonProperty("cancel_free_min")
	private Integer cancelFreeMin;

	static {
    	objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

	@JsonGetter("definition_list")
	public List<Definition> getDefinitionList() {
		return definitionList;
	}

	@JsonSetter("definition_list")
	public void setDefinitionList(List<Definition> definitionList) {
		this.definitionList = definitionList;
	}

	@JsonSetter("definitions")
	public void setDefinitions(JsonNode definitions) {
		try {
			definitionList = definitions.isArray()
					? Arrays.asList(objectMapper.readValue(definitions.toString().getBytes(), Definition[].class))
					: null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			definitionList = null;
		}
	}

	public Integer getCancelFreeMin() {
		return cancelFreeMin;
	}

	public void setCancelFreeMin(Integer cancelFreeMin) {
		this.cancelFreeMin = cancelFreeMin;
	}

}
