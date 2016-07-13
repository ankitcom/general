package com.crossover.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CurrencyRate {

	private long timestamp;
	private String base;
	private Map<String,Float> rates;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Map<String, Float> getRates() {
		return rates;
	}
	public void setRates(Map<String, Float> rates) {
		this.rates = rates;
	}
	
}
