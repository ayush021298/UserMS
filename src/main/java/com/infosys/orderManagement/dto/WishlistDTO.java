package com.infosys.orderManagement.dto;


import com.infosys.orderManagement.entity.Wishlist;

public class WishlistDTO {

	String buyerId;
	String prodId;
	
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	
	//Converts Entity into DTO
	public static WishlistDTO valueOf(Wishlist wishlist) {
		WishlistDTO wishlistDTO = new WishlistDTO();
		wishlistDTO.setBuyerId(wishlist.getBuyerId());
		wishlistDTO.setProdId(wishlist.getProdId());
		return wishlistDTO;
	}
	
	//Converts DTO into Entity
	public Wishlist createEntity() {
		Wishlist wishlist = new Wishlist();
		wishlist.setBuyerId(this.buyerId);
		wishlist.setProdId(this.prodId);
		return wishlist;
	}
}
