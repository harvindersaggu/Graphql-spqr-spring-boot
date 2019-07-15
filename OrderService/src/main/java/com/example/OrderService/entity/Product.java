package com.example.OrderService.entity;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

@GraphQLProperty(name = "getProductById", arguments = { @GraphQLArgument(name = "id", type = "String") })
public class Product {
	private String id;
	private String productName;
	private int quantity;
	private String category;

	Product() {
		System.out.println("Product Object created");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Product(String _id, String productName, int quantity, String category) {
		super();
		this.id = _id;
		this.productName = productName;
		this.quantity = quantity;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", quantity=" + quantity + ", category="
				+ category + "]";
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
