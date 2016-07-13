package com.crossover.model;

public class GeneralResponse {

	private int statusCode;
	private String message;
	private boolean ok;

	public GeneralResponse(int statusCode,String message,boolean ok){
		this.statusCode=statusCode;
		this.message=message;
		this.ok=ok;
	}
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
}