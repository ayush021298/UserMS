package com.infosys.orderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infosys.orderManagement.entity.Wishlist;
import com.infosys.orderManagement.entity.ProductId;

public interface WishlistRepository extends JpaRepository<Wishlist, ProductId>{

	Wishlist findByBuyerIdAndProdId(String buyerId, String prodId);
	
	@Query("DELETE FROM Wishlist w WHERE w.buyerId= :buyerId AND w.prodId= :prodId")
	@Modifying
	Integer deleteByBuyerIdAndProdId(@Param("buyerId") String buyerId,@Param("prodId") String prodId);
}
