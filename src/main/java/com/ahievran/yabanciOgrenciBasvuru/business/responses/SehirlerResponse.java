package com.ahievran.yabanciOgrenciBasvuru.business.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL) 
public class SehirlerResponse {
	@JsonProperty("id") 
	private int id;
	
    public SehirlerResponse(int id, String ingilizceAd) {
		super();
		this.id = id;
		this.ingilizceAd = ingilizceAd;
	}

	public SehirlerResponse() {
		super();
	}

	@JsonProperty("ingilizceAd") 
	private String ingilizceAd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIngilizceAd() {
		return ingilizceAd;
	}

	public void setIngilizceAd(String ingilizceAd) {
		this.ingilizceAd = ingilizceAd;
	}
}
