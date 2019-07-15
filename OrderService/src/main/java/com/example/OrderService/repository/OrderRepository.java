package com.example.OrderService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.OrderService.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
