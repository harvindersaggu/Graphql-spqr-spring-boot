package com.tricon.Product.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.leangen.graphql.annotations.GraphQLQuery;

@Document(collection = "product")
public class Product {
	@Id
	@GraphQLQuery(name = "id")
	private String id;
	
	@GraphQLQuery(name = "productName")
	private String productName;
	
	@GraphQLQuery(name = "quantity")
	private int quantity;
	
	@GraphQLQuery(name = "category")
	private String category;
	
	@GraphQLQuery(name = "cost")
	private long cost;
	
	@GraphQLQuery(name = "description")
	private String description;

	public Product() {
		System.out.println("Product Object created");
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public Product(String id, String productName, int quantity, String category, long cost, String description) {
		super();
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.category = category;
		this.cost = cost;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
