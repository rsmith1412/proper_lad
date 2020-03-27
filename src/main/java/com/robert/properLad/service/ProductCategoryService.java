package com.robert.properLad.service;

import org.springframework.stereotype.Service;

import com.robert.properLad.models.ProductCategory;
import com.robert.properLad.repos.ProductCategoryRepo;

@Service
public class ProductCategoryService {
	public final ProductCategoryRepo productCategoryRepo;
	
	public ProductCategoryService(ProductCategoryRepo productCategoryRepo) {
		this.productCategoryRepo = productCategoryRepo;
	}
	
	public ProductCategory createProdCat(ProductCategory productCategory) {
		return productCategoryRepo.save(productCategory);
	}

}
