package com.infosys.orderManagement.dto;

import com.infosys.orderManagement.entity.Cart;

public class CartDTO {

	String buyerId;
	String prodId;
	Integer quantity;
	
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	//Converts Entity into DTO
	public static CartDTO valueOf(Cart cart) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setBuyerId(cart.getBuyerId());
		cartDTO.setProdId(cart.getProdId());
		cartDTO.setQuantity(cart.getQuantity());
		return cartDTO;
	}
	
	//Converts DTO into Entity
	public Cart createEntity() {
		Cart cart = new Cart();
		cart.setBuyerId(this.buyerId);
		cart.setProdId(this.prodId);
		cart.setQuantity(this.quantity);
		return cart;
	}
}
