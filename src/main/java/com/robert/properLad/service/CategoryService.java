package com.robert.properLad.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.robert.properLad.models.Category;
import com.robert.properLad.repos.CategoryRepo;

@Service
public class CategoryService {
	private final CategoryRepo catRepo;
	
	public CategoryService(CategoryRepo catRepo) {
		this.catRepo = catRepo;
	}
	
	public List<Category> getAllCategories() {
		return catRepo.findAll();
	}
	public Category createCategory(Category category) {
		catRepo.save(category);
		return category;
	}
}
