package com.robert.properLad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.robert.properLad.models.Order;
import com.robert.properLad.repos.OrderRepo;

@Service
public class OrderService {
	public final OrderRepo orderRepo;
	
	public OrderService(OrderRepo orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	public Long createOrder(Order order) {
		orderRepo.save(order);
		return order.getId();
	}
	public Order findOrderById(Long id) {
		Optional<Order> o = orderRepo.findById(id);
		if(o.isPresent()) {
			return o.get();
		}
		return null;
	}
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}
	public void deleteOrder(long id) {
		orderRepo.deleteById(id);
	}
}
