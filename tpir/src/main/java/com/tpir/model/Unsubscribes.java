package com.tpir.model;

import java.util.ArrayList;

public class Unsubscribes {

	private String user_id;
	private ArrayList<Unsubscribe> unsubscribe;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public ArrayList<Unsubscribe> getUnsubscribe() {
		return unsubscribe;
	}
	public void setUnsubscribe(ArrayList<Unsubscribe> unsubscribe) {
		this.unsubscribe = unsubscribe;
	}
	
}
