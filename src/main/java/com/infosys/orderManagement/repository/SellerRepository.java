package com.infosys.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.orderManagement.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, String>{

	Seller findByEmail(String email);
	Seller findByPhoneNo(String phoneNo);
}
