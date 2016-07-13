package com.crossover.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AppLog {

	private String logName;
	private LogLevel logLevel;
	private String message;
	private String user;
	private Object[] messageObjects;

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Object[] getMessageObjects() {
		return messageObjects;
	}

	public void setMessageObjects(Object[] messageObjects) {
		this.messageObjects = messageObjects;
	}
}
