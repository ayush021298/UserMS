package com.infosys.orderManagement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.infosys.orderManagement.entity.Buyer;

public class BuyerDTO {
	
	String buyerId;
	@NotNull(message = "{buyer.name.absent}")
    @Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{buyer.name.invalid}")
	String name;
//	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "{buyer.email.invalid}")
	@Email(message = "{buyer.email.invalid}")
    @NotNull(message = "{buyer.email.absent}")
	String email;
	@Pattern(regexp="^[6-9]{1}[0-9]{9}$", message="{buyer.phoneno.invalid}")
	String phoneNo;
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#^!%*&])[A-Za-z\\d@$#^!%*&]{7,20}$", 
			message="{buyer.password.invalid}")
	String password;
	char isPrivileged;
	Integer rewardPoints;
	char isActive;
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
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
	public char getIsPrivileged() {
		return isPrivileged;
	}
	public void setIsPrivileged(char isPrivileged) {
		this.isPrivileged = isPrivileged;
	}
	public Integer getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	
	//Converts Entity into DTO
	public static BuyerDTO valueOf(Buyer buyer) {
		BuyerDTO buyerDTO = new BuyerDTO();
		buyerDTO.setBuyerId(buyer.getBuyerId());
		buyerDTO.setEmail(buyer.getEmail());
		buyerDTO.setName(buyer.getName());
		buyerDTO.setPassword(buyer.getPassword());
		buyerDTO.setPhoneNo(buyer.getPhoneNo());
		buyerDTO.setIsPrivileged(buyer.getIsPrivileged());
		buyerDTO.setRewardPoints(buyer.getRewardPoints());
		buyerDTO.setIsActive(buyer.getIsActive());
		
		return buyerDTO;
	}
	
	//Converts DTO into Entity
	public Buyer createEntity() {
		Buyer buyer = new Buyer();
		buyer.setBuyerId(this.buyerId);
		buyer.setEmail(this.email);
		buyer.setName(this.name);
		buyer.setPassword(this.password);
		buyer.setPhoneNo(this.phoneNo);
		buyer.setIsPrivileged(this.isPrivileged);
		buyer.setRewardPoints(this.rewardPoints);
		buyer.setIsActive(this.isActive);
		
		return buyer;
	}
}
