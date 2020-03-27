package com.robert.properLad.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.robert.properLad.models.Cart;
import com.robert.properLad.models.Order;
import com.robert.properLad.models.Product;
import com.robert.properLad.service.ImageService;
import com.robert.properLad.service.ProductService;

@Controller
public class ProductController {
	private final ProductService productService;
	private final ImageService imageService;
	private final int[] quantities = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	
	public ProductController(ProductService productService, ImageService imageService) {
		this.productService = productService;
		this.imageService = imageService;
	}
	
	@GetMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product, @ModelAttribute("cart")Cart cart, @ModelAttribute("order") Order order, BindingResult result, Model model) {
		if(id != null) {
			Product p = productService.findProductById(id);
			model.addAttribute("product", p);
			model.addAttribute("images", imageService.findImagesByProductId(id));
			if (p == null) {
				return "redirect:/";
			}
		}
		return "customers/showProduct.jsp";
	}
	// Add item to cart
	@PostMapping("/addToCart/{id}")
	public String addToCart(@PathVariable("id") Long id, @ModelAttribute("cart") Cart cart, Model model) {
		// If user session true, create
		model.addAttribute("quantities", quantities);
		return "";
	}
}
