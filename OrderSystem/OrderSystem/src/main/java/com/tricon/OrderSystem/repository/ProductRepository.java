package com.tricon.OrderSystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tricon.OrderSystem.entity.Customer;

@Repository
public interface ProductRepository extends MongoRepository<Customer, String> {
	//Customer findBy_id(ObjectId _id);
}
