package com.robert.properLad.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.robert.properLad.models.Category;
import com.robert.properLad.models.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
	List<Product> findAll(); 
//	List<Product> findByCategory(Category category);
//	Product findByBrand();
}
