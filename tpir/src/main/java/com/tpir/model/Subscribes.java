package com.tpir.model;

import java.util.ArrayList;

public class Subscribes {

	private String user_id;
	private ArrayList<Subscribe> subscribe;
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String userId) {
		this.user_id = userId;
	}
	public ArrayList<Subscribe> getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(ArrayList<Subscribe> subscribe) {
		this.subscribe = subscribe;
	}
	
}