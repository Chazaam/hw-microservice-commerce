package com.ecommerce.microservicecommerce.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microservicecommerce.dao.ProductDao;
import com.ecommerce.microservicecommerce.exception.ProductNotFoundException;
import com.ecommerce.microservicecommerce.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class ProductController {

	@Autowired
	private ProductDao productDao;
	
/* GET */
	//products
	@GetMapping(value = "products")
	public List<Product> productsList(){
		return productDao.findAll();
	}
	/*@GetMapping(value = "products")
	public MappingJacksonValue productsList() {
		List<Product> products = productDao.findAll();
		
		//Filtering
		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("buyingPrice");
		FilterProvider filtersList = new SimpleFilterProvider().addFilter("myDynamicFilter", myFilter);
		MappingJacksonValue filteredProducts = new MappingJacksonValue(products);
		filteredProducts.setFilters(filtersList);
		
		return filteredProducts;
	}*/
	
	//products/{id}
	@GetMapping(value = "products/{id}")
	public Product retrieveProduct(@PathVariable int id) throws ProductNotFoundException {
		Product product = productDao.findById(id);
		if(product == null) throw new ProductNotFoundException("The product with the id "+ id +" is not found");
		
		return product;
	}
	
	//products/{name}
	@GetMapping(value = "products/search/{name}")
	public List<Product> retrieveProductByName(@PathVariable String name) {
		return productDao.findByNameLike("%" + name + "%");
	}
	
	//products/greaterthan/{priceMin}
		@GetMapping(value = "products/greaterthan/{priceMin}")
		public List<Product> retrieveProductMoreExpensiveThan(@PathVariable double priceMin){
			//return productDao.findByPriceGreaterThan(priceMin);
			return productDao.searchExpensiveProduct(priceMin);
		}
		
/* POST */
	//products
	@PostMapping(value = "products")
	public ResponseEntity<Void> createProduct(@Valid @RequestBody Product product) {
		Product productToAdd = productDao.save(product);
		
		if(productToAdd == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(productToAdd.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
/* PUT */	
	@PutMapping(value = "produits")
	public void updateProduct(@RequestBody Product product) {
		productDao.save(product);
	}
	
/* DELETE */	
	@DeleteMapping(value = "products/{id}")
	public void deleteProduct(@PathVariable int id) {
		productDao.deleteById(id);
	}
	
}
