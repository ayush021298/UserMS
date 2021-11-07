package com.infosys.orderManagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.orderManagement.dto.LoginDTO;
import com.infosys.orderManagement.dto.WishlistDTO;
import com.infosys.orderManagement.exception.OrderManagementException;
import com.infosys.orderManagement.dto.BuyerDTO;
import com.infosys.orderManagement.dto.CartDTO;
import com.infosys.orderManagement.service.BuyerService;

@RestController
@CrossOrigin
@Validated
public class BuyerController {
	
	@Autowired
	BuyerService buyerService;
	
	@Autowired
	Environment environment;
	
	//Gets full profile of a specific Buyer
	@GetMapping(value = "/buyer/{buyerId}")
	public ResponseEntity<BuyerDTO> getBuyerDetails(@PathVariable String buyerId) throws OrderManagementException {
		BuyerDTO buyer = buyerService.getBuyerProfile(buyerId);
		return new ResponseEntity<>(buyer, HttpStatus.OK);
	}
	
	//Registers new Buyer
	@PostMapping(value = "/buyer/register")
	public ResponseEntity<String> addBuyer(@Valid @RequestBody BuyerDTO buyerDTO) throws OrderManagementException {
		buyerService.createBuyer(buyerDTO);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	//Buyer Login
	@PostMapping(value = "/buyer/login")
	public ResponseEntity<String> buyerLogin(@RequestBody LoginDTO loginDTO) throws OrderManagementException {
		String status = buyerService.login(loginDTO);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	//Deletes Buyer
	@DeleteMapping(value = "/buyer/{buyerId}/delete")
	public ResponseEntity<String> deleteBuyer(@PathVariable String buyerId) throws OrderManagementException {
		buyerService.deleteBuyer(buyerId);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	//Add Product To Wishlist
	@PostMapping(value = "/buyer/{buyerId}/wishlist")
	public ResponseEntity<String> addProductToWishlist(@PathVariable String buyerId, @RequestBody WishlistDTO wishlistDTO) throws OrderManagementException {
		String prodId = wishlistDTO.getProdId();
		String url = "http://localhost:5454/products/{prodId}/valid";
		RestTemplate restTemplate = new RestTemplate();
		boolean validProdId = restTemplate.getForObject(url, Boolean.class, prodId);
		if (validProdId) {
			wishlistDTO.setBuyerId(buyerId);
			buyerService.addProductToWishlist(wishlistDTO);
		}	
		else {
			throw new OrderManagementException("API.PRODUCT_NOT_FOUND");
		}
		String successMessage = environment.getProperty("API.WISHLIST_ADD_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	//Add Product To Cart
	@PostMapping(value = "/buyer/{buyerId}/cart")
	public ResponseEntity<String> addProductToCart(@PathVariable String buyerId, @RequestBody CartDTO cartDTO) throws OrderManagementException {
		String prodId = cartDTO.getProdId();
		String url = "http://localhost:5454/products/{prodId}/valid";
		RestTemplate restTemplate = new RestTemplate();
		boolean validProdId = restTemplate.getForObject(url, Boolean.class, prodId);
		if (validProdId) {
			cartDTO.setBuyerId(buyerId);
			buyerService.addProductToCart(cartDTO);
		}
		else {
			throw new OrderManagementException("API.PRODUCT_NOT_FOUND");
		}
		String successMessage = environment.getProperty("API.CART_ADD_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	//Deletes Product From Wishlist
	@DeleteMapping(value = "/buyer/{buyerId}/{prodId}/wishlist")
	public ResponseEntity<String> deleteProductFromWishlist(@PathVariable String buyerId, @PathVariable String prodId) throws OrderManagementException {
		buyerService.deleteProductFromWishlist(buyerId, prodId);
		String successMessage = environment.getProperty("API.WISHLIST_DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	//Deletes Product From Cart
	@DeleteMapping(value = "/buyer/{buyerId}/{prodId}/cart")
	public ResponseEntity<String> deleteProductFromCart(@PathVariable String buyerId, @PathVariable String prodId) throws OrderManagementException {
		buyerService.deleteProductFromCart(buyerId, prodId);
		String successMessage = environment.getProperty("API.CART_DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	//Moves Product From Wishlist To Cart
	@PostMapping(value = "/buyer/{buyerId}/{prodId}/move")
	public ResponseEntity<String> moveProductToCart(@PathVariable String buyerId, @PathVariable String prodId, @RequestBody CartDTO cartDTO) throws OrderManagementException {
		Integer quantity = cartDTO.getQuantity();
		buyerService.moveProductToCart(buyerId, prodId, quantity);
		String successMessage = environment.getProperty("API.CART_MOVE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	//Checks if Buyer Id exists or not
	@GetMapping(value = "/buyer/{buyerId}/valid")
	public ResponseEntity<Boolean> validBuyer(@PathVariable String buyerId) throws OrderManagementException{
		boolean isValid = buyerService.validBuyer(buyerId);
		return new ResponseEntity<>(isValid, HttpStatus.OK);
	}
}
