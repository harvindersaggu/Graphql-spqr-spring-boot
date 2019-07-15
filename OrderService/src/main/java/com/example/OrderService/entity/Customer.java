package com.example.OrderService.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

@GraphQLProperty(name = "getCustomerById", arguments = { @GraphQLArgument(name = "id", type = "String") })
public class Customer implements Serializable {
	@Id
	public String id;
	private String userName;
	private String email;
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + "]";
	}

	Customer() {
		System.out.println("Customer Object created");
	}

	public Customer(String id, String userName, String email, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

}
