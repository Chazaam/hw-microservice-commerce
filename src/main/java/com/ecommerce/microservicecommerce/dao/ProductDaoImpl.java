package com.ecommerce.microservicecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.microservicecommerce.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	public static List<Product> products = new ArrayList<>();

	static {
		products.add(new Product(1, new String("Old Computer"), 200));
		products.add(new Product(2, new String("Playstation"), 300));
		products.add(new Product(3, new String("Mac Mini"), 500));
	}
	
	@Override
	public List<Product> findAll() {
		return products;
	}

	@Override
	public Product findById(int id) {
		for (Product product : products) {
			if(product.getId()==id) {
				return product;
			}
		}
		return null;
	}

	@Override
	public Product save(Product product) {
		products.add(product);
		return product;
	}

}
