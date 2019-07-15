package com.example.OrderService.service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderService.entity.Customer;
import com.example.OrderService.entity.Order;
import com.example.OrderService.entity.Product;
import com.example.OrderService.repository.OrderRepository;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Service
public class OrderService {
	private GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
	private final String productUrl = "http://localhost:8085/graphql";
	private final String customertUrl = "http://localhost:8086/graphql";
	@Autowired
	private OrderRepository orderRepository;

	@GraphQLQuery(name = "getOrders")
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	public Object buildResponse(String url, Arguments arguments, Class classs)
			throws IllegalStateException, MalformedURLException {
		GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder().url(url).arguments(arguments)
				.request(classs).build();
		System.out.println("requestEntity " + requestEntity.getRequest());
		GraphQLResponseEntity<Object> responseEntity = graphQLTemplate.query(requestEntity, classs);
		System.out.println("responseEntity.getResponse() " + responseEntity.getResponse());
		return responseEntity.getResponse();
	}

	@GraphQLQuery(name = "getOrderById")
	public Order getOrderById(@GraphQLArgument(name = "id") String id)
			throws IllegalStateException, MalformedURLException {
		Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);
		Arguments arguments = new Arguments("getCustomerById", new Argument<String>("id", order.getCustomerId()));
		System.out.println(order.getCustomer());
		order.setCustomer((Customer) buildResponse(customertUrl, arguments, Customer.class));
		System.out.println(order.getCustomer());
		System.out.println("-------------");
		
		List<Product> products = new ArrayList<Product>();
		if (order.getProduct() != null) {
			order.getProduct().forEach(product -> {
				Arguments arg = new Arguments("getProductById", new Argument<String>("id", product.getId()));
				try {
					products.add((Product) buildResponse(productUrl, arg, Product.class));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			});
			order.setProduct(products);
			System.out.println(products.toString());
		}
		return order;
	}

	@GraphQLMutation(name = "registerOrder")
	public Order registerOrder(
			@GraphQLArgument(name = "customerId") String customerId,
			@GraphQLArgument(name = "products") List<Product> products,
			@GraphQLArgument(name = "date") String date,
			@GraphQLArgument(name = "amount") Integer amount) {
		Order order = new Order();
		order.setCustomerId(customerId);
		order.setProduct(products);
		order.setAmount(amount);
		order.setDate(date);
		System.out.println( orderRepository.save(order));
		return orderRepository.save(order);

	}

	/*
	 * public Order order(String id, String customerId, String productId) { Order
	 * order = new Order(); order.setId(id); order.setCustomerId(customerId);
	 * order.setProductId(productId); return orderRepository.save(order);
	 * 
	 * }
	 */
	@GraphQLMutation(name = "updateOrder")
	public Order updateOrder(String id, String customerId) throws IllegalStateException, MalformedURLException {
		Order order = getOrderById(id);
		order.setCustomerId(customerId);
		return orderRepository.save(order);

	}

	/*
	 * @GraphQLMutation(name = "deleteOrder") public String deleteOrder(String id)
	 * throws IllegalStateException, MalformedURLException { Order order =
	 * getOrderById(id); orderRepository.delete(order); return "Deleted"; }
	 */

}
