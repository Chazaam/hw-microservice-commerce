package com.ecommerce.microservicecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.microservicecommerce.model.Product;

@RestController
public class ProductController {

	//products/{i}
	@GetMapping(value = "products/{id}")
	public Product retrieveProduct(@PathVariable int id) {
		Product product = new Product(id, new String("computer"), 100);
		
		return product;
	}	
	
}
