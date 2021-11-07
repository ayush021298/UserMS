package com.infosys.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infosys.orderManagement.entity.Cart;
import com.infosys.orderManagement.entity.ProductId;

public interface CartRepository extends JpaRepository<Cart, ProductId>{

	Cart findByBuyerIdAndProdId(String buyerId, String prodId);
	
	@Query("DELETE FROM Cart c WHERE c.buyerId= :buyerId AND c.prodId= :prodId")
	@Modifying
	Integer deleteByBuyerIdAndProdId(@Param("buyerId") String buyerId, @Param("prodId") String prodId);
}
