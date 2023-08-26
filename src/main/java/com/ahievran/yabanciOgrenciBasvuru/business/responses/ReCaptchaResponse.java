package com.ahievran.yabanciOgrenciBasvuru.business.responses;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ReCaptchaResponse {
	private boolean success;
	
	private String hostname;
	
	public boolean isSuccess() {
		return success;
	}

	public ReCaptchaResponse(boolean success, String hostname, String challenge_ts, String[] errorCodes) {
		super();
		this.success = success;
		this.hostname = hostname;
		this.challenge_ts = challenge_ts;
		this.errorCodes = errorCodes;
	}

	public ReCaptchaResponse() {
		super();
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getChallenge_ts() {
		return challenge_ts;
	}

	public void setChallenge_ts(String challenge_ts) {
		this.challenge_ts = challenge_ts;
	}

	public String[] getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(String[] errorCodes) {
		this.errorCodes = errorCodes;
	}

	private String challenge_ts;
	
	@JsonProperty("error-codes")
	private String[] errorCodes;
}
