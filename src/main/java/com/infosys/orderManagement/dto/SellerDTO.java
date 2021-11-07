package com.infosys.orderManagement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.infosys.orderManagement.entity.Seller;

public class SellerDTO {

	String sellerId;
	@NotNull(message = "{seller.name.absent}")
    @Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{seller.name.invalid}")
	String name;
//	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "{seller.email.invalid}")
    @NotNull(message = "{seller.email.absent}")
	@Email(message = "{seller.email.invalid}")
	String email;
	@Pattern(regexp="^[6-9]{1}[0-9]{9}$", message="{seller.phoneno.invalid}")
	String phoneNo;
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#^!%*&])[A-Za-z\\d@$#^!%*&]{7,20}$", 
			message="{seller.password.invalid}")
	String password;
	char isActive;
	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	
	//Converts Entity into DTO
	public static SellerDTO valueOf(Seller seller) {
		SellerDTO sellerDTO = new SellerDTO();
		sellerDTO.setSellerId(seller.getSellerId());
		sellerDTO.setEmail(seller.getEmail());
		sellerDTO.setName(seller.getName());
		sellerDTO.setPassword(seller.getPassword());
		sellerDTO.setPhoneNo(seller.getPhoneNo());
		sellerDTO.setIsActive(seller.getIsActive());
		return sellerDTO;
	}
	
	//Converts DTO into Entity
	public Seller createEntity() {
		Seller seller = new Seller();
		seller.setSellerId(this.sellerId);
		seller.setEmail(this.email);
		seller.setName(this.name);
		seller.setPassword(this.password);
		seller.setPhoneNo(this.phoneNo);
		seller.setIsActive(this.isActive);
		return seller;
	}
}
