package com.robert.properLad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.robert.properLad.models.Product;
import com.robert.properLad.repos.ProductRepo;

@Service
public class ProductService {
	public final ProductRepo productRepo;
	
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	public Product createProduct(Product product) {
		productRepo.save(product);
		return product;
	}

	public Product findProductById(Long id) {
		Optional<Product> p = productRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
}
