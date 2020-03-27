package com.robert.properLad.service;

import org.springframework.stereotype.Service;

import com.robert.properLad.repos.CartRepo;

@Service
public class CartService {
	public final CartRepo cartRepo;
	
	public CartService(CartRepo cartRepo) {
		this.cartRepo = cartRepo;
	}
}
