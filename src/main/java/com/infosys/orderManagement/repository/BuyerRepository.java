package com.infosys.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infosys.orderManagement.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, String>{
	Buyer findByEmail(String email);
	Buyer findByPhoneNo(String phoneNo);
	@Query("UPDATE Buyer b SET b.isPrivileged= 'Y' WHERE b.buyerId= :buyerId")
	@Modifying
	Integer updatePrivilegedStatus(@Param("buyerId") String buyerId);
}
