package com.example.OrderService.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

@GraphQLProperty(name = "order", arguments = {
		@GraphQLArgument(name = "id", type="String")
})
@Document(collection="order")
public class Order {
	@Id
	private String id;
	private String customerId;
	private Customer customer;
	private List<Product> product;
	private String date;
	private int amount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Order(String id, String customerId,  List<Product> product) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.product = product;
	}

	public Order(String id2, String custId) {
		System.out.println("Order object created ");
	}

	public Order() {
		System.out.println("Object created");
	}



	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", customer=" + customer + ", product=" + product
				+ ", date=" + date + ", amount=" + amount + "]";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
