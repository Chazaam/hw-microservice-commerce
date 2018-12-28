package com.ecommerce.microservicecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@JsonFilter("myDynamicFilter")
//@JsonIgnoreProperties(value = {"buyingPrice", "id"})
@Entity
public class Product {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private double price;
	
	//@JsonIgnore
	private double buyingPrice;
	
	public Product() {}
	
	public Product(int id, String name, double price, double buyingPrice) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.buyingPrice = buyingPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	
	@Override
	public String toString() {
		//return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
		return "Product{" +
				"id=" + id +
				", name=" + name + 
				", price=" + price + 
				", buyingPrice=" + buyingPrice + 
				"}";
	}

}
