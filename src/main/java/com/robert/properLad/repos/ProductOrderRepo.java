package com.robert.properLad.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.robert.properLad.models.ProductOrder;

@Repository
public interface ProductOrderRepo extends CrudRepository<ProductOrder, Long> {
	
}
