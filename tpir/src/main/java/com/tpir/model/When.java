package com.tpir.model;

public enum When {

	ALWAYS("ALWAYS"),
	ALL_TIME_LOW("ALL_TIME_LOW"),
	MORE_THAN_10("MORE_THAN_10");
	
	private String value;
	
	private When(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}
}
