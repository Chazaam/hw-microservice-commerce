package com.ecommerce.microservicecommerce.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecommerce.microservicecommerce.model.Product;

@Repository
public interface ProductDao extends JpaRepository <Product, Integer>{
	
	Product findById(int id);
	
	List<Product> findByPriceGreaterThan(double price);
	
	@Query("SELECT id, name, price FROM Product p where p.price > :priceMin")
	List<Product> searchExpensiveProduct(@Param("priceMin") double priceMin);
}