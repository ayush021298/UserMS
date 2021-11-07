package com.infosys.orderManagement.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.orderManagement.dto.LoginDTO;
import com.infosys.orderManagement.dto.SellerDTO;
import com.infosys.orderManagement.entity.Seller;
import com.infosys.orderManagement.exception.OrderManagementException;
import com.infosys.orderManagement.repository.SellerRepository;

@Service
@Transactional
public class SellerService {

	@Autowired
	SellerRepository sellerRepo;
	
	//Seller Registration
	public void createSeller(SellerDTO sellerDTO) throws OrderManagementException{
		Optional<Seller> optional = sellerRepo.findById(sellerDTO.getSellerId());
		if (optional.isPresent())
			throw new OrderManagementException("Service.SELLER_ALREADY_PRESENT");
		Seller seller = sellerDTO.createEntity();
		if (sellerRepo.findByPhoneNo(seller.getPhoneNo())==null)
			sellerRepo.save(seller);
		else
			throw new OrderManagementException("Service.SELLER_CONTACT_ALREADY_PRESENT");
	}
	
	//Gets Specific Seller Details
	public SellerDTO getSellerProfile(String sellerId) throws OrderManagementException{
		Optional<Seller> optional = sellerRepo.findById(sellerId);
		if (optional.isEmpty())
			throw new OrderManagementException("Service.SELLER_NOT_FOUND");
		SellerDTO sellerDTO = SellerDTO.valueOf(optional.get());
		return sellerDTO;
	}
	
	//Login
	public String login(LoginDTO loginDTO) throws OrderManagementException{
		Seller seller = sellerRepo.findByEmail(loginDTO.getEmail());
		if (seller != null && seller.getPassword().equals(loginDTO.getPassword())) {
			return  "Login Successfull";
		}
		return "Login Denied";
	}
	
	//Deletes a Seller
	public void deleteSeller(String sellerId) throws OrderManagementException{
		Optional<Seller> optional = sellerRepo.findById(sellerId);
		if (optional.isEmpty())
			throw new OrderManagementException("Service.SELLER_NOT_FOUND");
		sellerRepo.deleteById(sellerId);
	}
	
	//Checks if Seller Id exists or not
	public boolean validSeller(String sellerId) throws OrderManagementException{
		Optional<Seller> optional = sellerRepo.findById(sellerId);
		if (optional.isPresent())
			return true;
		return false;
	}
}
