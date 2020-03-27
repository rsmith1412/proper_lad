package com.robert.properLad.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.robert.properLad.models.Order;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
	Optional<Order> findById(Long id);
	List<Order> findAll();
	List<Order> findAllByUserId(Long id);
}
