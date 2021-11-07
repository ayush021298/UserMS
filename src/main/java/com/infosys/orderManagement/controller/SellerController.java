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

import com.infosys.orderManagement.dto.LoginDTO;
import com.infosys.orderManagement.dto.SellerDTO;
import com.infosys.orderManagement.exception.OrderManagementException;
import com.infosys.orderManagement.service.SellerService;

@RestController
@CrossOrigin
@Validated
public class SellerController {

	@Autowired
	SellerService sellerService;
	
	@Autowired
	Environment environment;
	
	//Gets full profile of a specific Seller
	@GetMapping(value = "/seller/{sellerId}")
	public ResponseEntity<SellerDTO> getSellerDetails(@PathVariable String sellerId) throws OrderManagementException {
		SellerDTO seller = sellerService.getSellerProfile(sellerId);
		return new ResponseEntity<>(seller, HttpStatus.OK);
	}
	
	//Registers a Seller
	@PostMapping(value = "/seller/register")
	public ResponseEntity<String> addSeller(@Valid @RequestBody SellerDTO sellerDTO) throws OrderManagementException {
		sellerService.createSeller(sellerDTO);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	//Seller Login
	@PostMapping(value = "/seller/login")
	public ResponseEntity<String> sellerLogin(@RequestBody LoginDTO loginDTO) throws OrderManagementException {
		String status = sellerService.login(loginDTO);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	//Deletes Seller
	@DeleteMapping(value = "/seller/{sellerId}")
	public ResponseEntity<String> deleteSeller(@PathVariable String sellerId) throws OrderManagementException {
		sellerService.deleteSeller(sellerId);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	//Checks if Seller Id exists or not
	@GetMapping(value = "/seller/{sellerId}/valid")
	public ResponseEntity<Boolean> validSeller(@PathVariable String sellerId) throws OrderManagementException{
		boolean isValid = sellerService.validSeller(sellerId);
		return new ResponseEntity<>(isValid, HttpStatus.OK);
	}
}
