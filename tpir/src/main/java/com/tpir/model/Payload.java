package com.tpir.model;

public class Payload {

	private Product product;
	private When reason;
	
	public Payload(Product product, When reason){
		this.product=product;
		this.reason=reason;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public When getReason() {
		return reason;
	}
	public void setReason(When reason) {
		this.reason = reason;
	}
	
}
