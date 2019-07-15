package com.tricon.Product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tricon.Product.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
