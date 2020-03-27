package com.robert.properLad.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.robert.properLad.models.Category;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
	List<Category> findAll();
	List<Category> findAllByProductsId(Long id);
//	List<String> findAll(String category_name);
}
