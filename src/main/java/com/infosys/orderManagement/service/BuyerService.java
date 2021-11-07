package com.infosys.orderManagement.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.orderManagement.dto.LoginDTO;
import com.infosys.orderManagement.dto.WishlistDTO;
import com.infosys.orderManagement.dto.BuyerDTO;
import com.infosys.orderManagement.dto.CartDTO;
import com.infosys.orderManagement.entity.Buyer;
import com.infosys.orderManagement.entity.Cart;
import com.infosys.orderManagement.entity.Wishlist;
import com.infosys.orderManagement.exception.OrderManagementException;
import com.infosys.orderManagement.repository.BuyerRepository;
import com.infosys.orderManagement.repository.CartRepository;
import com.infosys.orderManagement.repository.WishlistRepository;


@Service
@Transactional
public class BuyerService {
	
	@Autowired
	BuyerRepository buyerRepo;
	
	@Autowired
	WishlistRepository wishlistRepo;
	
	@Autowired
	CartRepository cartRepo; 
	
	//Buyer Registration
	public void createBuyer(BuyerDTO buyerDTO) throws OrderManagementException{
		Optional<Buyer> optional = buyerRepo.findById(buyerDTO.getBuyerId());
		if (optional.isPresent())
			throw new OrderManagementException("Service.BUYER_ALREADY_PRESENT");
		Buyer buyer = buyerDTO.createEntity();
		if (buyerRepo.findByPhoneNo(buyer.getPhoneNo())==null)
			buyerRepo.save(buyer);
		else
			throw new OrderManagementException("Service.BUYER_CONTACT_ALREADY_PRESENT");
	}
	
	//Gets Specific Buyer Details
	public BuyerDTO getBuyerProfile(String buyerId) throws OrderManagementException{
		Optional<Buyer> optional = buyerRepo.findById(buyerId);
		if (optional.isEmpty())
			throw new OrderManagementException("Service.BUYER_NOT_FOUND");
		BuyerDTO buyerDTO = BuyerDTO.valueOf(optional.get());
		return buyerDTO;
	}
	
	//Login
	public String login(LoginDTO loginDTO) throws OrderManagementException{
		Buyer buyer = buyerRepo.findByEmail(loginDTO.getEmail());
		if (buyer != null && buyer.getPassword().equals(loginDTO.getPassword())) {
			if (buyer.getRewardPoints()>=10000)
				buyerRepo.updatePrivilegedStatus(buyer.getBuyerId());
			return "Login Successfull";
		}
		return "Login Denied";
	}
	
	//Deletes A Buyer
	public void deleteBuyer(String buyerId) throws OrderManagementException{
		Optional<Buyer> optional = buyerRepo.findById(buyerId);
		if (optional.isEmpty())
			throw new OrderManagementException("Service.BUYER_NOT_FOUND");
		buyerRepo.deleteById(buyerId);
	}
	
	//Add Product To Wishlist
	public void addProductToWishlist(WishlistDTO wishlistDTO) throws OrderManagementException{
		Wishlist wishlist = wishlistDTO.createEntity();
		wishlistRepo.save(wishlist);
	}
	
	//Add Product To Cart
	public void addProductToCart(CartDTO cartDTO) {
		Cart cart = cartDTO.createEntity();
		cartRepo.save(cart);
	}
	
	//Deletes Product From Wishlist
	public void deleteProductFromWishlist(String buyerId, String prodId) throws OrderManagementException{
		Wishlist wishlist = wishlistRepo.findByBuyerIdAndProdId(buyerId, prodId);
		if (wishlist==null)
			throw new OrderManagementException("Service.WISHLIST_NOT_FOUND");
		wishlistRepo.deleteByBuyerIdAndProdId(buyerId, prodId);
	}
	
	//Deletes Product From Cart
	public void deleteProductFromCart(String buyerId, String prodId) throws OrderManagementException{
		Cart cart = cartRepo.findByBuyerIdAndProdId(buyerId, prodId);
		if (cart==null)
			throw new OrderManagementException("Service.CART_NOT_FOUND");
		cartRepo.deleteByBuyerIdAndProdId(buyerId, prodId);
	}
	
	//Moves Product From Wishlist To Cart
	public void moveProductToCart(String buyerId, String prodId, Integer quantity) throws OrderManagementException{
		Wishlist wishlist = wishlistRepo.findByBuyerIdAndProdId(buyerId, prodId);
		if (wishlist==null)
			throw new OrderManagementException("Service.WISHLIST_NOT_FOUND");
		Cart cart = new Cart();
		cart.setBuyerId(buyerId);
		cart.setProdId(prodId);
		cart.setQuantity(quantity);
		cartRepo.save(cart);
		wishlistRepo.deleteByBuyerIdAndProdId(buyerId, prodId);
	}
	
	//Checks if Buyer Id exists or not
	public boolean validBuyer(String buyerId) throws OrderManagementException{
		Optional<Buyer> optional = buyerRepo.findById(buyerId);
		if (optional.isPresent())
			return true;
		return false;
	}
}
