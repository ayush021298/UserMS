package com.infosys.orderManagement.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductId implements Serializable{
	
	String buyerId;
	String prodId;
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	
	
}
