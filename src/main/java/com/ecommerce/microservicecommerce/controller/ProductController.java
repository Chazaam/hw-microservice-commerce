package com.ecommerce.microservicecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.microservicecommerce.dao.ProductDao;
import com.ecommerce.microservicecommerce.model.Product;

@RestController
public class ProductController {

	@Autowired
	private ProductDao productDao;
	
	//products
	@GetMapping(value = "products")
	public List<Product> productsList(){
		return productDao.findAll();
	}
	
	//products/{id}
	@GetMapping(value = "products/{id}")
	public Product retrieveProduct(@PathVariable int id) {
		return productDao.findById(id);
	}
	
	//products
	@PostMapping(value = "products")
	public void createProduct(@RequestBody Product product) {
		productDao.save(product);
	}
	
}
