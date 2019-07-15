package com.tricon.OrderSystem.service;

import java.util.List;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.OrderSystem.entity.Customer;
import com.tricon.OrderSystem.repository.ProductRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@GraphQLApi
@Service
public class CustomerService {

	@Autowired
	private ProductRepository customerRepository;
	private final ConcurrentMultiMap<String, FluxSink<Customer>> subscribers = new ConcurrentMultiMap<>();

	@GraphQLQuery(name = "getAllCustomers")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@GraphQLQuery(name = "getCustomerById")
	public Customer getCustomerId(String id) {
		return customerRepository.findById(id).orElseThrow((RuntimeException::new));
	}

	@GraphQLMutation(name = "registerCustomer")
	public Customer registerCustomer(@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "email") String email, @GraphQLArgument(name = "password") String password) {
		Customer cust = new Customer();
		cust.setId(id);
		cust.setEmail(email);
		cust.setPassword(password);
		return customerRepository.save(cust);
	}

	@GraphQLMutation(name = "updateCustomer")
	public Customer updateCustomer(@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "email") String email, @GraphQLArgument(name = "password") String password) {
		Customer cust = getCustomerId(id);
		cust.setId(id);
		cust.setEmail(email);
		cust.setPassword(password);
		return customerRepository.save(cust);

	}

	@GraphQLMutation(name = "deleteCustomer")
	public void deleteCustomer(@GraphQLArgument(name = "id") String id) {
		customerRepository.deleteById(id);
	}

	@GraphQLSubscription
	public Publisher<Customer> taskStatusChanged(String code) {
		return Flux.create(
				subscriber -> subscribers.add(code, subscriber.onDispose(() -> subscribers.remove(code, subscriber))),
				FluxSink.OverflowStrategy.LATEST);
	}
}
