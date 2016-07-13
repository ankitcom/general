package com.crossover.model;

public enum LogLevel {

	ERROR("ERROR"),
	WARN("WARN"),
	INFO("INFO"),
	DEBUG("DEBUG"),
	TRACE("TRACE");
	
	private String value;
	
	private LogLevel(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}
	
}
