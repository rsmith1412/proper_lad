package com.robert.properLad.repos;

import org.springframework.data.repository.CrudRepository;

import com.robert.properLad.models.Cart;

public interface CartRepo extends CrudRepository<Cart, Long> {
	
}
