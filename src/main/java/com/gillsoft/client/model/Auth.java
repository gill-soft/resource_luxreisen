package com.gillsoft.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Auth implements Serializable {

	private static final long serialVersionUID = 8615978426486177239L;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("expires_in")
	private long expiresIn;

	@JsonProperty("access_token")
	private String accessToken;
   
	@JsonProperty("refresh_token")
	private String refreshToken;

	private String error;

	private String message;

	private Long expiresOn;

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
		this.expiresOn = System.currentTimeMillis() + (expiresIn * 1000);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getToken() {
		if (tokenType != null && accessToken != null) {
			return tokenType + " " + accessToken;
		}
		return "";
	}

	public boolean isExpired() {
		return expiresOn == null || expiresOn < System.currentTimeMillis();
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
