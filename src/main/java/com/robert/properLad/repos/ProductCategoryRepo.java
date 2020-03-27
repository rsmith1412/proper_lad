package com.robert.properLad.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.robert.properLad.models.ProductCategory;

@Repository
public interface ProductCategoryRepo extends CrudRepository<ProductCategory, Long> {
	
}
