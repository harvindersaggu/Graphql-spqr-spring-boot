package com.tricon.Product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.Product.entity.Product;
import com.tricon.Product.repository.ProductRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@GraphQLQuery(name = "products")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@GraphQLQuery(name = "getProductById")
	public Product getProductId(@GraphQLArgument(name = "id") String id) {
		return productRepository.findById(id).orElseThrow((RuntimeException::new));
	}

	@GraphQLMutation(name = "save")
	public Product save(@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "productName") String productName,
			@GraphQLArgument(name = "category") String category) {
		Product product = new Product();
		product.setId(id);
		product.setProductName(productName);
		product.setCategory(category);
		return productRepository.save(product);
	}

	@GraphQLMutation(name = "update")
	public Product update(@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "productName") String productName,
			@GraphQLArgument(name = "category") String category) {
		Product product = getProductId(id);
		product.setId(id);
		product.setProductName(productName);
		product.setCategory(category);
		return productRepository.save(product);

	}

	@GraphQLMutation(name = "delete")
	public void delete(@GraphQLArgument(name = "id") String id) {
		productRepository.deleteById(id);
	}
}
